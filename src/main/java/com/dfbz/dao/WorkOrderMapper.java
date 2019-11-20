package com.dfbz.dao;


import com.dfbz.entity.WorkOrder;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface WorkOrderMapper extends Mapper<WorkOrder> {
    @SelectProvider(type = WorkOrderSqlProvider.class, method = "selectByCondition")
    List<WorkOrder> selectByCondition(Map<String, Object> map);
}