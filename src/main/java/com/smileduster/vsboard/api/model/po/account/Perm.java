package com.smileduster.vsboard.api.model.po.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Perm {

    private int permId;

    private String permName;

    private Date permAttachTime;
}
