package com.smileduster.vsboard.api.model.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {

    private int memberId;

    private byte[] memberUUID;

    private String memberName;

    private String memberNote;

    private int userId;

    private List<GroupCardDTO> memberGroups;

    private String userName;

}
