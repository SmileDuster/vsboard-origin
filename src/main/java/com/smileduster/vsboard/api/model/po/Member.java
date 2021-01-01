package com.smileduster.vsboard.api.model.po;

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

    private String memberInfo;

    private int userId;

}
