package com.dfbz.service;



import com.dfbz.entity.SysResource;

import java.util.List;


public interface SysResourceService extends tservice<SysResource>{

    List<SysResource> selectRoleResource(Long rid);



}

