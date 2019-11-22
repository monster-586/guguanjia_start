package com.dfbz.dao;

import org.springframework.util.StringUtils;

import java.util.Map;

public class AreaMapperSqlProvider {
    public String selectByCondition(Map<String,Object> map){
        StringBuilder sb = new StringBuilder();
        sb.append("select sa.*,la.name parentName " +
                "from sys_area sa " +
                "left join " +
                "sys_area la " +
                "on sa.parent_id=la.id ");
        if(!StringUtils.isEmpty(map.get("treeId"))){
            sb.append("where sa.id=#{treeId}");
        }
        if(!StringUtils.isEmpty(map.get("areaName"))){
            sb.append("where sa.name like concat('%',#{areaName},'%') ");
        }
        return sb.toString();
    }

}
