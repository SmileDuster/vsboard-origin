package com.smileduster.vsboard.api.service;

import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.MemberDTO;

import java.util.List;

public interface IMemberService {

    ResponseCode createMember(MemberDTO dto, int userId);

    ResponseCode deleteMember(String memberUUID, int userId);

    List<MemberDTO> getMembers(int page, int size, int userId);

    MemberDTO getMember(String memberUUID, int userId);

}
