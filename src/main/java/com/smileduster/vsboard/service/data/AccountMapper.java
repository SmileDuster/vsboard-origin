package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.dto.account.PermDTO;
import com.smileduster.vsboard.api.model.dto.account.RoleDTO;
import com.smileduster.vsboard.api.model.dto.account.UserDTO;
import com.smileduster.vsboard.api.model.po.account.Perm;
import com.smileduster.vsboard.api.model.po.account.Role;
import com.smileduster.vsboard.api.model.po.account.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    List<User> selectUserByName(String name, int page, int skip);
    List<User> selectUserAll(int page, int skip);
    User selectUserById(int uid);
    List<Role> selectRoleAll(int page, int skip);
    Role selectRoleById(int rid);
    List<Perm> selectPermAll(int page, int skip);

    boolean updatePwd(int uid, String pwd);
    void updateUserInfo(UserDTO dto);
    void updateRoleInfo(RoleDTO dto);
    void updatePermInfo(PermDTO dto);
    boolean updateRoleStatus(int rid, int status);
    boolean updatePermStatus(int pid, int status);

    void deleteUserById(int uid);
    void deleteRoleById(int rid);
    void deletePermById(int pid);

    void insertUser(UserDTO dto);
    void insertPerm(PermDTO dto);
    void insertRole(RoleDTO dto);
}
