package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.dto.GroupDTO;
import com.smileduster.vsboard.api.model.dto.GroupMemberDTO;
import com.smileduster.vsboard.api.service.IGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {

    @Override
    public Response<?> createGroup(GroupDTO dto, int userId) {
        return null;
    }

    @Override
    public Response<?> deleteGroup(String groupUUID, int userId) {
        return null;
    }

    @Override
    public Response<?> joinGroup(GroupMemberDTO dto, int userId) {
        return null;
    }

    @Override
    public Response<List<GroupDTO>> getGroups(int page, int size, int userId) {
        return null;
    }

    @Override
    public Response<GroupDTO> getGroup(String groupUUID, int userId) {
        return null;
    }

    @Override
    public Response<GroupMemberDTO> getGroupMember(String groupUUID, int groupMemberNumber, int userId) {
        return null;
    }

}
