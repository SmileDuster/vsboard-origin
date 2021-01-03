package com.smileduster.vsboard.service.data;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IdMapper {

    int getUserId(long userNo);

}
