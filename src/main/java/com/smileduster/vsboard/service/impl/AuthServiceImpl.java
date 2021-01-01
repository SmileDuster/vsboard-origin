package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.UserDTO;
import com.smileduster.vsboard.api.model.po.User;
import com.smileduster.vsboard.api.service.IAuthService;
import com.smileduster.vsboard.api.utils.RandomUtil;
import com.smileduster.vsboard.api.utils.TypeUtil;
import com.smileduster.vsboard.service.data.AuthMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AuthServiceImpl implements IAuthService {

    private final AuthMapper authMapper;

    public AuthServiceImpl(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    @Override
    public Response<?> createUser(UserDTO dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setUserPwd(dto.getUserPwd());
        user.setUserPwdSalt(dto.getUserPwdSalt());
        long userNo = RandomUtil.getUserNo();
        while (authMapper.checkUserNo(userNo) > 0) {
            userNo = RandomUtil.getUserNo();
        }
        user.setUserNo(userNo);
        user.setUserEmail(dto.getUserEmail());
        user.setUserCreateTime(new Date());
        user.setUserLastIP(TypeUtil.parseIP(dto.getUserLastIP()));
        authMapper.insertUser(user);
        return Response.create();
    }

    @Override
    public Response<UserDTO> getUser(String userNo) {
        return Response.todo();
    }

}
