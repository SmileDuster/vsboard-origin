package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.BattleDTO;
import com.smileduster.vsboard.api.model.dto.BattleEventDTO;
import com.smileduster.vsboard.api.model.dto.BattleMemberDTO;
import com.smileduster.vsboard.api.model.po.*;
import com.smileduster.vsboard.api.service.IBattleService;
import com.smileduster.vsboard.api.tools.Converter;
import com.smileduster.vsboard.api.tools.Generator;
import com.smileduster.vsboard.service.data.AuthMapper;
import com.smileduster.vsboard.service.data.BattleMapper;
import com.smileduster.vsboard.service.data.GroupMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BattleServiceImpl implements IBattleService {

    private final BattleMapper battleMapper;
    private final GroupMapper groupMapper;
    private final Generator generator;
    private final Converter converter;
    private final AuthMapper authMapper;

    public BattleServiceImpl(BattleMapper battleMapper,
                             GroupMapper groupMapper,
                             Generator generator,
                             Converter converter,
                             AuthMapper authMapper) {
        this.battleMapper = battleMapper;
        this.groupMapper = groupMapper;
        this.generator = generator;
        this.converter = converter;
        this.authMapper = authMapper;
    }

    @Override
    public Response<?> createBattle(BattleDTO dto, int userId) {
        Group group = groupMapper.selectGroupByUUID(converter.parseUUID(dto.getGroupUUID()));
        if (group == null || group.getUserId() != userId) {
            return Response.create(ResponseCode.illegalGroup, dto.getGroupUUID());
        }
        groupMapper.incBattleCount(group.getGroupId());

        Battle battle = new Battle();
        battle.setBattleName(dto.getBattleName());
        battle.setBattleNote(dto.getBattleNote());
        battle.setBattleInfo(dto.getBattleInfo());
        battle.setBattleUUID(generator.getUUID());
        battle.setBattleCreateTime(new Date());
        battle.setBattleBeginTime(converter.parseDate(dto.getBattleBeginTime()));
        battle.setBattleEndTime(converter.parseDate(dto.getBattleEndTime()));
        battle.setBattleEventCount(0);
        battle.setGroupId(group.getGroupId());
        battle.setUserId(userId);
        battleMapper.insertBattle(battle);
        return Response.create(converter.formatUUID(battle.getBattleUUID()));
    }

    @Override
    public Response<?> deleteBattle(String battleUUID, int userId) {
        Battle battle = battleMapper.selectBattleByUUID(converter.parseUUID(battleUUID));
        if (battle == null || battle.getUserId() != userId) {
            return Response.create();
        }
        Group group = groupMapper.selectGroupById(battle.getGroupId());
        if (group != null) {
            groupMapper.decBattleCount(group.getGroupId());
        }
        battleMapper.deleteMembersByBattle(battle.getBattleId());
        battleMapper.deleteEventsByBattle(battle.getBattleId());
        battleMapper.deleteBattleById(battle.getBattleId());
        return Response.create();
    }

    @Override
    public Response<?> finishBattle(String battleUUID, int userId) {
        return Response.todo();
    }

    @Override
    public Response<?> createBattleEvent(BattleEventDTO dto, int userId) {
        Battle battle = battleMapper.selectBattleByUUID(converter.parseUUID(dto.getBattleUUID()));
        if (battle == null || battle.getUserId() != userId) {
            return Response.create(ResponseCode.illegalBattle, dto.getBattleUUID());
        }

        int number = battle.getBattleEventNumber()+1;
        BattleEvent event = new BattleEvent();
        event.setBattleEventTitle(dto.getBattleEventTitle());
        event.setBattleEventNote(dto.getBattleEventNote());
        event.setBattleEventNumber(number);
        event.setBattleEventScore(dto.getBattleEventScore());
        event.setBattleEventResult(dto.getBattleEventResult());
        event.setBattleEventStartTime(converter.parseDate(dto.getBattleEventStartTime()));
        event.setBattleEventEndTime(converter.parseDate(dto.getBattleEventEndTime()));
        battleMapper.insertEvent(event);
        battleMapper.incEventNumber(battle.getBattleId());
        battleMapper.incEventCount(battle.getBattleId());

        Map<Integer, Integer> map = converter.parseDataString(event.getBattleEventResult());
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            BattleMember bm = battleMapper.selectMemberByNumber(battle.getBattleId(), e.getKey());
            if (bm == null) {
                bm = new BattleMember();
                bm.setBattleId(battle.getBattleId());
                bm.setGroupMemberNumber(e.getKey());
                bm.setBattleMemberReward(0);
                bm.setBattleMemberScore(e.getValue());
                battleMapper.insertBattleMember(bm);
            } else {
                battleMapper.appendEventResult(e.getKey(), e.getValue(), battle.getBattleId());
            }
        }
        return Response.create();
    }

    @Override
    public Response<List<BattleDTO>> getBattles(int page, int size, int userId) {
        List<BattleDTO> list = new LinkedList<>();
        List<Battle> battles = battleMapper.selectBattlesByUser(page, page*size, userId);
        for (Battle b : battles) {
            BattleDTO dto = new BattleDTO();
            dto.setBattleName(b.getBattleName());
            dto.setBattleNote(b.getBattleNote());
            dto.setBattleUUID(converter.formatUUID(b.getBattleUUID()));
            dto.setBattleCreateTime(converter.formatDate(b.getBattleCreateTime()));
            dto.setBattleBeginTime(converter.formatDate(b.getBattleBeginTime()));
            dto.setBattleEndTime(converter.formatDate(b.getBattleEndTime()));
            dto.setBattleEventCount(b.getBattleEventCount());

            BattleEvent event = battleMapper.selectEventByNumber(b.getBattleId(), b.getBattleEventNumber());
            dto.setLastEventTitle(event.getBattleEventTitle());

            list.add(dto);
        }
        return Response.create(list);
    }

    @Override
    public Response<BattleDTO> getBattle(String battleUUID, int userId) {
        Battle battle = battleMapper.selectBattleByUUID(converter.parseUUID(battleUUID));
        if (battle == null || battle.getUserId() != userId) {
            return Response.create(ResponseCode.unknownTarget, (BattleDTO) null);
        }

        BattleDTO dto = new BattleDTO();
        dto.setBattleName(battle.getBattleName());
        dto.setBattleNote(battle.getBattleNote());
        dto.setBattleInfo(battle.getBattleInfo());
        dto.setBattleCreateTime(converter.formatDate(battle.getBattleCreateTime()));
        dto.setBattleBeginTime(converter.formatDate(battle.getBattleBeginTime()));
        dto.setBattleEndTime(converter.formatDate(battle.getBattleEndTime()));
        dto.setBattleUUID(converter.formatUUID(battle.getBattleUUID()));
        dto.setBattleEventCount(battle.getBattleEventCount());

        Group group = groupMapper.selectGroupById(battle.getGroupId());
        dto.setGroupUUID(converter.formatUUID(group.getGroupUUID()));
        dto.setGroupName(group.getGroupName());

        User user = authMapper.selectUserById(battle.getUserId());
        dto.setUserNo(converter.formatUserNo(user.getUserNo()));
        dto.setUserName(user.getUserName());

        List<BattleEvent> events = battleMapper.selectEventsByBattle(battle.getBattleId());
        List<BattleEventDTO> eventDTOS = new LinkedList<>();
        for (BattleEvent event : events) {
            BattleEventDTO eventDTO = new BattleEventDTO();
            eventDTO.setBattleEventTitle(event.getBattleEventTitle());
            eventDTO.setBattleEventNumber(event.getBattleEventNumber());
            eventDTO.setBattleEventStartTime(converter.formatDate(event.getBattleEventStartTime()));
            eventDTOS.add(eventDTO);
        }
        dto.setBattleEventList(eventDTOS);

        List<BattleMember> battleMembers = battleMapper.selectMembersByBattle(battle.getBattleId());
        List<Integer> memberNumbers = new LinkedList<>();
        for (BattleMember bm : battleMembers) {
            memberNumbers.add(bm.getGroupMemberNumber());
        }
        List<GroupMember> groupMembers = groupMapper.selectMembersIn(group.getGroupId(), memberNumbers);

        List<BattleMemberDTO> memberDTOS = new LinkedList<>();
        for (int i = 0; i < battleMembers.size(); i++) {
            BattleMember bm = battleMembers.get(i);
            GroupMember gm = groupMembers.get(i);

            BattleMemberDTO battleMemberDTO = new BattleMemberDTO();
            battleMemberDTO.setGroupMemberNumber(bm.getGroupMemberNumber());
            battleMemberDTO.setGroupMemberName(gm.getGroupMemberName());
            battleMemberDTO.setBattleMemberScore(bm.getBattleMemberScore());
            battleMemberDTO.setBattleMemberReward(bm.getBattleMemberReward());
            memberDTOS.add(battleMemberDTO);
        }
        dto.setBattleMemberList(memberDTOS);

        return Response.create(dto);
    }

    @Override
    public Response<BattleMemberDTO> getBattleMember(String battleUUID, int groupMemberNumber, int userId) {
        Battle battle = battleMapper.selectBattleByUUID(converter.parseUUID(battleUUID));
        if (battle == null || battle.getUserId() != userId) {
            return Response.create(ResponseCode.unknownTarget, (BattleMemberDTO) null);
        }

        BattleMember bm = battleMapper.selectMemberByNumber(battle.getBattleId(), groupMemberNumber);
        GroupMember gm = groupMapper.selectMemberByNumber(battle.getGroupId(), groupMemberNumber);
        if (bm == null || gm == null) {
            return Response.create(ResponseCode.unknownTarget, (BattleMemberDTO) null);
        }

        BattleMemberDTO dto = new BattleMemberDTO();
        dto.setGroupMemberName(gm.getGroupMemberName());
        dto.setGroupMemberNumber(groupMemberNumber);
        dto.setBattleMemberScore(bm.getBattleMemberScore());
        dto.setBattleMemberReward(bm.getBattleMemberReward());

        return Response.create(dto);
    }

    @Override
    public Response<BattleEventDTO> getEvent(String battleUUID, int battleEventNumber, int userId) {
        Battle battle = battleMapper.selectBattleByUUID(converter.parseUUID(battleUUID));
        if (battle == null || battle.getUserId() != userId) {
            return Response.create(ResponseCode.unknownTarget, (BattleEventDTO) null);
        }
        BattleEvent event = battleMapper.selectEventByNumber(battle.getBattleId(), battleEventNumber);
        if (event == null) {
            return Response.create(ResponseCode.unknownTarget, (BattleEventDTO) null);
        }

        BattleEventDTO dto = new BattleEventDTO();
        dto.setBattleEventTitle(event.getBattleEventTitle());
        dto.setBattleEventNote(event.getBattleEventNote());
        dto.setBattleEventStartTime(converter.formatDate(event.getBattleEventStartTime()));
        dto.setBattleEventEndTime(converter.formatDate(event.getBattleEventEndTime()));
        dto.setBattleEventNumber(battleEventNumber);
        dto.setBattleEventScore(event.getBattleEventScore());
        dto.setBattleEventResult(event.getBattleEventResult());

        return Response.create(dto);
    }

}
