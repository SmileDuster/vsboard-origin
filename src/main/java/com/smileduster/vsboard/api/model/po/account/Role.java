package com.smileduster.vsboard.api.model.po.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Role {

    private int roleId;

    private String roleName;

    private Set<Perm> rolePerms;

    private Date roleAttachTime;

}
