package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.GroupMemberDTO;
import com.smileduster.vsboard.api.model.dto.MemberDTO;
import com.smileduster.vsboard.api.model.po.Group;
import com.smileduster.vsboard.api.model.po.GroupMember;
import com.smileduster.vsboard.api.model.po.Member;
import com.smileduster.vsboard.api.model.po.User;
import com.smileduster.vsboard.api.service.IMemberService;
import com.smileduster.vsboard.api.tools.Converter;
import com.smileduster.vsboard.api.tools.Generator;
import com.smileduster.vsboard.service.data.AuthMapper;
import com.smileduster.vsboard.service.data.GroupMapper;
import com.smileduster.vsboard.service.data.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MemberServiceImpl implements IMemberService {

    private final AuthMapper authMapper;
    private final MemberMapper memberMapper;
    private final GroupMapper groupMapper;
    private final Generator generator;
    private final Converter converter;

    public MemberServiceImpl(AuthMapper authMapper,
                             MemberMapper memberMapper,
                             GroupMapper groupMapper,
                             Generator generator,
                             Converter converter) {
        this.authMapper = authMapper;
        this.memberMapper = memberMapper;
        this.groupMapper = groupMapper;
        this.generator = generator;
        this.converter = converter;
    }

    @Override
    public Response<?> createMember(MemberDTO dto, int userId) {
        Member member = new Member();
        member.setMemberName(dto.getMemberName());
        member.setMemberNote(dto.getMemberNote());
        member.setMemberInfo(dto.getMemberInfo());
        member.setMemberUUID(generator.getUUID());
        member.setUserId(userId);
        memberMapper.insertMember(member);
        return Response.create(converter.formatUUID(member.getMemberUUID()));
    }

    //fixme check delete
    @Override
    public Response<?> deleteMember(String memberUUID, int userId) {
        Member member = memberMapper.selectMemberByUUID(converter.parseUUID(memberUUID));
        if (member == null || member.getUserId() != userId) {
            return Response.create();
        }

        memberMapper.deleteMemberById(member.getMemberId());
        return Response.create();
    }

    @Override
    public Response<List<MemberDTO>> getMembers(int page, int size, int userId) {
        List<Member> members = memberMapper.selectMembersByUser(page, page*size, userId);
        List<MemberDTO> memberDTOS = new LinkedList<>();
        for (Member m : members) {
            MemberDTO dto = new MemberDTO();
            dto.setMemberName(m.getMemberName());
            dto.setMemberNote(m.getMemberNote());
            dto.setMemberUUID(converter.formatUUID(m.getMemberUUID()));
            memberDTOS.add(dto);
        }
        return Response.create(memberDTOS);
    }

    @Override
    public Response<MemberDTO> getMember(String memberUUID, int userId) {
        Member member = memberMapper.selectMemberByUUID(converter.parseUUID(memberUUID));
        if (member == null || member.getUserId() != userId) {
            return Response.create(ResponseCode.unknownTarget, (MemberDTO) null);
        }

        MemberDTO dto = new MemberDTO();
        dto.setMemberName(member.getMemberName());
        dto.setMemberNote(member.getMemberNote());
        dto.setMemberInfo(member.getMemberInfo());
        dto.setMemberUUID(converter.formatUUID(member.getMemberUUID()));

        List<GroupMember> groupMembers = groupMapper.selectMembersByMember(member.getMemberId());
        List<GroupMemberDTO> memberDTOS = new LinkedList<>();
        for (GroupMember gm : groupMembers) {
            Group group = groupMapper.selectGroupById(gm.getGroupId());
            GroupMemberDTO memberDTO = new GroupMemberDTO();
            memberDTO.setGroupUUID(converter.formatUUID(group.getGroupUUID()));
            memberDTO.setGroupName(group.getGroupName());
            memberDTO.setGroupMemberNumber(gm.getGroupMemberNumber());
            memberDTO.setGroupMemberName(gm.getGroupMemberName());
            memberDTO.setGroupMemberPoint(gm.getGroupMemberPoint());
            memberDTOS.add(memberDTO);
        }
        dto.setGroupMembers(memberDTOS);

        User user = authMapper.selectUserById(userId);
        dto.setUserNo(converter.formatUserNo(user.getUserNo()));
        dto.setUserName(dto.getUserName());

        return Response.create(dto);
    }

}
