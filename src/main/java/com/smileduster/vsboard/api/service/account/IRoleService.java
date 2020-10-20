package com.smileduster.vsboard.api.service.account;

import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.account.RoleDTO;

import java.util.List;

public interface IRoleService {

    ResponseCode add(RoleDTO dto);

    ResponseCode remove(int rid);

    ResponseCode updateInfo(RoleDTO dto);

    ResponseCode updateStatus(int rid, int status);

    List<RoleDTO> getAll(int page, int size);

    RoleDTO getById(int rid);

}
