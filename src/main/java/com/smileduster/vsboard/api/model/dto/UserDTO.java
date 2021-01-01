package com.smileduster.vsboard.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smileduster.vsboard.api.model.po.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO {

    private String userName;

    private String userPwd;

    private byte[] userPwdSalt;

    private String userNo;

    private String userEmail;

    private Date userCreateTime;

    private Date userLoginTime;

    private String userLastIP;

    private Set<RoleDTO> userRoles;

}
