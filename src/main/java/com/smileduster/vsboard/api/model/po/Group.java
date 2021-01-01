package com.smileduster.vsboard.api.model.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Group {

    private int groupId;

    private byte[] groupUUID;

    private String groupName;

    private String groupNote;

    private String groupInfo;

    private int groupBattleCount;

    private int groupMemberCount;

    private int groupMemberNumber;

    private int userId;

}
