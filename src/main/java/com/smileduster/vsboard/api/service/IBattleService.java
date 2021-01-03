package com.smileduster.vsboard.api.service;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.dto.BattleDTO;
import com.smileduster.vsboard.api.model.dto.BattleEventDTO;
import com.smileduster.vsboard.api.model.dto.BattleMemberDTO;

import java.util.List;

public interface IBattleService {

    Response<?> createBattle(BattleDTO dto, int userId);

    Response<?> deleteBattle(String battleUUID, int userId);

    Response<?> finishBattle(String battleUUID, int userId);

    Response<?> createBattleEvent(BattleEventDTO dto, int userId);

    Response<List<BattleDTO>> getBattles(int page, int size, int userId);

    Response<BattleDTO> getBattle(String battleUUID, int userId);

    Response<BattleMemberDTO> getBattleMember(String battleUUID, int groupMemberNumber, int userId);

    Response<BattleEventDTO> getEvent(String battleUUID, int battleEventNumber, int userId);

}
