package com.dfbz.service;

import com.dfbz.dao.AppVersionMapper;
import com.dfbz.entity.AppVersion;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppVersionServiceImp extends tservisceIpm implements AppVersionService {


    @Override
    public PageInfo<AppVersion> selectAll(Integer pageNum, Integer pageSize) {
        return null;
    }
}
