package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.po.Battle;
import com.smileduster.vsboard.api.model.po.BattleEvent;
import com.smileduster.vsboard.api.model.po.BattleMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BattleMapper {

    void insertBattle(Battle battle);
    void insertBattleMember(BattleMember battleMember);

    void insertEvent(BattleEvent event);
    void incEventCount(int battleId);
    void incEventNumber(int battleId);
    void appendEventResult(int number, int result, int battleId);

    void deleteBattleById(int battleId);
    void deleteMembersByBattle(int battleId);
    void deleteEventsByBattle(int battleId);

    Battle selectBattleByUUID(byte[] battleUUID);
    BattleEvent selectEventByNumber(int battleId, int battleEventNumber);
    BattleMember selectMemberByNumber(int battleId, int groupMemberNumber);
    List<Battle> selectBattlesByUser(int page, int skip, int userId);
    List<BattleEvent> selectEventsByBattle(int battleId);
    List<BattleMember> selectMembersByBattle(int battleId);

}
