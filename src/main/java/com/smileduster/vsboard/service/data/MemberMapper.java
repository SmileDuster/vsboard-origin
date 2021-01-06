package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.dto.MemberDTO;
import com.smileduster.vsboard.api.model.po.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    Member selectMemberByUUID(byte[] memberUUID);

    void deleteMemberById(int memberId);

    List<Member> selectMembersByUser(int page, int skip, int userId);

    void insertMember(Member member);

    Member selectMemberById(int memberId);
}
