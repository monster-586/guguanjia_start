package com.dfbz.service;

import com.dfbz.entity.Area;
import com.dfbz.entity.SysOffice;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "area")//设置全局缓存配置
public class SysOfficeServiceImpl extends tservisceIpm<SysOffice> implements SysOfficeService {


}
