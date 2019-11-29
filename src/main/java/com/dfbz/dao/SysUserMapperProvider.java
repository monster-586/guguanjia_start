package com.dfbz.dao;

import org.springframework.util.StringUtils;

import java.util.Map;

public class SysUserMapperProvider {


    /**
     * 根据条件，动态生成sql语句
     * @param params
     * @return
     */
    public String selectByCondition(Map<String, Object> params){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " +
                " su.*,so.name officeName " +
                " FROM sys_user su " +
                " LEFT JOIN " +
                " sys_office so ON " +
                " su.office_id=so.id "
               );

        if(params.containsKey("roleId")&&!StringUtils.isEmpty(params.get("roleId"))){
            sb.append(" LEFT JOIN " +
                    " sys_user_role sur ON " +
                    " sur.user_id=su.id " +
                    " LEFT JOIN " +
                    " sys_role sr ON " +
                    " sur.role_id=sr.id " +
                    " where " +
                    " su.del_flag=0 ");
            sb.append(" and sr.id=#{roleId} ");
        }else{
            sb.append(" where su.del_flag=0  ");
        }
        if(params.containsKey("userId")&&!StringUtils.isEmpty(params.get("userId"))){
            sb.append(" and su.id=#{userId} ");
        }

        if(params.containsKey("officeId")&&!StringUtils.isEmpty(params.get("officeId"))){
            sb.append(" and so.id=#{officeId} ");
        }

        if(params.containsKey("userName")&&!StringUtils.isEmpty(params.get("userName"))){
            sb.append(" and su.name like CONCAT('%',#{userName},'%') ");
        }
        return sb.toString();
    }

}
