package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.exception.UnknownIdException;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.account.PermDTO;
import com.smileduster.vsboard.api.model.po.account.Perm;
import com.smileduster.vsboard.api.service.account.IPermService;
import com.smileduster.vsboard.service.data.AccountMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PermServiceImpl implements IPermService {

    private final AccountMapper accountMapper;

    public PermServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public ResponseCode add(PermDTO dto) {
        accountMapper.insertPerm(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode remove(int pid) {
        accountMapper.deletePermById(pid);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode updateInfo(PermDTO dto) {
        accountMapper.updatePermInfo(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode updateStatus(int pid, int status) {
        if (!accountMapper.updatePermStatus(pid, status)) {
            throw new UnknownIdException(pid);
        }
        return ResponseCode.success;
    }

    @Override
    public List<PermDTO> getAll(int page, int size) {
        List<Perm> perms = accountMapper.selectPermAll(page, size*page);
        List<PermDTO> dtos = new LinkedList<>();
        for (Perm perm : perms) {
            PermDTO dto = new PermDTO();
            dto.setPermId(perm.getPermId());
            dto.setPermName(perm.getPermName());
            dto.setPermStatus(perm.getPermStatus());
            dtos.add(dto);
        }
        return dtos;
    }

}
