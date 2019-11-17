package com.dfbz.service;


import com.dfbz.entity.Qualification;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface QualificationService extends tservice<Qualification>{

PageInfo<Qualification> selsctByorder(Map<String,Object> params);

}
