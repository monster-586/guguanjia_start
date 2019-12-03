package com.dfbz.controller;


import com.dfbz.entity.SysResource;
import com.dfbz.service.SysOfficeService;
import com.dfbz.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("manager/resource")
public class SysResourceController {

    @Autowired
    SysResourceService sysResourceService;
    @Autowired
    SysOfficeService sysOfficeService;

    @RequestMapping("selectByRid")
    List<SysResource> selectRoleResource(Long rid) {
        List<SysResource> sysResources = sysResourceService.selectRoleResource(rid);
        return sysResources;
    }


    @RequestMapping("list")
    List<SysResource> listResource() {
        return sysResourceService.selectAll();
    }

    @RequestMapping(value = "upDataResource",produces = "html/text;charset=utf-8")
    String upDataResource(@RequestBody Map<String, Object> params) {
        String msg="操作失败";
        int roleId = (int) params.get("roleId");
        int i = sysResourceService.deleteByRid((long)roleId);

        List<Long> listResource = new ArrayList<Long>();
        List<Integer> list = (List<Integer>) params.get("resourceIds");
        for (Integer mapList : list) {
            listResource.add(Long.valueOf(mapList));
        }
        int j = sysResourceService.insertBatch((long)roleId, listResource);

        List<Long> listOffids = new ArrayList<Long>();
        List<Integer> list1= (List<Integer>) params.get("offIds");
        for (Integer mapList : list1) {
            listOffids.add(Long.valueOf(mapList));
        }
        int k = sysOfficeService.insertBatch((long)roleId, listOffids);

        if (i+j+k>3){msg="操作成功";}
        return msg;
    }
}
