package com.dfbz.service;

import com.dfbz.entity.AppVersion;
import com.github.pagehelper.PageInfo;



public interface AppVersionService extends tservice<AppVersion>{


    PageInfo<AppVersion> selectAll(Integer pageNum,Integer pageSize);
}
