package com.dfbz.controller;


import com.dfbz.entity.Area;
import com.dfbz.service.AreaService;
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
//        System.out.println("*1" + map);
//        System.out.println("*2" + map.get("pageNum"));
//        System.out.println("*3" + map.get("pageSize"));
//        System.out.println("*4" + map.get("treeId"));
//        System.out.println("*5" + map.get("areaName"));
        PageInfo<Area> pageInfo = areaService.selectByCondition(map);
        return pageInfo;
    }

    @RequestMapping("selectOneById")
    public Area selectOneById(@RequestParam Long id) {
//        System.out.println("selectOneById" + id);
        Area area = areaService.selectOneById(id);
        System.out.println("befor" + area);
        area.setOldparentIds(area.getParentIds());
        area.setOdlparentId(area.getParentId());
        System.out.println("afet" + area);

        return area;
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

    @RequestMapping(value = "update", produces = "html/text;charset=utf-8")
    public String insertSelective(@RequestBody Area area) {
        String msg = "操作失败";
        System.out.println("in" + area);
        int i = areaService.updateOne(area);
        Area area1 = areaService.selectOneById(area.getId());
        System.out.println("one" + area1);
        if (i > 0) {
            int j = areaService.updateAllParentIds(area1);
            System.out.println("all" + area1);
            if (j > 0) {
                msg = "操作失败";
            }
        }
        return msg;
    }


}
