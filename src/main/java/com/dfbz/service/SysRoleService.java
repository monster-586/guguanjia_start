package com.dfbz.service;

import com.dfbz.entity.SysRole;
import com.dfbz.entity.SysUser;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysRoleService extends tservice<SysRole>{

    PageInfo<SysRole> selectByCondition(Map<String, Object> map);

    int insertBatch(List<Long> insertUserId, long insertRoleId);

    int deleteByRoleAndUser(Long roleId, Long removeUserId);


}

