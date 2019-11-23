package com.dfbz.service;



import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;

import com.alibaba.excel.write.metadata.WriteSheet;
import com.dfbz.Listener.SysAreaListener;
import com.dfbz.dao.AreaMapper;
import com.dfbz.entity.Area;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AreaServiceImpl extends tservisceIpm<Area> implements AreaService {

@Autowired
    AreaMapper areaMapper;
    @Override
    public PageInfo<Area> selectByCondition(Map<String, Object> map) {
        System.out.println(map);
        if(StringUtils.isEmpty(map.get("pageNum"))){
            map.put("pageNum",1);
        }
        if(StringUtils.isEmpty(map.get("pageSize"))){
            map.put("pageSize",5);
        }
        PageHelper.startPage((Integer) map.get("pageNum"),(Integer) map.get("pageSize"));
        System.out.println("*1**"+map);
        System.out.println("*2**"+map.get("pageNum"));
        System.out.println("*3**"+map.get("pageSize"));
        System.out.println("*4**"+map.get("treeId"));
        System.out.println("*5**"+map.get("areaName"));
        List<Area> areas = areaMapper.selectByCondition(map);
        PageInfo<Area> pageInfo = new PageInfo<>(areas);
        return pageInfo;
    }

    @Override
    public Area selectOneById(Long id) {
        return areaMapper.selectOneById(id);
    }

    @Override
    public void ExcelWrite(OutputStream outputStream) {
/*mapper确定需要读取的数据库，只要指定需要读取到的文件位置就完成读取*/
        List<Area> areas = areaMapper.selectAll();
        ExcelWriter excelWriter = EasyExcel.write(outputStream, Area.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0).build();
        excelWriter.write(areas,writeSheet);
        excelWriter.finish();
    }

    @Override
    public void ExcelRead(InputStream inputStream) {
        ExcelReader excelReader = EasyExcel.read(inputStream,Area.class,new SysAreaListener(areaMapper)).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        excelReader.finish();
    }




}
