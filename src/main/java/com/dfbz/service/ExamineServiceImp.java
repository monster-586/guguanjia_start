package com.dfbz.service;

import com.dfbz.dao.ExamineMapper;
import com.dfbz.entity.Examine;
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
public class ExamineServiceImp extends tservisceIpm<Examine> implements ExamineService {
    @Autowired
    ExamineMapper mapper;

    @Override
    public PageInfo<Examine> selectByCondition(Map<String, Object> map) {
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 5);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));

        List<Examine> list = mapper.selectByCondition(map);
        PageInfo<Examine> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
