package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.BattleDTO;
import com.smileduster.vsboard.api.model.dto.GroupDTO;
import com.smileduster.vsboard.api.model.dto.GroupMemberDTO;
import com.smileduster.vsboard.api.model.po.*;
import com.smileduster.vsboard.api.service.IGroupService;
import com.smileduster.vsboard.api.tools.Converter;
import com.smileduster.vsboard.api.tools.Generator;
import com.smileduster.vsboard.service.data.AuthMapper;
import com.smileduster.vsboard.service.data.BattleMapper;
import com.smileduster.vsboard.service.data.GroupMapper;
import com.smileduster.vsboard.service.data.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {

    private final AuthMapper authMapper;
    private final MemberMapper memberMapper;
    private final BattleMapper battleMapper;
    private final GroupMapper groupMapper;
    private final Generator generator;
    private final Converter converter;

    public GroupServiceImpl(AuthMapper authMapper,
                            MemberMapper memberMapper,
                            BattleMapper battleMapper,
                            GroupMapper groupMapper,
                            Generator generator,
                            Converter converter) {
        this.authMapper = authMapper;
        this.memberMapper = memberMapper;
        this.battleMapper = battleMapper;
        this.groupMapper = groupMapper;
        this.generator = generator;
        this.converter = converter;
    }

    @Override
    public Response<?> createGroup(GroupDTO dto, int userId) {
        Group group = new Group();
        group.setGroupName(dto.getGroupName());
        group.setGroupNote(dto.getGroupNote());
        group.setGroupInfo(dto.getGroupInfo());
        group.setGroupUUID(generator.getUUID());
        group.setGroupBattleCount(0);
        group.setGroupMemberNumber(0);
        group.setGroupMemberCount(0);
        group.setUserId(userId);
        groupMapper.insertGroup(group);
        return Response.create(converter.formatUUID(group.getGroupUUID()));
    }

    @Override
    public Response<?> deleteGroup(String groupUUID, int userId) {
        Group group = groupMapper.selectGroupByUUID(converter.parseUUID(groupUUID));
        if (group == null || group.getUserId() != userId) {
            return Response.create();
        }
        if (group.getGroupBattleCount() > 0) {
            return Response.create(ResponseCode.dependentBattle, group.getGroupBattleCount());
        }

        groupMapper.deleteMembersByGroup(group.getGroupId());
        groupMapper.deleteGroupById(group.getGroupId());
        return Response.create();
    }

    @Override
    public Response<?> joinGroup(GroupMemberDTO dto, int userId) {
        Group group = groupMapper.selectGroupByUUID(converter.parseUUID(dto.getGroupUUID()));
        Member member = memberMapper.selectMemberByUUID(converter.parseUUID(dto.getMemberUUID()));
        if (group == null || group.getUserId() != userId) {
            return Response.create(ResponseCode.illegalGroup, dto.getGroupUUID());
        }
        if (member == null || member.getUserId() != userId) {
            return Response.create(ResponseCode.illegalMember, dto.getMemberUUID());
        }

        GroupMember groupMember = new GroupMember();
        groupMember.setGroupId(group.getGroupId());
        groupMember.setMemberId(member.getMemberId());
        groupMember.setGroupMemberName(dto.getGroupMemberName());
        groupMember.setGroupMemberNumber(group.getGroupMemberNumber()+1);
        groupMember.setGroupMemberPoint(0);

        groupMapper.insertGroupMember(groupMember);
        groupMapper.incMemberCount(group.getGroupId());
        groupMapper.incMemberNumber(group.getGroupId());
        return Response.create(groupMember.getGroupMemberNumber());
    }

    @Override
    public Response<List<GroupDTO>> getGroups(int page, int size, int userId) {
        List<Group> groups = groupMapper.selectGroupsByUser(page, page*size, userId);
        List<GroupDTO> groupDTOS = new LinkedList<>();
        for (Group g : groups) {
            GroupDTO dto = new GroupDTO();
            dto.setGroupName(g.getGroupName());
            dto.setGroupNote(g.getGroupNote());
            dto.setGroupBattleCount(g.getGroupBattleCount());
            dto.setGroupMemberCount(g.getGroupMemberCount());
            dto.setGroupUUID(converter.formatUUID(g.getGroupUUID()));
            groupDTOS.add(dto);
        }

        return Response.create(groupDTOS);
    }

    @Override
    public Response<GroupDTO> getGroup(String groupUUID, int userId) {
        Group group = groupMapper.selectGroupByUUID(converter.parseUUID(groupUUID));
        if (group == null || group.getUserId() != userId) {
            return Response.create(ResponseCode.unknownTarget, (GroupDTO) null);
        }

        GroupDTO dto = new GroupDTO();
        dto.setGroupName(group.getGroupName());
        dto.setGroupNote(group.getGroupNote());
        dto.setGroupInfo(group.getGroupInfo());
        dto.setGroupUUID(groupUUID);
        dto.setGroupBattleCount(group.getGroupBattleCount());
        dto.setGroupMemberCount(group.getGroupMemberCount());

        List<GroupMember> groupMembers = groupMapper.selectMembersByGroup(group.getGroupId());
        List<GroupMemberDTO> memberDTOS = new LinkedList<>();
        for (GroupMember gm : groupMembers) {
            Member member = memberMapper.selectMemberById(gm.getMemberId());
            GroupMemberDTO memberDTO = new GroupMemberDTO();
            memberDTO.setMemberName(member.getMemberName());
            memberDTO.setMemberUUID(converter.formatUUID(member.getMemberUUID()));
            memberDTO.setGroupMemberName(gm.getGroupMemberName());
            memberDTO.setGroupMemberNumber(gm.getGroupMemberNumber());
            memberDTO.setGroupMemberPoint(gm.getGroupMemberPoint());
            memberDTOS.add(memberDTO);
        }
        dto.setGroupMembers(memberDTOS);

        List<Battle> battles = groupMapper.selectBattlesByGroup(group.getGroupId());
        List<BattleDTO> battleDTOS = new LinkedList<>();
        for (Battle battle : battles) {
            BattleDTO battleDTO = new BattleDTO();
            battleDTO.setBattleUUID(converter.formatUUID(battle.getBattleUUID()));
            battleDTO.setBattleName(battle.getBattleName());
            battleDTO.setBattleEventCount(battle.getBattleEventCount());
            battleDTOS.add(battleDTO);
        }
        dto.setBattles(battleDTOS);

        User user = authMapper.selectUserById(userId);
        dto.setUserNo(converter.formatUserNo(user.getUserNo()));
        dto.setUserName(user.getUserName());

        return Response.create(dto);
    }

    @Override
    public Response<GroupMemberDTO> getGroupMember(String groupUUID, int groupMemberNumber, int userId) {
        Group group = groupMapper.selectGroupByUUID(converter.parseUUID(groupUUID));
        if (group == null || group.getUserId() != userId) {
            return Response.create(ResponseCode.unknownTarget, (GroupMemberDTO) null);
        }

        GroupMember groupMember = groupMapper.selectMemberByNumber(group.getGroupId(), groupMemberNumber);
        if (groupMember == null) {
            return Response.create(ResponseCode.unknownTarget, (GroupMemberDTO) null);
        }
        Member member = memberMapper.selectMemberById(groupMember.getMemberId());

        GroupMemberDTO dto = new GroupMemberDTO();
        dto.setMemberName(member.getMemberName());
        dto.setMemberUUID(converter.formatUUID(member.getMemberUUID()));
        dto.setGroupMemberName(groupMember.getGroupMemberName());
        dto.setGroupMemberPoint(groupMember.getGroupMemberPoint());
        dto.setGroupMemberNumber(groupMemberNumber);
        dto.setGroupName(group.getGroupName());
        dto.setGroupUUID(groupUUID);
        return Response.create(dto);
    }

}
