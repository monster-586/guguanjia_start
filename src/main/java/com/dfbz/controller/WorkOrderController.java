package com.dfbz.controller;


import com.dfbz.entity.WorkOrder;
import com.dfbz.service.WorkOrderService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("manager/company/work")
public class WorkOrderController {

    @Autowired
    WorkOrderService worService;

    @RequestMapping("selectByCondition")
    public PageInfo<WorkOrder> selectByCondition(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        PageInfo<WorkOrder> workOrderPageInfo = worService.selectByCondition(map);
        System.out.println(workOrderPageInfo);
        return workOrderPageInfo;
    }
    @RequestMapping("selectAll")
    public List<WorkOrder> selectAll(){
        List<WorkOrder> workOrders = worService.selectAll();
        return workOrders;
    }
    @RequestMapping("selectOneByCondition")
    public WorkOrder selectOneByCondition(@Param("id") Integer id){
        return worService.selectOneByCondition(id);
    }


}
