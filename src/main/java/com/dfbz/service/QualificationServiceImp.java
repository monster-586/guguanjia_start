package com.dfbz.service;


import com.dfbz.dao.QualificationMapper;
import com.dfbz.dao.SysUserMapper;
import com.dfbz.entity.Qualification;

import com.dfbz.entity.SysUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class QualificationServiceImp extends tservisceIpm<Qualification> implements QualificationService {

    @Autowired
    QualificationMapper quaMapper;
    @Autowired
    SysUserMapper sysuserMapper;

    @Override
    public PageInfo<Qualification> selsctByorder(Map<String, Object> map) {
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 5);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));

        Example example = new Example(Qualification.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", 0);
        if (!StringUtils.isEmpty(map.get("check"))) {
            criteria.andEqualTo("check", map.get("check"));
        }
        if (!StringUtils.isEmpty(map.get("type"))) {
            criteria.andEqualTo("type", map.get("type"));
        }
        if (!StringUtils.isEmpty(map.get("starteDate"))) {
            criteria.andGreaterThan("createDate", map.get("starteDate"));
        }

        if (!StringUtils.isEmpty(map.get("endDate"))) {
            criteria.andLessThan("createDate", map.get("endDate"));
        }
        List<Qualification> qualifications = mapper.selectByExample(example);

        for (Qualification qualification : qualifications) {
            Qualification qualback = quaMapper.selectQuename(qualification.getId());
            qualification.setApplyName(qualback.getApplyName());
            qualification.setUserCheck(qualback.getUserCheck());
        }
        PageInfo<Qualification> quaPageInfo = new PageInfo<>(qualifications);

        return quaPageInfo;
    }

    @Value("${imgPath}")
    String imgPath;

    public Qualification selectByPrimaryKey(Object key) {
        //根据上传用户id关联用户表查询office_id
        Qualification qualification = mapper.selectByPrimaryKey(key);
        SysUser sysUser = sysuserMapper.selectByPrimaryKey(qualification.getUploadUserId());
        // uploads/文件夹名称(企业id)/用户图片名/ File.separator 是系统默认的文件分隔符号
        qualification.setAddress(imgPath + sysUser.getOfficeId() + File.separator + qualification.getAddress());
        return qualification;
    }



}
