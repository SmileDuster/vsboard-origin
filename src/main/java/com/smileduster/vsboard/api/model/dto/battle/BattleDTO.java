package com.smileduster.vsboard.api.model.dto.battle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BattleDTO {

    private int battleId;

    private byte[] battleUUID;

    private String battleName;

    private String battleNote;

    private int battleStatus;

    private int userId;

    private int groupId;

    private byte[] groupUUID;
    
    private String userName;

}
