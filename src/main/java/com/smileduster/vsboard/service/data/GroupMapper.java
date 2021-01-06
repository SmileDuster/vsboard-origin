package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.po.Battle;
import com.smileduster.vsboard.api.model.po.Group;
import com.smileduster.vsboard.api.model.po.GroupMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {

    Group selectGroupByUUID(byte[] groupUUID);

    void incBattleCount(int groupId);

    Group selectGroupById(int groupId);

    void decBattleCount(int groupId);

    List<GroupMember> selectMembersByGroup(int groupId);

    GroupMember selectMemberByNumber(int groupId, int groupMemberNumber);

    List<GroupMember> selectMembersByMember(int memberId);

    void insertGroup(Group group);

    void deleteGroupById(int groupId);

    void insertGroupMember(GroupMember groupMember);

    void incMemberCount(int groupId);

    void incMemberNumber(int groupId);

    List<GroupMember> selectMembersIn(int groupId, List<Integer> memberNumbers);

    void deleteMembersByGroup(int groupId);

    List<Group> selectGroupsByUser(int page, int skip, int userId);

    List<Battle> selectBattlesByGroup(int groupId);
}
