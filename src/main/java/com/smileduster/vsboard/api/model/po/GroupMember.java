package com.smileduster.vsboard.api.model.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupMember {

    private int groupId;

    private int groupMemberNumber;

    private int memberId;

    private String groupMemberName;

    private int groupMemberPoint;

}
