package com.smileduster.vsboard.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BattleEventDTO {

    private String battleUUID;
    private int battleEventNumber;
    private String battleEventTitle;
    private String battleEventNote;

    private String battleEventResult;
    private String battleEventScore;

    private String battleEventStartTime;
    private String battleEventEndTime;

}
