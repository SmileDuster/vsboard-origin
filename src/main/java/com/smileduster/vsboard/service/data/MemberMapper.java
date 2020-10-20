package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.dto.member.MemberDTO;
import com.smileduster.vsboard.api.model.po.member.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<Member> selectMember(int uid, String name, int page, int skip);
    Member selectMemberById(int mid);

    void updateMemberInfo(MemberDTO dto);

    void deleteMemberById(int mid);

    void insertMember(MemberDTO dto);

}
