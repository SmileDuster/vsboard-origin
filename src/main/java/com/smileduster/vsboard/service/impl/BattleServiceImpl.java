package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.dto.BattleDTO;
import com.smileduster.vsboard.api.model.dto.BattleEventDTO;
import com.smileduster.vsboard.api.model.dto.BattleMemberDTO;
import com.smileduster.vsboard.api.service.IBattleService;
import com.smileduster.vsboard.service.data.BattleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BattleServiceImpl implements IBattleService {

    private final BattleMapper battleMapper;

    public BattleServiceImpl(BattleMapper battleMapper) {
        this.battleMapper = battleMapper;
    }

    @Override
    public Response<?> createBattle(BattleDTO dto, int userId) {
        return null;
    }

    @Override
    public Response<?> deleteBattle(String battleUUID, int userId) {
        return null;
    }

    @Override
    public Response<?> finishBattle(String battleUUID, int userId) {
        return null;
    }

    @Override
    public Response<?> createBattleEvent(BattleEventDTO dto, int userId) {
        return null;
    }

    @Override
    public Response<List<BattleDTO>> getBattles(int page, int size, int userId) {
        return null;
    }

    @Override
    public Response<BattleDTO> getBattle(String battleUUID, int userId) {
        return null;
    }

    @Override
    public Response<BattleMemberDTO> getBattleMember(String battleUUID, int battleMemberNumber, int userId) {
        return null;
    }

    @Override
    public Response<BattleEventDTO> getEvent(String battleUUID, int battleEventNumber, int userId) {
        return null;
    }

}
