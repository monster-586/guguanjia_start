package com.dfbz.service;

import com.dfbz.entity.SysRole;
import com.dfbz.entity.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysRoleService {

    PageInfo<SysRole> selectByCondition(Map<String, Object> map);

    List< SysRole> selectAll();
}
