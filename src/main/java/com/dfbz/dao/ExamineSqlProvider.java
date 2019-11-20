package com.dfbz.dao;

import org.springframework.util.StringUtils;

import java.util.Map;

public class ExamineSqlProvider {

     public String selectByCondition(Map<String,Object> map){
        StringBuilder sb = new StringBuilder();
        sb.append(" select ex.*,su.name uname,so.name oname " +
                " from " +
                " examine ex,sys_user su,sys_office so  " +
                " where " +
                " ex.del_flag=0  " +
                " and ex.examine_user_id=su.id " +
                " and su.company_id=so.id ");
        if(!StringUtils.isEmpty(map.get("officeName"))){
            sb.append(" and so.name like concat('%',#{officeName},'%') ");
        }
        if(!StringUtils.isEmpty(map.get("uName"))){
            sb.append(" and su.name like concat('%',#{uName},'%') ");
        }
        if(!StringUtils.isEmpty(map.get("type"))){
            sb.append(" and ex.type=#{type} ");
        }

        return sb.toString();
    }
}
