package com.hyjj.hyjjservice.service.fill.impl;

import com.hyjj.hyjjservice.dao.ProcessMapper;
import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dao.ReportTemplateMapper;
import com.hyjj.hyjjservice.dataobject.Process;
import com.hyjj.hyjjservice.dataobject.ReportDataHtml;
import com.hyjj.hyjjservice.dataobject.ReportDataList;
import com.hyjj.hyjjservice.dataobject.ReportTemplate;
import com.hyjj.hyjjservice.service.fill.FillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FillServiceImpl implements FillService {

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private ReportTemplateMapper reportTemplateMapper;

    @Override
    public List<ReportDataList> getReportListByUserId(Long userId) {
        return reportDataMapper.getReportDataListByUserId(userId);
    }

    @Override
    public ReportDataHtml getReportDataHtml(Integer id, Long userId) {
        return reportDataMapper.getReportDataHtml(id, userId);
    }

    @Override
    public int saveReportDataHtml(ReportDataHtml reportDataHtml) {
        return reportDataMapper.saveReportDataHtml(reportDataHtml);
    }

    @Override
    public int submitReportData(ReportDataHtml reportDataHtml) {
        Integer j = reportDataMapper.saveReportDataHtml(reportDataHtml);
        if (!j.equals(1)) {
            return 0;
        }
        Date date = new Date();
        Process process = new Process();
        process.setProcessName("审核");
        process.setProsessDescription("审核");
        process.setUpdateTime(date);
        int i = processMapper.updateProcessByReportId(reportDataHtml.getId(), process);
        return i;
    }

    @Override
    public int clearReportData(Integer reportId){
        return reportDataMapper.clearReportDataByReportId(reportId);
    }

    @Override
    public ReportTemplate getRowAndColByTemplateId(Integer reportId) {
        return reportDataMapper.getRowAndColByTemplateId(reportId);
    }

    @Override
    public String getReportNumber(Long reportTemplateId) {
        return reportTemplateMapper.getReportNumber(reportTemplateId);
    }
}
