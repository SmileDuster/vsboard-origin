package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.dto.battle.BattleDTO;
import com.smileduster.vsboard.api.model.po.battle.Battle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BattleMapper {

    List<Battle> selectBattle(int uid, int gid, String name, int page, int skip);
    Battle selectBattleById(int bid);

    void updateBattleInfo(BattleDTO dto);
    boolean updateBattleStatus(int bid, int status);

    void deleteBattleById(int bid);

    void insertBattle(BattleDTO dto);

}
