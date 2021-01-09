package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.po.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void insertMember(Member member);

    void deleteMemberById(int memberId);

    Member selectMemberByUUID(byte[] memberUUID);
    Member selectMemberById(int memberId);
    List<Member> selectMembersByUser(int page, int skip, int userId);

}
