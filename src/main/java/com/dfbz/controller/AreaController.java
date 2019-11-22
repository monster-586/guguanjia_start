package com.dfbz.controller;


import com.dfbz.entity.Area;
import com.dfbz.entity.Sysuser;
import com.dfbz.service.AreaService;
import com.dfbz.service.SysuserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/area")
public class AreaController {

    @Autowired
    AreaService areaService;

    @RequestMapping("list")
    public List<Area> list() {
        System.out.println("list11111111111111111");
        return areaService.selectAll();
    }

    @RequestMapping("selectByCondition")
    public  PageInfo<Area> selectByCondition(@RequestBody Map<String,Object> map){
        System.out.println("*1"+map);
        System.out.println("*2"+map.get("pageNum"));
        System.out.println("*3"+map.get("pageSize"));
        System.out.println("*4"+map.get("treeId"));
        System.out.println("*5"+map.get("areaName"));
        PageInfo<Area> pageInfo = areaService.selectByCondition(map);
        return pageInfo;
    }


}
