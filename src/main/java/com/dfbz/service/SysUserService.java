package com.dfbz.service;


import com.dfbz.entity.SysUser;
import com.github.pagehelper.PageInfo;


import java.util.List;
import java.util.Map;

public interface SysUserService extends tservice<SysUser> {

  PageInfo< SysUser> selectByCondition(Map<String, Object> map);

  List<SysUser> selectNotRole(Map<String, Object> params);

  List<SysUser> selectHaveRole(Map<String, Object> params);

}
