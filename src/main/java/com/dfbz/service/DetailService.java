package com.dfbz.service;

import com.dfbz.entity.Detail;

import java.util.List;

public interface DetailService extends tservice<Detail> {

    List<Detail> selectOneByCondition(Integer id);
}
