package com.smileduster.vsboard.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BattleDTO {

    private String battleUUID;
    private String battleName;
    private String battleNote;
    private String battleInfo;

    private String lastEventTitle;
    private int battleEventCount;
    private String battleCreateTime;
    private String battleBeginTime;
    private String battleEndTime;

    private String userNo;
    private String userName;
    private String groupUUID;
    private String groupName;

    private List<BattleMemberDTO> battleMemberList;
    private List<BattleEventDTO> battleEventList;

}
