package com.smileduster.vsboard.api.service.battle;

import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.battle.BattleDTO;
import com.smileduster.vsboard.api.model.dto.battle.BattleLogDTO;
import com.smileduster.vsboard.api.model.dto.battle.EventDTO;

import java.util.List;

public interface IBattleService {

    ResponseCode add(BattleDTO dto);

    ResponseCode remove(int bid);

    ResponseCode updateInfo(BattleDTO dto);

    ResponseCode updateStatus(int bid, int status);

    ResponseCode addEvent(EventDTO dto);

    List<BattleDTO> getByUser(int uid, int page, int size);

    List<BattleDTO> getByName(String name, int page, int size);

    List<BattleDTO> getByNameAndUser(int uid, String name, int page, int size);

    BattleDTO getById(int bid);

    BattleLogDTO getLogById(int bid);

}
