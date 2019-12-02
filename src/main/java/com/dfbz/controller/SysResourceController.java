package com.dfbz.controller;



import com.dfbz.entity.SysResource;
import com.dfbz.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("manager/resource")
public class SysResourceController {

    @Autowired
    SysResourceService sysResourceService;

    @RequestMapping("selectByRid")
    List<SysResource> selectRoleResource(Long rid) {
        List<SysResource> sysResources = sysResourceService.selectRoleResource(rid);
        return sysResources;
    }


    @RequestMapping("list")
    List<SysResource> listResource(){
        return sysResourceService.selectAll();
    }


}
