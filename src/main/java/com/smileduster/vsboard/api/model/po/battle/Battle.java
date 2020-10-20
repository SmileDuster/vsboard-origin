package com.smileduster.vsboard.api.model.po.battle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Battle {

    private int battleId;

    private byte[] battleUUID;

    private String battleName;

    private String battleNote;

    private int battleStatus;

    private Date battleCreateTime;

    private int userId;

    private int groupId;

}
