package com.smileduster.vsboard.web.core.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("group/")
public class GroupController {

    private final GroupService groupService;
    private final MemberService memberService;

    public GroupController(GroupService groupService, MemberService memberService) {
        this.groupService = groupService;
        this.memberService = memberService;
    }

    @PostMapping("addGroup")
    public ResponseCode addGroup(@RequestBody GroupDTO groupDTO){
        groupService.add(groupDTO, getUid());
        return ResponseCode.OK;
    }

    @PostMapping("removeGroup")
    public ResponseCode removeGroup(String groupId){
        groupService.remove(groupId);
        return ResponseCode.OK;
    }

    @PostMapping("updateGroup")
    public ResponseCode setGroupInfo(@RequestBody GroupDTO groupDTO){
        groupService.setInfo(groupDTO);
        return ResponseCode.OK;
    }

    @PostMapping("addMember")
    public ResponseCode addMember(@RequestBody MemberDTO memberDTO){
        memberService.add(memberDTO, getUid());
        return ResponseCode.OK;
    }

    @PostMapping("removeMember")
    public ResponseCode removeMember(String groupId, int memberId){
        memberService.remove(memberId, groupId);
        return ResponseCode.OK;
    }

    @PostMapping("setMemberInfo")
    public ResponseCode setMemberInfo(@RequestBody MemberDTO memberDTO){
        memberService.updateInfo(memberDTO);
        return ResponseCode.OK;
    }

    @PostMapping("setPublic")
    public ResponseCode setPublic(String groupId, int publicity){
        groupService.setPublic(groupId, publicity);
        return ResponseCode.OK;
    }

    @GetMapping("getGroupById")
    public GroupDTO getGroupById(String groupId){
        return groupService.getById(groupId);
    }

    @GetMapping("getGroupByUser")
    public List<GroupDTO> getGroupByUser(int page, int size){
        return groupService.getByUser(getUid(), page, size);
    }

    @GetMapping("getGroupByUserAndName")
    public List<GroupDTO> getGroupByUserAndName(String name, int page, int size){
        return groupService.getByUserAndName(getUid(), name, page, size);
    }

    @GetMapping("getGroupByName")
    public List<GroupDTO> getGroupByName(String name, int page, int size){

    }

    @GetMapping("getMemberByGroup")
    public List<MemberDTO> getMemberByGroup(String groupId, int page, int size){

    }

}
