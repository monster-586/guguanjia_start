package com.dfbz.controller;


import com.dfbz.entity.SysRole;

import com.dfbz.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
;
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
        PageInfo<SysRole> pageInfo = sysRoleService.selectByCondition(map);
//        System.out.println(pageInfo);
        return pageInfo;
    }

    @RequestMapping("list")
    public List<SysRole> SelectALL() {
        List<SysRole> sysRoles = sysRoleService.selectAll();
        return sysRoles;
    }

    @RequestMapping(value = "insertBatch",produces = "html/text;charset=utf-8")
    public String insertBatch(@RequestBody Map<String, Object> params) {

        int roleId = (int) params.get("roleId");
        ArrayList<Long> insertUserId = new ArrayList<>();
        List<Integer> list = (List<Integer>) params.get("insertUserId");
        for (Integer integer : list) {
            insertUserId.add(Long.valueOf(integer));
        }

        String msg="操作失败";
        int i = sysRoleService.insertBatch(insertUserId, roleId);
        if(i>0){
            msg="操作成功";
        }
        return msg;
    }
    @RequestMapping(value = "deleteBatch",produces = "html/text;charset=utf-8")
    public String deleteByRoleAndUser(@RequestBody Map<String, Object> params){

        int roleId = (int) params.get("roleId");
        int i=0;
        List<Integer> list = (List<Integer>) params.get("removeUserId");
        for (Integer intlist : list) {
          i = sysRoleService.deleteByRoleAndUser((long) roleId, Long.valueOf(intlist));
        }
        String msg="操作失败";
        if(i>0){
            msg="操作成功";
        }
        return msg;
    }

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
