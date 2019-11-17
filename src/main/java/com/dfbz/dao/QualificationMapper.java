package com.dfbz.dao;


import com.dfbz.entity.Qualification;
import com.dfbz.entity.Sysuser;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;



public interface QualificationMapper extends Mapper<Qualification> {
@Select(" SELECT   uu.`name` applyName,uu.username userCheck\n" +
        " FROM   \n" +
        "  qualification qu  \n" +
        " LEFT JOIN \n" +
        "  sys_user uu  \n" +
        " ON \n" +
        "  qu.upload_user_id=uu.id\n" +
        " WHERE\n" +
        "  qu.id=#{id}")
    public Qualification selectQuename(Long id);

}