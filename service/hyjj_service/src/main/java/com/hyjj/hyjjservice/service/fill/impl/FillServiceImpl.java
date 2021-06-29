package com.hyjj.hyjjservice.service.fill.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hyjj.hyjjservice.controller.fill.dto.FormulaVerificationDto;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportVO;
import com.hyjj.hyjjservice.dao.ProcessMapper;
import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dao.ReportTemplateMapper;
import com.hyjj.hyjjservice.dataobject.*;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataHtml;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataList;
import com.hyjj.hyjjservice.dataobject.Process;
import com.hyjj.hyjjservice.service.fill.FillService;
import com.hyjj.hyjjservice.service.statistic.impl.factory.AddTargetStrategyFactory;
import com.hyjj.hyjjservice.service.statistic.impl.template.AbstractTargetTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FillServiceImpl implements FillService {

    @Autowired
    private AddTargetStrategyFactory addTargetStrategyFactory;

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private ReportTemplateMapper reportTemplateMapper;

    @Override
    public List<ReportDataList> getReportListByUserId(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize == null ? 10 : pageSize);
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
        int i = reportDataMapper.submitReportData(reportDataHtml);
        processMapper.updateProcessByReportId(reportDataHtml.getId(), process);
        return i;
    }

    @Override
    public int clearReportData(Integer reportId) {
        return reportDataMapper.clearReportDataByReportId(reportId);
    }

    @Override
    public ReportTemplate getRowAndColByReportId(Integer reportId) {
        return reportDataMapper.getRowAndColByReportId(reportId);
    }

    @Override
    public List<String> getReportNumber(List<Long> reportTemplateId) {
        return reportTemplateMapper.getReportNumber(reportTemplateId);
    }


    @Override
    public List<ReportDataList> getStatement(ReportVO reportVO, User user, Integer pageNum, Integer pageSize) {
        //指定搜索某一年
        Long userId = user.getId();

        String year = reportVO.getYear() + "-01-01 00:00:00";
        String nextYear = (Integer.parseInt(reportVO.getYear()) + 1) + "-01-01 00:00:00";

        String status = null;
        if (reportVO.getStatus() == 1) {
            status = "未填报";
        } else if (reportVO.getStatus() == 2) {
            status = "审核不通过";
        } else if (reportVO.getStatus() == 3) {
            status = "未提交";
        } else if (reportVO.getStatus() == 4) {
            status = "已入库";
        }
        //先查询出所有已选行业
        String industry = reportVO.getIndustry();
        //需要查询的行业id的集合
        List<Integer> industriesId = new ArrayList<>();
        if (industry.charAt(0) == '1') {
            //全选的情况
            PageHelper.startPage(pageNum, pageSize == null ? 10 : pageSize);
            return reportDataMapper.reportSelectAllIndustryReportData(reportVO.getType(), status, year, nextYear, userId);
        } else {
            for (int i = 1; i < 14; i++) {
                if (industry.charAt(i) == '1') {
                    industriesId.add(i);
                }
            }
            //勾选了其他选项
            if (industry.charAt(14) == '1') {
                for (int i = 14; i < 35; i++) {
                    industriesId.add(i);
                }
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        return reportDataMapper.reportSelectReportDataByIndustryId(industriesId, reportVO.getType(), status, year, nextYear, userId);
    }

    @Override
    public String formulaVerification(FormulaVerificationDto formulaVerificationDto) {
        AbstractTargetTemplate template = addTargetStrategyFactory.getAddTargetValueStatus(
                reportDataMapper.selectReportTemplateByReportId(formulaVerificationDto.getReportId()));
        return template.formulaVerification(formulaVerificationDto.getData()) ? "公式校验通过" : "公式校验不通过";
    }

//    @Override
//    public List<String> getFormHeadInfo(){
//
//    }
}
