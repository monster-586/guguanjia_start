package com.dfbz.service;


import com.dfbz.entity.SysOffice;

import java.util.List;


public interface SysOfficeService extends tservice<SysOffice> {

    int insertBatch(Long roleId, List<Long> Resources);


}
