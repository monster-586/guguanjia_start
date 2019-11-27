package com.dfbz.service;


import com.dfbz.entity.SysUser;
import com.github.pagehelper.PageInfo;


import java.util.Map;

public interface SysUserService extends tservice<SysUser> {

  PageInfo< SysUser> selectByCondition(Map<String, Object> map);




}
