package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.po.Role;
import com.smileduster.vsboard.api.model.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface AuthMapper {

    void insertUser(User user);

    int checkUserNo(long userNo);
    int checkEmail(String userEmail);

    User selectUserById(int userId);
    User selectUserByUserNo(long userNo);
    User selectUserByUserEmail(String userEmail);
    Set<Role> selectRolesByUserId(int userId);
}
