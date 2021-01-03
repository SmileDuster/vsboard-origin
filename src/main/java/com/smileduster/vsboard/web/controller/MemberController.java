package com.smileduster.vsboard.web.controller;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.dto.MemberDTO;
import com.smileduster.vsboard.api.service.IMemberService;
import com.smileduster.vsboard.web.form.CreateMemberForm;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final IMemberService memberService;

    public MemberController(IMemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/create")
    public Response<?> createMember(CreateMemberForm form) {
        MemberDTO dto = new MemberDTO();
        dto.setMemberName(form.getMemberName());
        dto.setMemberNote(form.getMemberNote());
        dto.setMemberInfo(form.getMemberInfo());
        return memberService.createMember(dto, userId());
    }

    @PostMapping("/delete/{memberUUID}")
    public Response<?> deleteMember(@PathVariable String memberUUID) {
        return memberService.deleteMember(memberUUID, userId());
    }

    @GetMapping("/{memberUUID}")
    public Response<MemberDTO> getMemberDetail(@PathVariable String memberUUID) {
        return memberService.getMember(memberUUID, userId());
    }

    @GetMapping("/list")
    public Response<List<MemberDTO>> getMemberList(int page, int size) {
        return memberService.getMembers(page, size, userId());
    }

    private int userId() {
        return (int) SecurityUtils.getSubject().getSession().getAttribute("userId");
    }

}
