package com.smileduster.vsboard.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BattleMemberDTO {

    private String battleUUID;

    private int groupMemberNumber;

    private int battleMemberScore;

    private int battleMemberReward;

}
