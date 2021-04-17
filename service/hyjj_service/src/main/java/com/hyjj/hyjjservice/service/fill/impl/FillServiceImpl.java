package com.hyjj.hyjjservice.service.fill.impl;

import com.hyjj.hyjjservice.controller.fill.viewObject.ReportVO;
import com.hyjj.hyjjservice.controller.report.viewobject.AuditVO;
import com.hyjj.hyjjservice.dao.IndustryMapper;
import com.hyjj.hyjjservice.dao.ProcessMapper;
import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dao.ReportTemplateMapper;
import com.hyjj.hyjjservice.dataobject.*;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataHtml;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataList;
import com.hyjj.hyjjservice.dataobject.Process;
import com.hyjj.hyjjservice.service.fill.FillService;
import com.hyjj.util.error.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FillServiceImpl implements FillService {

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private IndustryMapper industryMapper;

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
    public List<String> getReportNumber(List<Long> reportTemplateId) {
        return reportTemplateMapper.getReportNumber(reportTemplateId);
    }


    @Override
    public List<ReportDataList> getStatement(ReportVO reportVO, User user) {
        //指定搜索某一年
        Long userId = user.getId();;
        String year = reportVO.getYear() + "-01-01 00:00:00";
        String nextYear = (Integer.parseInt(reportVO.getYear()) + 1) + "-01-01 00:00:00";

        String status = null;
        if (reportVO.getStatus() == 1) {
            status = "填报数据";
        } else if (reportVO.getStatus() == 2) {
            status = "未提交";
        } else if(reportVO.getStatus() == 3){
            status = "已入库";
        }
        //先查询出所有已选行业
        String industry = reportVO.getIndustry();
        //需要查询的行业id的集合
        List<Integer> industriesId = new ArrayList<>();
        if (industry.charAt(0) == '1') {
            //全选的情况
            return reportDataMapper.reportSelectAllIndustryReportData(reportVO.getType(), status, year, nextYear,userId);
        } else {
            for (int i = 1; i < 14; i++) {
                if (industry.charAt(i) == '1') {
                    industriesId.add(i);
                }
            }
            if (industry.charAt(14) == '1') {  //勾选了其他选项
                for (int i = 14; i < 35; i++) {
                    industriesId.add(i);
                }
            }
        }
        return reportDataMapper.reportSelectReportDataByIndustryId(industriesId, reportVO.getType(), status, year, nextYear, userId);
    }
}
