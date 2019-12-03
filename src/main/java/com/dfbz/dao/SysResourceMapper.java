package com.dfbz.dao;


import com.dfbz.entity.SysResource;
import com.dfbz.entity.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysResourceMapper extends Mapper<SysResource> {

    @Select(" select sr.*,srr.id roleId  " +
            " from sys_role_resource srr  " +
            " left join   " +
            " sys_resource sr  " +
            " on sr.id=srr.resource_id  " +
            " where role_id=#{rid}")
    List<SysResource> selectByRid(long rid);

    @Delete(" delete from " +
            " sys_role_resource " +
            " where role_id=#{roleId} ")
    int deleteByRid(Long roleId);

@InsertProvider(type = SysResourceMapperProcider.class,method = "insertBatch")
int insertBatch(@Param("roleId") Long roleId, @Param("Resources")List<Long> Resources);
}