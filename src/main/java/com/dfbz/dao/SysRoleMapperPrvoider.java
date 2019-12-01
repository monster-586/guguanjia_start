package com.dfbz.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

public class SysRoleMapperPrvoider {
    public String selectByCondition(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("select sr.*,so.name officeName " +
                " from sys_role sr " +
                " left join  " +
                " sys_office so on " +
                " sr.office_id=so.id " +
                " where sr.del_flag=0");
        if (params.containsKey("offId") && !StringUtils.isEmpty(params.get("offId"))) {
            sb.append(" and so.id=#{offId}");
        }
        if (params.containsKey("roleType") && !StringUtils.isEmpty(params.get("roleType"))) {
            sb.append(" and sr.data_scope=#{roleType} ");
        }
        if (params.containsKey("roleId") && !StringUtils.isEmpty(params.get("roleId"))) {
            sb.append(" and sr.name like concat('%',#{roleName},'%') ");
        }
        if (params.containsKey("DesCri") && !StringUtils.isEmpty(params.get("DesCri"))) {
            sb.append(" and sr.remarks= #{DesCri}");
        }

        return sb.toString();


    }
    public String insertBatch(@Param("insertUserId") List<Long> insertUserId, @Param("roleId") long roleId){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `sys_user_role`( `role_id`, `user_id`, `create_by`, `create_date`, " +
                "`update_by`, `update_date`, `del_flag`) VALUES ");

        for (int i = 0; i < insertUserId.size(); i++) {
            sb.append("(#{roleId},#{insertUserId["+i+"]},null,now(),null,now(),0),");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

}
