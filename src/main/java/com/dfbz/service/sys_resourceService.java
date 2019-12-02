package com.dfbz.service;


import com.dfbz.entity.sys_resource;
import java.util.List;


public interface sys_resourceService {

    List<sys_resource> selectRoleResource(Long rid);

}

