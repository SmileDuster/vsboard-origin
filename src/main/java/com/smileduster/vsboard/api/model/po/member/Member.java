package com.smileduster.vsboard.api.model.po.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Member {

    private int memberId;

    private byte[] memberUUID;

    private String memberName;

    private String memberNote;

    private Date memberCreateTime;

    private int userId;

    private List<GroupCard> memberGroups;

}
