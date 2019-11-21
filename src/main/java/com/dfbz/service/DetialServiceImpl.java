package com.dfbz.service;


import com.dfbz.dao.DetailMapper;
import com.dfbz.entity.Detail;
import com.dfbz.entity.Sysuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetialServiceImpl extends tservisceIpm<Detail> implements DetailService {



    @Autowired
    DetailMapper detailMapper;
    @Override
    public List<Detail> selectOneByCondition(Integer id) {
        return detailMapper.selectOneByCondition(id);
    }

//@Override


}
