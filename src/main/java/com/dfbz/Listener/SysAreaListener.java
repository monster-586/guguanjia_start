package com.dfbz.Listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dfbz.dao.AreaMapper;
import com.dfbz.entity.Area;

import java.util.ArrayList;
import java.util.List;

public class SysAreaListener extends AnalysisEventListener<Area> {
    private AreaMapper areaMapper;
    private List<Area> batchAreas = new ArrayList<>();

    public SysAreaListener(AreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }

    public SysAreaListener() {
    }

    @Override
    public void invoke(Area area, AnalysisContext analysisContext) {
        /*每次读到数都会执行这个方法，再获取到数据,are为读取的当前数据，有
    EasyExcel.read("D:\\excel\\area.xlsx", Area.class, new SysAreaListener(areaMapper))，就能读取到area*/
        batchAreas.add(area);
        if (batchAreas.size() == 5){
            areaMapper.insertBatch(batchAreas);
            batchAreas.clear();
            System.out.println("读取 Excel方法");
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        /*所有数据读取完之后自动执行的方法*/
        if (batchAreas.size()>0){
            areaMapper.insertBatch(batchAreas);
        System.out.println("所有数据读完后 Excel方法");
        }
    }
}
