package com.hyjj.hyjjservice.service.company.impl;

import com.hyjj.hyjjservice.dao.IndustryMapper;
import com.hyjj.hyjjservice.dataobject.Industry;
import com.hyjj.hyjjservice.service.company.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IndustryServiceImpl implements IndustryService {


    @Autowired
    private IndustryMapper industryMapper;

    @Override
    public List<Industry> getIndustry() {

        return industryMapper.selectAllIndustry();
    }
}
