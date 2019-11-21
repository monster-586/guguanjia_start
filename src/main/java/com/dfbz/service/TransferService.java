package com.dfbz.service;


import com.dfbz.entity.Transfer;

import java.util.List;

public interface TransferService extends tservice<Transfer> {

    List<Transfer> selectOneByCondition(Integer id);
}
