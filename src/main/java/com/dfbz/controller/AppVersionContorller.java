package com.dfbz.controller;

import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AppVersionContorller {
    @Autowired
    AppVersionService appVersionService;

    @RequestMapping("index")
    @ResponseBody
    List<AppVersion> selectAll(){
        return appVersionService.selectAll();
    }
}
