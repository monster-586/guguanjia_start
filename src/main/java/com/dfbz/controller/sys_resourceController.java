package com.dfbz.controller;


import com.dfbz.entity.sys_resource;
import com.dfbz.service.sys_resourceService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("manager/resource")
public class sys_resourceController {

    @Autowired
    sys_resourceService resourceService;

    @RequestMapping("list")
    List<sys_resource> selectRoleResource(Long rid) {
        List<sys_resource> sys_resources = resourceService.selectRoleResource(rid);
        return sys_resources;
    }


}
