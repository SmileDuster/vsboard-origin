package com.smileduster.vsboard.api.model.po.account;

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

    private Set<Role> userRoles;

    private Date userCreateTime;

    private Date userLoginTime;

    private byte[] userLastIP;

}
