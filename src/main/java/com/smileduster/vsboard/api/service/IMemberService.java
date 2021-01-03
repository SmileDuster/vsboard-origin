package com.smileduster.vsboard.api.service;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.dto.MemberDTO;

import java.util.List;

public interface IMemberService {

    Response<?> createMember(MemberDTO dto, int userId);

    Response<?> deleteMember(String memberUUID, int userId);

    Response<List<MemberDTO>> getMembers(int page, int size, int userId);

    Response<MemberDTO> getMember(String memberUUID, int userId);

}
