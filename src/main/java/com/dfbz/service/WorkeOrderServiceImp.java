package com.dfbz.service;


import com.dfbz.dao.WorkOrderMapper;
import com.dfbz.entity.WorkOrder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class WorkeOrderServiceImp extends tservisceIpm<WorkOrder> implements WorkOrderService {
    @Autowired
    WorkOrderMapper wormapper;

    @Override
    public  PageInfo<WorkOrder> selectByCondition(Map<String, Object> map) {
        System.out.println(map);
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 5);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));

        List<WorkOrder> list = wormapper.selectByCondition(map);
        System.out.println(list);
        PageInfo<WorkOrder> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}
