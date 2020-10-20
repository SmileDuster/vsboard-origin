package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.exception.UnknownIdException;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.account.UserDTO;
import com.smileduster.vsboard.api.model.po.account.User;
import com.smileduster.vsboard.api.service.account.IUserService;
import com.smileduster.vsboard.service.data.AccountMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final AccountMapper accountMapper;

    public UserServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public ResponseCode add(UserDTO dto) {
        accountMapper.insertUser(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode remove(int uid) {
        accountMapper.deleteUserById(uid);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode updateInfo(UserDTO dto) {
        accountMapper.updateUserInfo(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode updatePwd(int uid, String pwd) {
        if (!accountMapper.updatePwd(uid, pwd)){
            throw new UnknownIdException(uid);
        }
        return ResponseCode.success;
    }

    @Override
    public List<UserDTO> getByName(String name, int page, int size) {
        List<User> users = accountMapper.selectUserByName(name, page, page*size);
        List<UserDTO> dtos = new LinkedList<>();
        for (User user : users) {
            UserDTO dto = new UserDTO();
            dto.setUserName(user.getUserName());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<UserDTO> getAll(int page, int size) {
        List<User> users = accountMapper.selectUserAll(page, page*size);
        List<UserDTO> dtos = new LinkedList<>();
        for (User user : users) {
            UserDTO dto = new UserDTO();
            dto.setUserId(user.getUserId());
            dto.setUserName(user.getUserName());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public UserDTO getById(int uid) {
        User user = accountMapper.selectUserById(uid);
        if (user == null){
            throw new UnknownIdException(uid);
        } else {
            UserDTO dto = new UserDTO();
            dto.setUserName(user.getUserName());
            dto.setUserRoles(user.getUserRoles());
            return dto;
        }
    }
}
