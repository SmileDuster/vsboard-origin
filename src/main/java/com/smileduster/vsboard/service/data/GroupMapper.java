package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.dto.member.GroupCardDTO;
import com.smileduster.vsboard.api.model.dto.member.GroupDTO;
import com.smileduster.vsboard.api.model.po.member.Group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {

    List<Group> selectGroup(int uid, String name, int page, int skip);
    Group selectGroupById(int gid);

    void updateGroupInfo(GroupDTO dto);
    void updateGroupCardInfo(GroupCardDTO dto);
    boolean updateGroupStatus(int gid, int status);

    void deleteGroupById(int gid);
    void deleteGroupCardById(int gid, int mid);

    void insertGroup(GroupDTO dto);
    void insertGroupCard(GroupCardDTO dto);


}
