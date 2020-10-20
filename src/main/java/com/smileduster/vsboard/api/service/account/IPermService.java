package com.smileduster.vsboard.api.service.account;

import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.account.PermDTO;

import java.util.List;

public interface IPermService {

    ResponseCode add(PermDTO dto);

    ResponseCode remove(int pid);

    ResponseCode updateInfo(PermDTO dto);

    ResponseCode updateStatus(int pid, int status);

    List<PermDTO> getAll(int page, int size);

}
