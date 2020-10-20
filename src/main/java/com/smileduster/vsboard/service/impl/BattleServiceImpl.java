package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.exception.UnknownIdException;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.battle.BattleDTO;
import com.smileduster.vsboard.api.model.dto.battle.BattleLogDTO;
import com.smileduster.vsboard.api.model.dto.battle.EventDTO;
import com.smileduster.vsboard.api.model.po.battle.Battle;
import com.smileduster.vsboard.api.model.po.battle.BattleLog;
import com.smileduster.vsboard.api.model.po.battle.Event;
import com.smileduster.vsboard.api.service.battle.IBattleService;
import com.smileduster.vsboard.service.data.BattleLogRepository;
import com.smileduster.vsboard.service.data.BattleMapper;
import com.smileduster.vsboard.service.data.IdMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BattleServiceImpl implements IBattleService {

    private final BattleLogRepository logRepository;
    private final BattleMapper battleMapper;
    private final IdMapper idMapper;

    public BattleServiceImpl(BattleLogRepository logRepository, BattleMapper battleMapper, IdMapper idMapper) {
        this.logRepository = logRepository;
        this.battleMapper = battleMapper;
        this.idMapper = idMapper;
    }

    @Override
    public ResponseCode add(BattleDTO dto) {
        battleMapper.insertBattle(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode remove(int bid) {
        battleMapper.deleteBattleById(bid);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode updateInfo(BattleDTO dto) {
        battleMapper.updateBattleInfo(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode updateStatus(int bid, int status) {
        if (!battleMapper.updateBattleStatus(bid, status)) {
            throw new UnknownIdException(bid);
        }
        return ResponseCode.success;
    }

    @Override
    public ResponseCode addEvent(EventDTO dto) {
        Optional<BattleLog> optional = logRepository.findById(idMapper.selectBattleId(dto.getBattleUUID()));
        if (optional.isPresent()){
            BattleLog battleLog = optional.get();
            battleLog.setEventCount(battleLog.getEventCount());
            Event event = new Event();
            event.setEventNumber(battleLog.getEventCount());
            event.setEventName(dto.getEventName());
            event.setEventNote(dto.getEventNote());
            event.setEventTime(dto.getEventTime());
            event.setEventScores(dto.getEventScores());
            List<Event> events = battleLog.getBattleEvents();
            events.add(event);
            battleLog.setBattleEvents(events);
            logRepository.save(battleLog);
        } else {
            throw new UnknownIdException(dto.getBattleUUID());
        }
        return ResponseCode.success;
    }

    @Override
    public List<BattleDTO> getByUser(int uid, int page, int size) {
        List<Battle> battles = battleMapper.selectBattle(uid, -1, "", page, page*size);
        List<BattleDTO> battleDTOS = new LinkedList<>();
        for (Battle battle : battles) {
            BattleDTO battleDTO = new BattleDTO();
            battleDTO.setBattleName(battle.getBattleName());
            battleDTO.setBattleNote(battle.getBattleNote());
            battleDTO.setBattleStatus(battle.getBattleStatus());
            battleDTO.setBattleUUID(battle.getBattleUUID());
            battleDTO.setGroupUUID(idMapper.selectGroupUUID(battle.getGroupId()));
            battleDTOS.add(battleDTO);
        }
        return battleDTOS;
    }

    @Override
    public List<BattleDTO> getByName(String name, int page, int size) {
        List<Battle> battles = battleMapper.selectBattle(-1, -1, name, page, page*size);
        List<BattleDTO> battleDTOS = new LinkedList<>();
        for (Battle battle : battles) {
            BattleDTO battleDTO = new BattleDTO();
            battleDTO.setBattleName(battle.getBattleName());
            battleDTO.setBattleNote(battle.getBattleNote());
            battleDTO.setBattleStatus(battle.getBattleStatus());
            battleDTO.setBattleUUID(battle.getBattleUUID());
            battleDTO.setGroupUUID(idMapper.selectGroupUUID(battle.getGroupId()));
            battleDTO.setUserName(idMapper.selectUserName(battle.getUserId()));
            battleDTOS.add(battleDTO);
        }
        return battleDTOS;
    }

    @Override
    public List<BattleDTO> getByNameAndUser(int uid, String name, int page, int size) {
        List<Battle> battles = battleMapper.selectBattle(uid, -1, name, page, page*size);
        List<BattleDTO> battleDTOS = new LinkedList<>();
        for (Battle battle : battles) {
            BattleDTO battleDTO = new BattleDTO();
            battleDTO.setBattleName(battle.getBattleName());
            battleDTO.setBattleNote(battle.getBattleNote());
            battleDTO.setBattleStatus(battle.getBattleStatus());
            battleDTO.setBattleUUID(battle.getBattleUUID());
            battleDTO.setGroupUUID(idMapper.selectGroupUUID(battle.getGroupId()));
            battleDTOS.add(battleDTO);
        }
        return battleDTOS;
    }

    @Override
    public BattleDTO getById(int bid) {
        Battle battle = battleMapper.selectBattleById(bid);
        BattleDTO battleDTO = new BattleDTO();
        battleDTO.setBattleName(battle.getBattleName());
        battleDTO.setBattleNote(battle.getBattleNote());
        battleDTO.setBattleUUID(battle.getBattleUUID());
        battleDTO.setGroupUUID(idMapper.selectGroupUUID(battle.getGroupId()));
        battleDTO.setBattleStatus(battle.getBattleStatus());
        battleDTO.setUserName(idMapper.selectUserName(battle.getUserId()));
        return battleDTO;
    }

    @Override
    public BattleLogDTO getLogById(int bid) {
        Optional<BattleLog> optional = logRepository.findById(bid);
        if (optional.isPresent()){
            BattleLog battleLog = optional.get();
            BattleLogDTO battleLogDTO = new BattleLogDTO();
            battleLogDTO.setBattleEvents(battleLog.getBattleEvents());
            battleLogDTO.setBattleMembers(battleLog.getBattleMembers());
            return battleLogDTO;
        } else {
            throw new UnknownIdException(bid);
        }
    }

}
