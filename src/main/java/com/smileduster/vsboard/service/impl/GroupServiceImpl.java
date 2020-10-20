package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.exception.UnknownIdException;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.member.GroupCardDTO;
import com.smileduster.vsboard.api.model.dto.member.GroupDTO;
import com.smileduster.vsboard.api.model.po.member.Group;
import com.smileduster.vsboard.api.service.member.IGroupService;
import com.smileduster.vsboard.service.data.GroupMapper;
import com.smileduster.vsboard.service.data.IdMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {

    private final GroupMapper groupMapper;
    private final IdMapper idMapper;

    public GroupServiceImpl(GroupMapper groupMapper, IdMapper idMapper) {
        this.groupMapper = groupMapper;
        this.idMapper = idMapper;
    }

    @Override
    public ResponseCode add(GroupDTO dto) {
        groupMapper.insertGroup(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode remove(int gid) {
        groupMapper.deleteGroupById(gid);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode updateInfo(GroupDTO dto) {
        groupMapper.updateGroupInfo(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode updateStatus(int gid, int status) {
        if (!groupMapper.updateGroupStatus(gid, status)) {
            throw new UnknownIdException(gid);
        }
        return ResponseCode.success;
    }

    @Override
    public ResponseCode addMember(GroupCardDTO dto) {
        groupMapper.insertGroupCard(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode updateMember(GroupCardDTO dto) {
        groupMapper.updateGroupCardInfo(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode removeMember(int gid, int mid) {
        groupMapper.deleteGroupCardById(gid, mid);
        return ResponseCode.success;
    }

    @Override
    public List<GroupDTO> getByUser(int uid, int page, int size) {
        List<Group> groups = groupMapper.selectGroup(uid, "", page, page*size);
        List<GroupDTO> dtos = new LinkedList<>();
        for (Group group : groups) {
            GroupDTO dto = new GroupDTO();
            dto.setGroupUUID(group.getGroupUUID());
            dto.setGroupName(group.getGroupName());
            dto.setGroupStatus(group.getGroupStatus());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<GroupDTO> getByName(String name, int page, int size) {
        List<Group> groups = groupMapper.selectGroup(-1, name, page, page*size);
        List<GroupDTO> dtos = new LinkedList<>();
        for (Group group : groups) {
            GroupDTO dto = new GroupDTO();
            dto.setGroupUUID(group.getGroupUUID());
            dto.setGroupName(group.getGroupName());
            dto.setGroupStatus(group.getGroupStatus());
            dto.setUserName(idMapper.selectUserName(group.getUserId()));
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<GroupDTO> getByUserAndName(int uid, String name, int page, int size) {
        List<Group> groups = groupMapper.selectGroup(uid, name, page, page*size);
        List<GroupDTO> dtos = new LinkedList<>();
        for (Group group : groups) {
            GroupDTO dto = new GroupDTO();
            dto.setGroupUUID(group.getGroupUUID());
            dto.setGroupName(group.getGroupName());
            dto.setGroupStatus(group.getGroupStatus());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public GroupDTO getById(int gid) {
        Group group = groupMapper.selectGroupById(gid);
        if (group == null){
            throw new UnknownIdException(gid);
        }
        GroupDTO dto = new GroupDTO();
        dto.setGroupUUID(group.getGroupUUID());
        dto.setGroupName(group.getGroupName());
        dto.setGroupNote(group.getGroupNote());
        dto.setGroupMembers(group.getGroupMembers());
        dto.setGroupStatus(group.getGroupStatus());
        return dto;
    }

}
