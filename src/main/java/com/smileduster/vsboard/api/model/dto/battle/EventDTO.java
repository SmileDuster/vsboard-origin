package com.smileduster.vsboard.api.model.dto.battle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {

    private int eventNumber;

    private Date eventTime;

    private String eventName;

    private String eventNote;

    private Map<Integer, Double> eventScores;

    private byte[] battleUUID;

}
