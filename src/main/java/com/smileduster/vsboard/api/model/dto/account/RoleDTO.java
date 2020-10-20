package com.smileduster.vsboard.api.model.dto.account;

import com.smileduster.vsboard.api.model.po.account.Perm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RoleDTO {

    private int roleId;

    private String roleName;

    private int roleStatus;

    private Set<Perm> rolePerms;

}
