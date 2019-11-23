package com.dfbz.dao;

import com.dfbz.entity.Area;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

public class AreaSqlProvider {
    public String selectByCondition(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("select sa.*,la.name parentName " +
                "from sys_area sa " +
                "left join " +
                "sys_area la " +
                "on sa.parent_id=la.id ");
        if (!StringUtils.isEmpty(map.get("treeId"))) {
            sb.append("where sa.id=#{treeId}");
        }
        if (!StringUtils.isEmpty(map.get("areaName"))) {
            sb.append("where sa.name like concat('%',#{areaName},'%') ");
        }
        return sb.toString();
    }

    public String insertBatch(@Param("Area") List<Area> Area) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `sys_area`( `parent_id`, `parent_ids`, `code`, `name`, `type`, `create_by`, `create_date`, " +
                "`update_by`, `update_date`, `remarks`, `del_flag`, `icon`) VALUES ");

        for (int i = 0; i < Area.size(); i++) {
            sb.append("(");
            sb.append("#{ Area[" + i + "].parentId}," +
                    "#{ Area[" + i + "].parentIds}," +
                    "#{ Area[" + i + "].code}," +
                    "#{ Area[" + i + "].name}," +
                    "#{ Area[" + i + "].type}," +
                    "#{ Area[" + i + "].createBy}," +
                    "#{ Area[" + i + "].createDate}," +
                    "#{ Area[" + i + "].updateBy}," +
                    "#{ Area[" + i + "].updateDate}," +
                    "#{ Area[" + i + "].remarks}," +
                    "#{ Area[" + i + "].delFlag}," +
                    "#{ Area[" + i + "].icon}");
            sb.append("),");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
        return sb.toString();

    }
}
