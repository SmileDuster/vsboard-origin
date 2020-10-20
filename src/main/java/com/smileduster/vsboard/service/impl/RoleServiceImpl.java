package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.exception.UnknownIdException;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.account.RoleDTO;
import com.smileduster.vsboard.api.model.po.account.Role;
import com.smileduster.vsboard.api.service.account.IRoleService;
import com.smileduster.vsboard.service.data.AccountMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    private final AccountMapper accountMapper;

    public RoleServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public ResponseCode add(RoleDTO dto) {
        accountMapper.insertRole(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode remove(int rid) {
        accountMapper.deleteRoleById(rid);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode updateInfo(RoleDTO dto) {
        accountMapper.updateRoleInfo(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode updateStatus(int rid, int status) {
        if (!accountMapper.updateRoleStatus(rid, status)) {
            throw new UnknownIdException(rid);
        }
        return ResponseCode.success;
    }

    @Override
    public List<RoleDTO> getAll(int page, int size) {
        List<Role> roles = accountMapper.selectRoleAll(page, size*page);
        List<RoleDTO> dtos = new LinkedList<>();
        for (Role role : roles) {
            RoleDTO dto = new RoleDTO();
            dto.setRoleId(role.getRoleId());
            dto.setRoleName(role.getRoleName());
            dto.setRoleStatus(role.getRoleStatus());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public RoleDTO getById(int rid) {
        Role role = accountMapper.selectRoleById(rid);
        if (role == null){
            throw new UnknownIdException(rid);
        } else {
            RoleDTO dto = new RoleDTO();
            dto.setRoleId(role.getRoleId());
            dto.setRoleName(role.getRoleName());
            dto.setRoleStatus(role.getRoleStatus());
            dto.setRolePerms(role.getRolePerms());
            return dto;
        }
    }
}
