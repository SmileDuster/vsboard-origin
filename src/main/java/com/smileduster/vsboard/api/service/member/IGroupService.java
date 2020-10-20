package com.smileduster.vsboard.api.service.member;

import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.member.GroupCardDTO;
import com.smileduster.vsboard.api.model.dto.member.GroupDTO;

import java.util.List;

public interface IGroupService {

    ResponseCode add(GroupDTO dto);

    ResponseCode remove(int gid);

    ResponseCode updateInfo(GroupDTO dto);

    ResponseCode updateStatus(int gid, int status);

    ResponseCode addMember(GroupCardDTO dto);

    ResponseCode updateMember(GroupCardDTO dto);

    ResponseCode removeMember(int gid, int mid);

    List<GroupDTO> getByUser(int uid, int page, int size);

    List<GroupDTO> getByName(String name, int page, int size);

    List<GroupDTO> getByUserAndName(int uid, String name, int page, int size);

    GroupDTO getById(int gid);

}
