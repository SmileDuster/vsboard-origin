package com.smileduster.vsboard.api.model.po;

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

    private String battleInfo;

    private int battleEventCount;

    private Date battleCreateTime;

    private Date battleBeginTime;

    private Date battleEndTime;

    private int userId;

    private int groupId;

}
