package com.smileduster.vsboard.api.model.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BattleEvent {

    private int battleId;

    private int battleEventNumber;

    private String battleEventTitle;

    private String battleEventNote;

    private String battleEventResult;

    private String battleEventScore;

    private Date battleEventStartTime;

    private Date battleEventEndTime;

}
