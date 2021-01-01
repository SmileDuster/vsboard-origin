package com.smileduster.vsboard.service.data;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IdMapper {

//    long getUserNo(int userId);
    int getUserId(long userNo);
//
//    byte[] getGroupUUID(int groupId);
//    int getGroupId(byte[] groupUUID);
//
//    byte[] getBattleUUID(int battleId);
//    int getBattleId(byte[] battleUUID);
//
//    byte[] getMemberUUID(int memberId);
//    int getMemberId(byte[] memberUUID);

}
