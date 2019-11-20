package com.dfbz.controller;



import com.dfbz.entity.Sysuser;
import com.dfbz.service.SysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/sysuser")
public class SysuserController {

    @Autowired
    SysuserService syuService;

    @RequestMapping("list")
    public List<Sysuser> list (){
        return syuService.selectAll();
    }


}
