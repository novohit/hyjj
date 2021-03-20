package com.hyjj.hyjjservice.service.company.impl;

import com.hyjj.hyjjservice.controller.company.viewobject.CompanyInfoPo;
import com.hyjj.hyjjservice.controller.company.viewobject.CompanyInfoVo;
import com.hyjj.hyjjservice.dao.ComInfoMapper;
import com.hyjj.hyjjservice.dao.param.ComInfoQueryPo;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.service.company.CompanyInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    private final Logger log = LoggerFactory.getLogger(CompanyInfoServiceImpl.class);

    @Autowired
    public ComInfoMapper comInfoMapper;

    @Override
    public List<ComInfo> selectByIndustryEtc(CompanyInfoPo companyInfoPo, int page, int size) {
        ComInfoQueryPo comInfoQueryPo = new ComInfoQueryPo();
        //解析comTypes
        LinkedList<Integer> comTypes = new LinkedList<>();
        String bitmap = companyInfoPo.getComTypes();

        //判断是否没有0，即全1，全1即条件永真，不传参数到mapper即可，避免sql拼接
        boolean hasZero = false;
        for (int i = 0; i < bitmap.length(); i++) {
            char bit = bitmap.charAt(i);
            if (bit == '1') {
                comTypes.add(i);
            } else {
                hasZero = true;
            }
        }
        log.debug("comTypes bitmap：{}", comTypes);
        //- 不含有0的话，条件永真，避免拼接
        if (!hasZero) {
            comInfoQueryPo.setComTypes(null);
        } else {
            comInfoQueryPo.setComTypes(comTypes);
        }

        //解析industrys
        hasZero = false;
        LinkedList<Integer> industrys = new LinkedList<>();
        bitmap = companyInfoPo.getIndustrys();
        for (int i = 0; i < bitmap.length(); i++) {
            char bit = bitmap.charAt(i);
            if (bit == '1') {
                industrys.add(i + 1);
            } else {
                hasZero = true;
            }
        }
        log.debug("industries bitmap：{}", industrys);
        //- 上面有解释
        if (!hasZero) {
            comInfoQueryPo.setIndustryIds(null);
        } else {
            comInfoQueryPo.setIndustryIds(industrys);
        }

        //设置start，size，county，name
        comInfoQueryPo.setStart((long) (page * size));
        comInfoQueryPo.setSize((long) size);
        //设置name和county
        String county = companyInfoPo.getCounty();
        if (!StringUtils.isEmpty(county)) {
            comInfoQueryPo.setCounty(county);
        }
        String name = companyInfoPo.getName();
        if (!StringUtils.isEmpty(name)) {
            comInfoQueryPo.setCounty(name);
        }
        List<ComInfo> comInfos = comInfoMapper.selectByIndustryEtc(comInfoQueryPo);

        //确保不会返回null
        if (comInfos == null) {
            comInfos = new LinkedList<>();
        }
        log.debug("查询到[{}]个cominfo", comInfos.size());
        return comInfos;
    }

    @Override
    public List<ComInfo> selectAllCompany() {
        return comInfoMapper.selectAllCompany();
    }
}
