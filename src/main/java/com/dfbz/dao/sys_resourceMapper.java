package com.dfbz.dao;

import com.dfbz.entity.sys_resource;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface sys_resourceMapper extends Mapper<sys_resource> {
    @Select(" select sr.*,srr.id roleId " +
            " from sys_role_resource srr " +
            " left join  " +
            " sys_resource sr " +
            " on sr.id=srr.resource_id " +
            " where role_id=#{rid}")
    List<sys_resource> selectRoleResource(Long rid);
}