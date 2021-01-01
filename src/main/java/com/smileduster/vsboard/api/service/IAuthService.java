package com.smileduster.vsboard.api.service;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.dto.UserDTO;

public interface IAuthService {

    Response<?> createUser(UserDTO dto);

    Response<UserDTO> getUser(String userNo);

}
