package com.smileduster.vsboard.api.model.dto.member;

import com.smileduster.vsboard.api.model.po.member.GroupCard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GroupDTO {

    private int groupId;

    private byte[] groupUUID;

    private String groupName;

    private String groupNote;

    private List<GroupCard> groupMembers;

    private int groupStatus;

    private int userId;

    private String userName;

}
