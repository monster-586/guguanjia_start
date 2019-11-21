package com.dfbz.service;


import com.dfbz.dao.DetailMapper;
import com.dfbz.dao.TransferMapper;
import com.dfbz.entity.Detail;
import com.dfbz.entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransferServiceImpl extends tservisceIpm<Transfer> implements TransferService {



    @Autowired
    TransferMapper transferMapper;
    @Override
    public List<Transfer> selectOneByCondition(Integer id) {
        return transferMapper.selectOneByCondition(id);
    }
//@Override

}
