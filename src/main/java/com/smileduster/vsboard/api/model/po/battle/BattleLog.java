package com.smileduster.vsboard.api.model.po.battle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BattleLog {

    private int battleId;

    private int eventCount;

    private List<Event> battleEvents;

    private List<BattleCard> battleMembers;

}
