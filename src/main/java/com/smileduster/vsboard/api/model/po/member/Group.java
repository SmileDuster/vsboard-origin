package com.smileduster.vsboard.api.model.po.member;

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

    private List<GroupCard> groupMembers;

    private int groupStatus;

    private Date groupCreateTime;

    private int userId;

}
