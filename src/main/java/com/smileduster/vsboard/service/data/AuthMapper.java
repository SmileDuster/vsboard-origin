package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.dto.RoleDTO;
import com.smileduster.vsboard.api.model.dto.UserDTO;
import com.smileduster.vsboard.api.model.po.Role;
import com.smileduster.vsboard.api.model.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface AuthMapper {

    void insertUser(User user);

    Set<Role> selectRolesByUserId(int userId);

    User selectUserById(int uid);

    int checkUserNo(long userNo);

    User selectUserByUserNo(long userNo);
}
