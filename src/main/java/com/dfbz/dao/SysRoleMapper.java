package com.dfbz.dao;


import com.dfbz.entity.SysRole;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


public interface SysRoleMapper extends Mapper<SysRole> {

    @Select(" select sr.*" +
            " from sys_user su " +
            " left join  " +
            " sys_user_role sur on  " +
            " sur.user_id=su.id " +
            " left join " +
            " sys_role sr on " +
            " sur.role_id=sr.id " +
            " where  " +
            " su.id=#{userId}")
    List<SysRole> selectRoleByUserId(Long userId);

    @SelectProvider(type = SysRoleMapperPrvoider.class, method = "selectByCondition")
    List<SysRole> selectByCondition(Map<String, Object> params);

    @InsertProvider(type = SysRoleMapperPrvoider.class, method = "insertBatch")
    int insertBatch(@Param("insertUserId") List<Long> insertUserId, @Param("roleId") long roleId);

    @Delete(" delete from " +
            " sys_user_role " +
            " where user_id=#{removeUserId} " +
            " and role_id=#{roleId} ")
    int deleteByRoleAndUser(@Param("roleId") Long roleId, @Param("removeUserId") Long removeUserId);


}