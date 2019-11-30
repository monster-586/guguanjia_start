package com.dfbz.controller;

import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("manager/app/index")
public class AppVersionContorller {
    @Autowired
    AppVersionService appVersionService;

    @RequestMapping("selectAll")
    public PageInfo<AppVersion> selectAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "3") Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        AppVersion appVersion = new AppVersion();
        appVersion.setDelFlag("0");
        List<AppVersion> appVersionList = appVersionService.select(appVersion);
        PageInfo<AppVersion> pageInfo = new PageInfo<>(appVersionList);
        return pageInfo;
    }

    @RequestMapping(value = "updata", produces = "html/text;charset=utf-8")
    public String updateByPrimaryKey(@RequestBody AppVersion appVersion) {
        int i = appVersionService.updateByPrimaryKeySelective(appVersion);
        String mag = "操作失败";
        if (i > 0) {
            mag = "操作成功";
        }
        return mag;
    }

    @RequestMapping(value = "toupdata")
    public AppVersion toUpdate(Long id) {
        return appVersionService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "del", produces = "html/text;charset=utf-8")
    public String deleteByPrimaryKey(Long id) {
        AppVersion appVersion=new AppVersion();
        appVersion.setId(id);
        appVersion.setDelFlag("1");
        Date date = new Date();
        appVersion.setCreateDate(date);
//        System.out.println(date);
        int i = appVersionService.updateByPrimaryKeySelective(appVersion);
        String mag = "操作失败";
        if (i > 0) {
            mag = "操作成功";
        }
        return mag;
    }

    @RequestMapping(value = "add", produces = "html/text;charset=utf-8")
    public String insertSelective(@RequestBody AppVersion appVersion) {
        appVersion.setCreateBy("超级管理员");
        appVersion.setDelFlag("0");
        appVersion.setUpdateDate(new Date());
        appVersion.setCreateDate(new Date());
//        System.out.println(appVersion);
        int i = appVersionService.insertSelective(appVersion);
        String mag = "操作失败";
        if (i > 0) {
            mag = "操作成功";
        }
        return mag;
    }
}
