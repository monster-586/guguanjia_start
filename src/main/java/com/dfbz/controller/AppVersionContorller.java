package com.dfbz.controller;

import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AppVersionContorller {
    @Autowired
    AppVersionService appVersionService;

    @RequestMapping("index")
    @ResponseBody
    PageInfo<AppVersion> selectAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize")Integer pageSize){
        PageInfo<AppVersion> pageInfo = appVersionService.selectAll(pageNum, pageSize);
        return pageInfo;


    }
}
