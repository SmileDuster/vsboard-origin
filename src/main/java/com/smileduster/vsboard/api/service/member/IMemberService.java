package com.smileduster.vsboard.api.service.member;

import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.member.MemberDTO;

import java.util.List;

public interface IMemberService {

    ResponseCode add(MemberDTO dto);

    ResponseCode remove(int mid);

    ResponseCode updateInfo(MemberDTO dto);

    List<MemberDTO> getByUserAndName(int uid, String name, int page, int size);

    List<MemberDTO> getByUser(int uid, int page, int size);

    List<MemberDTO> getByName(String name, int page, int size);

    MemberDTO getById(int mid);

}
