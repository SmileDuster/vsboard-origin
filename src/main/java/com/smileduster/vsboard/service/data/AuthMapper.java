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

//    User selectUserByName(String name, int page, int skip);
//    Role selectRoleById(int rid);
//
    void insertUser(User user);
//    void insertRole(Role role);

    Set<Role> selectRolesByUserId(int userId);

    User selectUserById(int uid);

    int checkUserNo(long userNo);

}
