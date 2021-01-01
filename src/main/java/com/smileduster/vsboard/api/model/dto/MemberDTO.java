package com.smileduster.vsboard.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MemberDTO {

    private String memberUUID;

    private String memberName;

    private String memberNote;

    private String memberInfo;

    private String userNo;
    private String userName;

    private List<GroupMemberDTO> groupMembers;

}
