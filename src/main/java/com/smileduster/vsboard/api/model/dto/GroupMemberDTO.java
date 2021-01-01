package com.smileduster.vsboard.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GroupMemberDTO {

    private String groupName;
    private String groupUUID;

    private int groupMemberNumber;

    private String memberName;
    private String memberUUID;

    private String groupMemberName;

    private int groupMemberPoint;

}
