package com.dfbz.controller;

import com.dfbz.entity.Examine;
import com.dfbz.entity.Qualification;
import com.dfbz.service.ExamineService;
import com.dfbz.service.QualificationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("manager/examine/index")
public class ExamineController {

    @Autowired
    ExamineService exaService;

    @RequestMapping("selectByCondition")
    public PageInfo<Examine> selectByCondition(@RequestBody Map<String, Object> map) {

        PageInfo<Examine> examinePageInfo = exaService.selectByCondition(map);
        return examinePageInfo;
    }

}
