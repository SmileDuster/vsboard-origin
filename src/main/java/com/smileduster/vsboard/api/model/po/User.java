package com.smileduster.vsboard.api.model.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private int userId;

    private String userName;

    private String userPwd;

    private byte[] userPwdSalt;

    private long userNo;

    private String userEmail;

    private Date userCreateTime;

    private Date userLoginTime;

    private byte[] userLastIP;

}
