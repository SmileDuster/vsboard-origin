package com.smileduster.vsboard.api.model.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BattleMember {

    private int battleId;

    private int groupMemberNumber;

    private int battleMemberScore;

    private int battleMemberReward;

}
