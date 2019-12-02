package com.dfbz.dao;


import com.dfbz.entity.SysResource;
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
}