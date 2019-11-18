package com.dfbz.controller;

import com.dfbz.entity.Qualification;
import com.dfbz.service.QualificationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("manager/qualification/index")
public class qualificationContrloller {

    @Autowired
    QualificationService quaService;

    @RequestMapping("selsctByorder")
    public PageInfo<Qualification> selsctByorder(@RequestBody Map<String, Object> params) {
        PageInfo<Qualification> quaPageInfo = quaService.selsctByorder(params);
        return quaPageInfo;
    }

    @RequestMapping("toUpdate")
    public Qualification toUpdate(@RequestParam Long id) {
        return quaService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "updateCheck", produces = "html/text;charset=utf-8")
    public String updatecheck(@RequestBody Qualification qualification) {
        Qualification qual = new Qualification();
        qual.setId(qualification.getId());
        Integer i = 0;
        if (qualification.getCheck() == 1) {
            qual.setCheck(1);
            qual.setDescription(qualification.getDescription());
            i = quaService.updateByPrimaryKeySelective(qual);
            if (i > 0) {
                return "资质改为通过审核";
            } else {
                return "操作失败";
            }

        } else {
            qual.setCheck(2);
            qual.setDescription(qualification.getDescription());
            i = quaService.updateByPrimaryKeySelective(qual);
            if (i > 0) {
                return "资质改为通过审核";
            } else {
                return "操作失败";
            }
        }

    }
}
