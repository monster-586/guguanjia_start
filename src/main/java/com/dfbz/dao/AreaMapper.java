package com.dfbz.dao;

import com.dfbz.entity.Area;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface AreaMapper extends Mapper<Area> {
    @SelectProvider(type = AreaMapperSqlProvider.class,method = "selectByCondition")
    List<Area> selectByCondition(Map<String,Object> map);

}