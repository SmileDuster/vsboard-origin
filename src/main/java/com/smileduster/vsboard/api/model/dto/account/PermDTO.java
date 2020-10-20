package com.smileduster.vsboard.api.model.dto.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PermDTO {

    private int permId;

    private String permName;

    private int permStatus;

}
