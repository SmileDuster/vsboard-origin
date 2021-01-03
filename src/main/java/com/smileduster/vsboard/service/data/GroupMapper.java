package com.smileduster.vsboard.service.data;

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
}
