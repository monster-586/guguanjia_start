package com.dfbz.dao;

import org.springframework.util.StringUtils;

import java.util.Map;

public class WorkOrderSqlProvider {
    public String selectByCondition(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select wo.*,su.name createName, tu.name transportName,ru.name recipientName,su.phone phone ,so.name createOffice " +
                " from work_order wo " +
                " left join sys_user tu " +
                " on wo.transport_user_id=tu.id " +
                " left join sys_user ru " +
                " on wo.recipient_user_id=ru.id " +
                " left join sys_user su " +
                " on wo.create_user_id=su.id  " +
                " left join sys_office so " +
                " on su.office_id=so.id " +
                " where wo.del_flag=0" );
        if (!StringUtils.isEmpty(map.get("status"))) {
            sb.append(" and wo.status=#{status}");
        }
        if (!StringUtils.isEmpty(map.get("officeName"))) {
            sb.append(" and su.name like concat('%','#{officeName}','%')");
        }
        if (!StringUtils.isEmpty(map.get("startDate"))) {
            sb.append(" and wo.create_date>#{startDate}");
        }
        if (!StringUtils.isEmpty(map.get("endDate"))) {
            sb.append(" and wo.create_date<#{endDate}");
        }

        return sb.toString();

    }
}
