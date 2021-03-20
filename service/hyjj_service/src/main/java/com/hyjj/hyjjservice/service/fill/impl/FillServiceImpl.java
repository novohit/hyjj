package com.hyjj.hyjjservice.service.fill.impl;

import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dataobject.ReportDataHtml;
import com.hyjj.hyjjservice.dataobject.ReportDataList;
import com.hyjj.hyjjservice.service.fill.FillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillServiceImpl implements FillService {

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Override
    public List<ReportDataList> getReportListByUserId(Long userId){
        return reportDataMapper.getReportDataListByUserId(userId);
    }

    @Override
    public ReportDataHtml getReportDataHtml(int id,Long userId){
        return reportDataMapper.getReportDataHtml(id,userId);
    }

    @Override
    public int saveReportDataHtml(ReportDataHtml reportDataHtml){
        return reportDataMapper.saveReportDataHtml(reportDataHtml);
    }

//    @Override
//    public int submitReportData(ReportDataHtml reportDataHtml){
//
//    }


}
