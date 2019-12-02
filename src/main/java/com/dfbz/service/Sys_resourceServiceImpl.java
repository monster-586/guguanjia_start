package com.dfbz.service;


import com.dfbz.dao.SysRoleMapper;
import com.dfbz.dao.sys_resourceMapper;
import com.dfbz.entity.SysRole;
import com.dfbz.entity.sys_resource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class Sys_resourceServiceImpl extends tservisceIpm<sys_resource> implements sys_resourceService {

    @Autowired
    sys_resourceMapper resourceMapper;

    @Override
    public List<sys_resource> selectRoleResource(Long rid) {
        return resourceMapper.selectRoleResource(rid);
    }
}
