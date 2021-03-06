package com.dfbz.service;


import com.dfbz.dao.SysResourceMapper;
import com.dfbz.entity.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SysResourceServiceImpl extends tservisceIpm<SysResource> implements SysResourceService {

    @Autowired
    SysResourceMapper resourceMapper;

    @Override
    public List<SysResource> selectRoleResource(Long rid){

        return resourceMapper.selectByRid(rid);
    }

    @Override
    public int deleteByRid(Long roleId) {
        return resourceMapper.deleteByRid(roleId);
    }

    @Override
    public int insertBatch(Long roleId, List<Long> Resources) {
        return resourceMapper.insertBatch(roleId,Resources);
    }
}
