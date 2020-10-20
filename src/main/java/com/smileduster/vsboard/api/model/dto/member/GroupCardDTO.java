package com.smileduster.vsboard.api.model.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupCardDTO {

    private int memberNumber;

    private String groupNickname;

    private int groupPoint;

    private byte[] groupUUID;

    private byte[] memberUUID;

    private int groupId;

    private int memberId;

}
