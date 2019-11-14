package com.dfbz.controller;

import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.ws.api.pipe.ContentType;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("manager/app")
public class AppVersionContorller {
    @Autowired
    AppVersionService appVersionService;

    @RequestMapping("selectAll")
    @ResponseBody
    public PageInfo<AppVersion> selectAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "3") Integer pageSize) {
        PageInfo<AppVersion> pageInfo = appVersionService.selectAll(pageNum, pageSize);
        return pageInfo;
    }

    @RequestMapping(value = "updata",produces = "html/text;charset=utf-8")
    @ResponseBody
    public String updateByPrimaryKey(@RequestBody AppVersion appVersion) {
        int i = appVersionService.updateByPrimaryKeySelective(appVersion);
        String mag = "操作失败";
        if (i > 0) {
            mag = "操作成功";
        }
        return mag;
    }

    @RequestMapping(value = "toupdata")
    @ResponseBody
    public AppVersion toUpdate(Long id) {
        return appVersionService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "del",produces = "html/text;charset=utf-8")
    @ResponseBody
    public String deleteByPrimaryKey(Long id) {
        int i=appVersionService.deleteByPrimaryKey(id);
        String mag = "操作失败";
        if (i > 0) {
            mag = "操作成功";
        }
        return mag;
    }

}
