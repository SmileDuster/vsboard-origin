package com.smileduster.vsboard.web.form;

import lombok.Data;

@Data
public class CreateBattleForm {

    private String battleName;
    private String battleNote;
    private String battleInfo;

    private String battleBeginTime;
    private String battleEndTime;

    private String groupUUID;

}
