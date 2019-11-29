package com.dfbz.dao;


import com.dfbz.entity.SysUser;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {


    @SelectProvider(type = SysUserMapperProvider.class,method = "selectByCondition")
    //设置关联查询，将用户id对应的所有的roles查询出来
    @Results({
            @Result(column = "id",property = "id"),
            @Result(property = "roles",column = "id",many = @Many(select = "com.dfbz.dao.SysRoleMapper.selectRoleByUserId"))
    })
    List<SysUser> selectByCondition(Map<String, Object> params);

    
    @Select(" select *  " +
            " from sys_user su " +
            " where " +
            " su.office_id=#{offId} " +
            " and su.id  " +
            " not in " +
            " (select sur.user_id " +
            " from sys_user_role sur " +
            " left join  " +
            " sys_role sr on " +
            " sur.role_id=sr.id " +
            " where " +
            " sr.id=#{offId} )  ")
    List<SysUser> selectNotRole(Long offId);
}