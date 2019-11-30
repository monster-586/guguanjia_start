package com.dfbz.controller;


import com.dfbz.entity.SysRole;

import com.dfbz.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/role")
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping("selectByCondition")
    public PageInfo<SysRole> selectByCondition(@RequestBody Map<String, Object> map) {
//        System.out.println(map);
        PageInfo<SysRole> pageInfo = sysRoleService.selectByCondition(map);
//        System.out.println(pageInfo);
        return pageInfo;
    }

    @RequestMapping("list")
    public List<SysRole> SelectALL() {
        List<SysRole> sysRoles = sysRoleService.selectAll();
        return sysRoles;
    }

    @RequestMapping("insertBatch")
    public void insertBatch(@RequestBody Map<String, Object> params) {
        Long insertRoleId = (Long) params.get("insertRoleId");
        ArrayList<Long> longs = new ArrayList<>();
        List<Long> removeUserId = (List<Long>) params.get("removeUserId");
        for (Long removeUserId1 : removeUserId) {
            longs.add(removeUserId1);
        }
        sysRoleService.insertBatch(longs, insertRoleId);
    }
//    @RequestMapping("selectOneById")
//    public Area selectOneById(@RequestParam Long id) {
////        System.out.println("selectOneById" + id);
//        Area area = areaService.selectOneById(id);
//        System.out.println("befor" + area);
//        area.setOldparentIds(area.getParentIds());
//        area.setOdlparentId(area.getParentId());
//        System.out.println("afet" + area);
//
//        return area;
//    }
//
//
//    @RequestMapping(value = "update", produces = "html/text;charset=utf-8")
//    public String insertSelective(@RequestBody Area area) {
//        String msg = "操作失败";
//        System.out.println("in" + area);
//        int i = areaService.updateOne(area);
//        Area area1 = areaService.selectOneById(area.getId());
//        System.out.println("one" + area1);
//        if (i > 0) {
//            int j = areaService.updateAllParentIds(area1);
//            System.out.println("all" + area1);
//            if (j > 0) {
//                msg = "操作失败";
//            }
//        }
//        return msg;
//    }


}
