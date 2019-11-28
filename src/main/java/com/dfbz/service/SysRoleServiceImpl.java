package com.dfbz.service;


import com.dfbz.dao.SysRoleMapper;
import com.dfbz.dao.SysUserMapper;
import com.dfbz.entity.SysRole;
import com.dfbz.entity.SysUser;
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
public class SysRoleServiceImpl extends tservisceIpm<SysRole> implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> selectByCondition(Map<String, Object> map) {
        if (!map.containsKey("pageNum")||StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (!map.containsKey("pageSize")||StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 5);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));

        List<SysRole> sysRole = sysRoleMapper.selectByCondition(map);
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRole);
        return pageInfo;
    }
}
