package com.dfbz.service;


import com.dfbz.entity.Area;
import com.github.pagehelper.PageInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface AreaService extends tservice<Area> {
    PageInfo<Area> selectByCondition(Map<String, Object> map);


    Area selectOneById(Long id);

    void ExcelWrite(OutputStream outputStream);/*数据库读取到表格*/

    void ExcelRead(InputStream inputStream);/*读取表格的内容到数据库相应的表格*/


}
