package com.smileduster.vsboard.api.model.dto.battle;

import com.smileduster.vsboard.api.model.po.battle.BattleCard;
import com.smileduster.vsboard.api.model.po.battle.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BattleLogDTO {

    private int battleId;

    private List<Event> battleEvents;

    private List<BattleCard> battleMembers;

    private byte[] battleUUID;

}
