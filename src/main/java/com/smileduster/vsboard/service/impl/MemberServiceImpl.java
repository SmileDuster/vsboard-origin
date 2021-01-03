package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.dto.MemberDTO;
import com.smileduster.vsboard.api.model.po.Member;
import com.smileduster.vsboard.api.service.IMemberService;
import com.smileduster.vsboard.api.tools.DefaultGenerator;
import com.smileduster.vsboard.service.data.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements IMemberService {

    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Response<?> createMember(MemberDTO dto, int userId) {
        Member member = new Member();
        member.setMemberName(dto.getMemberName());
        member.setMemberNote(dto.getMemberNote());
        member.setMemberInfo(dto.getMemberInfo());
        member.setMemberUUID(DefaultGenerator.getUUID());

        return null;
    }

    @Override
    public Response<?> deleteMember(String memberUUID, int userId) {
        return null;
    }

    @Override
    public Response<List<MemberDTO>> getMembers(int page, int size, int userId) {
        return null;
    }

    @Override
    public Response<MemberDTO> getMember(String memberUUID, int userId) {
        return null;
    }

}
