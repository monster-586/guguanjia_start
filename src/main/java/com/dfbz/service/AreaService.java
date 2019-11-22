package com.dfbz.service;


import com.dfbz.entity.Area;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface AreaService extends tservice<Area> {
    PageInfo<Area> selectByCondition(Map<String,Object> map);


}
