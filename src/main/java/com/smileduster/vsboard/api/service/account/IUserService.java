package com.smileduster.vsboard.api.service.account;

import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.account.UserDTO;

import java.util.List;

public interface IUserService {

    ResponseCode add(UserDTO dto);

    ResponseCode remove(int uid);

    ResponseCode updateInfo(UserDTO dto);

    ResponseCode updatePwd(int uid, String pwd);

    List<UserDTO> getByName(String name, int page, int size);

    List<UserDTO> getAll(int page, int size);

    UserDTO getById(int uid);

}
