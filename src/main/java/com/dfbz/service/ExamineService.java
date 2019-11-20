package com.dfbz.service;


import com.dfbz.entity.Examine;

import com.github.pagehelper.PageInfo;

import java.util.Map;


public interface ExamineService extends tservice<Examine> {

    PageInfo<Examine> selectByCondition(Map<String, Object> map);


}
