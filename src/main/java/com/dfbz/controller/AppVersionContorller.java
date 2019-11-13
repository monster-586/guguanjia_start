package com.dfbz.controller;

import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("app/controller")
public class AppVersionContorller {
    @Autowired
    AppVersionService appVersionService;

    @RequestMapping("selectAll")
    @ResponseBody
    public PageInfo<AppVersion> selectAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "3") Integer pageSize) {
        PageInfo<AppVersion> pageInfo = appVersionService.selectAll(pageNum, pageSize);
        return pageInfo;
    }

    @RequestMapping("updata")
    @ResponseBody
    public String updateByPrimaryKey(@RequestBody AppVersion appVersion) {
        int i = appVersionService.updateByPrimaryKey(appVersion);
        String mag="操作失败";
        if (i>0){
        mag="操作成功";
        }
        return mag;
    }

    @RequestMapping("toupdata")
    @ResponseBody
    public AppVersion toUpdate(Long id){
        return appVersionService.selectByPrimaryKey(id);
    }}
