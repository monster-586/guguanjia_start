package com.dfbz.dao;


import com.dfbz.entity.WorkOrder;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface WorkOrderMapper extends Mapper<WorkOrder> {
    @SelectProvider(type = WorkOrderSqlProvider.class, method = "selectByCondition")
    List<WorkOrder> selectByCondition(Map<String, Object> map);

@Select(" select wo.*,su.name createName, tu.name transportName,ru.name recipientName,su.phone phone ,so.name createOffice " +
        " from work_order wo " +
        " left join sys_user tu " +
        " on wo.transport_user_id=tu.id " +
        " left join sys_user ru " +
        " on wo.recipient_user_id=ru.id " +
        " left join sys_user su " +
        " on wo.create_user_id=su.id  " +
        " left join sys_office so " +
        " on su.office_id=so.id " +
        " where wo.del_flag=0" +
        "and wo.id=#{id} ")
    WorkOrder selectOneByCondition(Integer id);

}