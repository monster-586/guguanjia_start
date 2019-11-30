package com.dfbz.controller;


import com.dfbz.entity.SysUser;
import com.dfbz.service.SysUserService;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/sysuser")
public class SysUserController {

    @Autowired
    SysUserService syuService;


    @RequestMapping("list")
    public List<SysUser> list() {
        return syuService.selectAll();
    }

    @RequestMapping("selectByCondition")
    public PageInfo<SysUser> selectByCondition(@RequestBody Map<String, Object> map) {

        PageInfo<SysUser> sysUserPageInfo = syuService.selectByCondition(map);
        return sysUserPageInfo;
    }

    @RequestMapping("selectNotRole")
    public List<SysUser> selectNotRole(@RequestBody Map<String, Object> params) {
//        System.out.println("*****************"+params);
        List<SysUser> sysUsers = syuService.selectNotRole(params);
//        for (SysUser sysUser : sysUsers) {
//            System.out.println("dx"+sysUser);
//        }
        return sysUsers;
    }

    @RequestMapping("selectHaveRole")
    public List<SysUser> selectHaveRole(@RequestBody Map<String, Object> params){
        List<SysUser> sysUsers = syuService.selectHaveRole(params);
        for (SysUser sysUser : sysUsers) {
            System.out.println("yx"+sysUser);
        }
        return sysUsers;
    }



}
