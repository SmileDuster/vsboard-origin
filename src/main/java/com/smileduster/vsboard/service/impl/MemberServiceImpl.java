package com.smileduster.vsboard.service.impl;

import com.smileduster.vsboard.api.exception.UnknownIdException;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.member.GroupCardDTO;
import com.smileduster.vsboard.api.model.dto.member.MemberDTO;
import com.smileduster.vsboard.api.model.po.member.GroupCard;
import com.smileduster.vsboard.api.model.po.member.Member;
import com.smileduster.vsboard.api.service.member.IMemberService;
import com.smileduster.vsboard.service.data.IdMapper;
import com.smileduster.vsboard.service.data.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MemberServiceImpl implements IMemberService {

    private final MemberMapper memberMapper;
    private final IdMapper idMapper;

    public MemberServiceImpl(MemberMapper memberMapper, IdMapper idMapper) {
        this.memberMapper = memberMapper;
        this.idMapper = idMapper;
    }

    @Override
    public ResponseCode add(MemberDTO dto) {
        memberMapper.insertMember(dto);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode remove(int mid) {
        memberMapper.deleteMemberById(mid);
        return ResponseCode.success;
    }

    @Override
    public ResponseCode updateInfo(MemberDTO dto) {
        memberMapper.updateMemberInfo(dto);
        return ResponseCode.success;
    }

    @Override
    public List<MemberDTO> getByUserAndName(int uid, String name, int page, int size) {
        List<Member> members = memberMapper.selectMember(uid, name, page, size*page);
        List<MemberDTO> memberDTOS = new LinkedList<>();
        for (Member member : members) {
            MemberDTO dto = new MemberDTO();
            dto.setMemberName(member.getMemberName());
            dto.setMemberUUID(member.getMemberUUID());
            memberDTOS.add(dto);
        }
        return memberDTOS;
    }

    @Override
    public List<MemberDTO> getByUser(int uid, int page, int size) {
        List<Member> members = memberMapper.selectMember(uid, "", page, size*page);
        List<MemberDTO> memberDTOS = new LinkedList<>();
        for (Member member : members) {
            MemberDTO dto = new MemberDTO();
            dto.setMemberUUID(member.getMemberUUID());
            dto.setMemberName(member.getMemberName());
            memberDTOS.add(dto);
        }
        return memberDTOS;
    }

    @Override
    public List<MemberDTO> getByName(String name, int page, int size) {
        List<Member> members = memberMapper.selectMember(-1, name, page, size*page);
        List<MemberDTO> memberDTOS = new LinkedList<>();
        for (Member member : members) {
            MemberDTO dto = new MemberDTO();
            dto.setMemberUUID(member.getMemberUUID());
            dto.setMemberName(member.getMemberName());
            dto.setUserName(idMapper.selectUserName(dto.getUserId()));
            memberDTOS.add(dto);
        }
        return memberDTOS;
    }

    @Override
    public MemberDTO getById(int mid) {
        Member member = memberMapper.selectMemberById(mid);
        if (member == null){
            throw new UnknownIdException(mid);
        }
        MemberDTO dto = new MemberDTO();
        dto.setMemberUUID(member.getMemberUUID());
        dto.setMemberName(member.getMemberName());
        dto.setMemberNote(member.getMemberNote());
        List<GroupCard> groupCards = member.getMemberGroups();
        List<GroupCardDTO> groupCardDTOS = new LinkedList<>();
        for (GroupCard card : groupCards) {
            GroupCardDTO cardDTO = new GroupCardDTO();
            cardDTO.setMemberNumber(card.getMemberNumber());
            cardDTO.setGroupNickname(card.getGroupNickname());
            cardDTO.setGroupPoint(card.getGroupPoint());
            groupCardDTOS.add(cardDTO);
        }
        dto.setMemberGroups(groupCardDTOS);
        dto.setUserName(idMapper.selectUserName(member.getUserId()));
        return dto;
    }

}
