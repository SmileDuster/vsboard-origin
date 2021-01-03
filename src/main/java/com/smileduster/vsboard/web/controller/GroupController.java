package com.smileduster.vsboard.web.controller;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.dto.GroupDTO;
import com.smileduster.vsboard.api.model.dto.GroupMemberDTO;
import com.smileduster.vsboard.api.service.IGroupService;
import com.smileduster.vsboard.web.form.CreateGroupForm;
import com.smileduster.vsboard.web.form.JoinGroupForm;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final IGroupService groupService;

    public GroupController(IGroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/create")
    public Response<?> createGroup(@RequestBody CreateGroupForm form) {
        GroupDTO dto = new GroupDTO();
        dto.setGroupName(form.getGroupName());
        dto.setGroupNote(form.getGroupNote());
        dto.setGroupInfo(form.getGroupInfo());
        return groupService.createGroup(dto, userId());
    }

    @PostMapping("/delete/{groupUUID}")
    public Response<?> deleteGroup(@PathVariable String groupUUID) {
        return groupService.deleteGroup(groupUUID, userId());
    }

    @PostMapping("/{groupUUID}")
    public Response<GroupDTO> getGroupDetail(@PathVariable String groupUUID) {
        return groupService.getGroup(groupUUID, userId());
    }

    @PostMapping("/list")
    public Response<List<GroupDTO>> getOwnedGroupList(int page, int size) {
        return groupService.getGroups(page, size, userId());
    }

    @PostMapping("/member/join")
    public Response<?> joinGroup(@RequestBody JoinGroupForm form) {
        GroupMemberDTO dto = new GroupMemberDTO();
        dto.setMemberUUID(form.getMemberUUID());
        dto.setGroupUUID(form.getGroupUUID());
        dto.setGroupMemberName(form.getGroupMemberName());
        return groupService.joinGroup(dto, userId());
    }

    @GetMapping("/member/{groupUUID}/{groupMemberNumber}")
    public Response<GroupMemberDTO> getGroupMemberDetail(
            @PathVariable String groupUUID,
            @PathVariable String groupMemberNumber
    ) {
        int number = Integer.parseInt(groupMemberNumber);
        return groupService.getGroupMember(groupUUID, number, userId());
    }

    private int userId() {
        return (int) SecurityUtils.getSubject().getSession().getAttribute("userId");
    }

}
