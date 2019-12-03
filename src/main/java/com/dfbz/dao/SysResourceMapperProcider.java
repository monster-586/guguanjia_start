package com.dfbz.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public class SysResourceMapperProcider {
    public String insertBatch(@Param("roleId") Long roleId, @Param("Resources")List<Long> Resources){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `sys_role_resource`( `role_id`, `resource_id`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`) VALUES ");
        for (int i = 0; i < Resources.size(); i++) {
            sb.append("(#{roleId},#{Resources["+i+"]},null,null,null,null,0),");
        };
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();

    }
}
