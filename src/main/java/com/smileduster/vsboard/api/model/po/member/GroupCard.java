package com.smileduster.vsboard.api.model.po.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupCard {

    private int groupId;

    private int memberNumber;

    private String groupNickname;

    private int groupPoint;

}