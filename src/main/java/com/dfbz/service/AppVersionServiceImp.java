package com.dfbz.service;


import com.dfbz.entity.AppVersion;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppVersionServiceImp extends tservisceIpm<AppVersion> implements AppVersionService {

    @Override
    public PageInfo<AppVersion> selectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AppVersion> list = mapper.selectAll();
        PageInfo<AppVersion> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
