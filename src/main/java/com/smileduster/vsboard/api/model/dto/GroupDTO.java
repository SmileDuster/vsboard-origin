package com.smileduster.vsboard.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smileduster.vsboard.api.model.po.GroupMember;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GroupDTO {

    private String groupUUID;

    private String groupName;

    private String groupNote;

    private String groupInfo;

    private int groupBattleCount;
    private int groupMemberCount;
    private int groupMemberNumber;

    private List<GroupMemberDTO> groupMembers;
    private List<BattleDTO> battles;

    private String userNo;
    private String userName;

}
