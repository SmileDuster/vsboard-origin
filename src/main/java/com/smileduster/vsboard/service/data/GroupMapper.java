package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.po.Battle;
import com.smileduster.vsboard.api.model.po.Group;
import com.smileduster.vsboard.api.model.po.GroupMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {

    void insertGroup(Group group);
    void insertGroupMember(GroupMember groupMember);

    void incMemberCount(int groupId);
    void incMemberNumber(int groupId);
    void incBattleCount(int groupId);
    void decBattleCount(int groupId);

    void deleteGroupById(int groupId);
    void deleteMembersByGroup(int groupId);

    Group selectGroupByUUID(byte[] groupUUID);
    Group selectGroupById(int groupId);
    GroupMember selectMemberByNumber(int groupId, int groupMemberNumber);
    List<GroupMember> selectMembersByGroup(int groupId);
    List<GroupMember> selectMembersByMember(int memberId);
    List<GroupMember> selectMembersIn(int groupId, List<Integer> memberNumbers);
    List<Group> selectGroupsByUser(int page, int skip, int userId);
    List<Battle> selectBattlesByGroup(int groupId);

}
