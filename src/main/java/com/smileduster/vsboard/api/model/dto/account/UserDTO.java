package com.smileduster.vsboard.api.model.dto.account;

import com.smileduster.vsboard.api.model.po.account.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private int userId;

    private String userName;

    private String userPwd;

    private Set<Role> userRoles;

}
