package com.smileduster.vsboard.api.service;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.dto.GroupDTO;
import com.smileduster.vsboard.api.model.dto.GroupMemberDTO;

import java.util.List;

public interface IGroupService {

    Response<?> createGroup(GroupDTO dto, int userId);

    Response<?> deleteGroup(String groupUUID, int userId);

    Response<?> joinGroup(GroupMemberDTO dto, int userId);

    Response<List<GroupDTO>> getGroups(int page, int size, int userId);

    Response<GroupDTO> getGroup(String groupUUID, int userId);

    Response<GroupMemberDTO> getGroupMember(String groupUUID, int groupMemberNumber, int userId);

}
