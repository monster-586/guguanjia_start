package com.dfbz.service;



import com.dfbz.dao.SysUserMapper;
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
public class SysUserServiceImpl extends tservisceIpm<SysUser> implements SysUserService {

@Autowired
    SysUserMapper sysUserMapper;
    @Override
    public PageInfo<SysUser> selectByCondition(Map<String, Object> map) {
        if(StringUtils.isEmpty(map.get("pageNum"))){
            map.put("pageNum",1);
        }
        if(StringUtils.isEmpty(map.get("pageSize"))){
            map.put("pageSize",5);
        }
        PageHelper.startPage((Integer) map.get("pageNum"),(Integer)map.get("pageSize"));

        List<SysUser> sysUsers = sysUserMapper.selectByCondition(map);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        return pageInfo;
    }

    @Override
    public List<SysUser> selectNotRole(Long offId) {
        return sysUserMapper.selectNotRole(offId);
    }
}
