package com.dfbz.controller;


import com.dfbz.entity.Area;
import com.dfbz.entity.Sysuser;
import com.dfbz.service.AreaService;
import com.dfbz.service.SysuserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/area")
public class AreaController {

    @Autowired
    AreaService areaService;

    @RequestMapping("list")
    public List<Area> list() {
        return areaService.selectAll();
    }

    @RequestMapping("selectByCondition")
    public PageInfo<Area> selectByCondition(@RequestBody Map<String, Object> map) {
        System.out.println("*1" + map);
        System.out.println("*2" + map.get("pageNum"));
        System.out.println("*3" + map.get("pageSize"));
        System.out.println("*4" + map.get("treeId"));
        System.out.println("*5" + map.get("areaName"));
        PageInfo<Area> pageInfo = areaService.selectByCondition(map);
        return pageInfo;
    }

    @RequestMapping("selectOneById")
    public Area selectOneById(@RequestParam Long id) {
        System.out.println("selectOneById" + id);
        return areaService.selectOneById(id);
    }

    @RequestMapping("exportExcel")/*导出*/
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.addHeader("Content-Disposition", "attachment;filename=" + new String("area.xlsx".getBytes(), "ISO-8859-1"));
        areaService.ExcelWrite(response.getOutputStream());

    }

    @RequestMapping("importExcel")/*插入数据库*/
    public String importExcel(MultipartFile file) throws IOException {
        areaService.ExcelRead(file.getInputStream());
        String a = "执行了插入";
        return a;
    }


}
