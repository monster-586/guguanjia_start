package com.dfbz.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public class SysOfficeMapperProvider {
    public String insertBatch(@Param("roleId") Long roleId, @Param("offIds") List<Long> offIds){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `sys_role_office`(`role_id`, `office_id`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`) VALUES");
        for (int i = 0; i < offIds.size(); i++) {
            sb.append("(#{roleId},#{offIds["+i+"]},null,null,null,null,0),");
        };
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
