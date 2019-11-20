package com.dfbz.service;



import com.dfbz.entity.WorkOrder;
import com.github.pagehelper.PageInfo;

import java.util.Map;


public interface WorkOrderService extends tservice<WorkOrder> {

    PageInfo<WorkOrder> selectByCondition(Map<String, Object> map);

}
