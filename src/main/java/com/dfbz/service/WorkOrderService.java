package com.dfbz.service;



import com.dfbz.entity.WorkOrder;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


public interface WorkOrderService extends tservice<WorkOrder> {


    PageInfo<WorkOrder> selectByCondition(Map<String, Object> map);

    WorkOrder selectOneByCondition(Integer id);

}
