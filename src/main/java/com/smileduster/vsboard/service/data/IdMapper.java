package com.smileduster.vsboard.service.data;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IdMapper {

    String selectUserName(int uid);
    byte[] selectGroupUUID(int gid);
    Integer selectBattleId(byte[] battleUUID);

}
