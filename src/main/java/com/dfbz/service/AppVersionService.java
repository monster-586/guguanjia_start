package com.dfbz.service;

import com.dfbz.entity.AppVersion;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AppVersionService {
    PageInfo<AppVersion> selectAll(Integer pageNum,Integer pageSize);
}
