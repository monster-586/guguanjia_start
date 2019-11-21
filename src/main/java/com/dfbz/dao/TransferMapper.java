package com.dfbz.dao;

import com.dfbz.entity.Transfer;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TransferMapper extends Mapper<Transfer> {
    @Select(" select tf.*,su.name oprateName ,su.phone opratePhone " +
            " from transfer tf " +
            " left join sys_user su " +
            " on tf.oprate_user_id=su.id " +
            " where tf.del_flag=0 " +
            " and tf.work_order_id=#{id}" +
            " order by" +
            " tf.create_date desc ")
    List<Transfer> selectOneByCondition(Integer id);
}