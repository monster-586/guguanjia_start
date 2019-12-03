package com.dfbz.dao;


import com.dfbz.entity.SysOffice;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysOfficeMapper extends Mapper<SysOffice> {
    @InsertProvider(type=SysOfficeMapperProvider.class,method = "insertBatch")
    int insertBatch(@Param("roleId") Long roleId, @Param("offIds") List<Long> offIds);
}