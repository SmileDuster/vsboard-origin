package com.smileduster.vsboard.web.form;

import lombok.Data;

@Data
public class CreateBattleEventForm {

    private String battleEventTitle;
    private String battleEventNote;
    private String battleEventResult;
    private String battleEventScore;

    private String battleEventStartTime;
    private String battleEventEndTime;

    private String battleUUID;
}
