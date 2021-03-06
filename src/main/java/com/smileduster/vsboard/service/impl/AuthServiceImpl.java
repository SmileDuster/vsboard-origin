package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.exception.VsboardServiceException;
import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.UserDTO;
import com.smileduster.vsboard.api.model.po.User;
import com.smileduster.vsboard.api.service.IAuthService;
import com.smileduster.vsboard.api.tools.Converter;
import com.smileduster.vsboard.api.tools.Generator;
import com.smileduster.vsboard.service.data.AuthMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AuthServiceImpl implements IAuthService {

    private final AuthMapper authMapper;
    private final Converter converter;
    private final Generator generator;

    public AuthServiceImpl(AuthMapper authMapper,
                           Converter converter,
                           Generator generator) {
        this.authMapper = authMapper;
        this.converter = converter;
        this.generator = generator;
    }

    @Override
    public Response<?> createUser(UserDTO dto) {
        if (authMapper.checkEmail(dto.getUserEmail()) > 0) {
            return Response.create(ResponseCode.dupEmail, dto.getUserEmail());
        }

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setUserPwd(dto.getUserPwd());
        user.setUserPwdSalt(dto.getUserPwdSalt());
        long userNo = generator.getUserNo();
        while (authMapper.checkUserNo(userNo) > 0) {
            userNo = generator.getUserNo();
        }
        user.setUserNo(userNo);
        user.setUserEmail(dto.getUserEmail());
        user.setUserCreateTime(new Date());
        user.setUserLastIP(converter.parseIP(dto.getUserLastIP()));
        authMapper.insertUser(user);
        return Response.create();
    }

    @Override
    public Response<UserDTO> getUser(String userNo) {
//        User user = authMapper.selectUserByUserNo(converter.parseUserNo(userNo));
//
//        UserDTO dto = new UserDTO();
//        dto.setUserName(user.getUserName());
//        dto.set
        return Response.todo();
    }

}
