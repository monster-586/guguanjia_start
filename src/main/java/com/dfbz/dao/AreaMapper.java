package com.dfbz.dao;

import com.dfbz.entity.Area;

import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface AreaMapper extends Mapper<Area> {
    @SelectProvider(type = AreaSqlProvider.class, method = "selectByCondition")
    List<Area> selectByCondition(Map<String, Object> map);

    @Select(" select sa.*,la.name parentName " +
            " from sys_area sa " +
            " left join " +
            " sys_area la " +
            " on sa.parent_id=la.id" +
            " where sa.id=#{id} ")
    Area selectOneById(Long id);

    @InsertProvider(type = AreaSqlProvider.class,method = "insertBatch")
     void insertBatch(@Param("Area") List<Area> Area);

    @Update("update  " +
            " sys_area " +
            "set " +
            " parent_ids = REPLACE(parent_ids,#{oldparentId},#{parentIds}) " +
            "where " +
            " parent_ids like concat('%',#{id},'%')")
    int updateAllParentIds(Area area);

    @Update(" update " +
            " sys_area " +
            " set " +
            " parent_id=#{parentId}," +
            " parent_ids = REPLACE(parent_ids,#{oldparentId},#{parentId})" +
            " where " +
            " id=#{id}")
    int updateOne(Area area);
}