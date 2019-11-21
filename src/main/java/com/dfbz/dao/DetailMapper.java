package com.dfbz.dao;

import com.dfbz.entity.Detail;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DetailMapper extends Mapper<Detail> {


    @Select("select de.* ,wa.code detailCode,wt.name detailNa,wt.code detailMe " +
            "from detail de " +
            "left join waste wa " +
            "on de.waste_id=wa.id " +
            "left join waste_type wt " +
            "on de.waste_type_id=wt.id " +
            "where de.del_flag=0 " +
             "and de.work_order_id=#{id}"    )
    List<Detail> selectOneByCondition(Integer id);
}