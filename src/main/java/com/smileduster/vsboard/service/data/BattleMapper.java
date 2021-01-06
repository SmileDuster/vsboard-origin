package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.po.Battle;
import com.smileduster.vsboard.api.model.po.BattleEvent;
import com.smileduster.vsboard.api.model.po.BattleMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BattleMapper {

    void insertBattle(Battle battle);

    Battle selectBattleByUUID(byte[] battleUUID);

    void deleteBattleById(int battleId);

    void insertEvent(BattleEvent event);

    void incEventCount(int battleId);

    void incEventNumber(int battleId);

    void deleteEventsByBattle(int battleId);

    void appendEventResult(int number, int result, int battleId);

    List<Battle> selectBattlesByUser(int page, int skip, int userId);

    BattleEvent selectEventByNumber(int battleId, int battleEventNumber);

    List<BattleEvent> selectEventsByBattle(int battleId);

    List<BattleMember> selectMembersByBattle(int battleId);

    BattleMember selectMemberByNumber(int battleId, int groupMemberNumber);

    void insertBattleMember(BattleMember battleMember);

    void deleteMembersByBattle(int battleId);
}
