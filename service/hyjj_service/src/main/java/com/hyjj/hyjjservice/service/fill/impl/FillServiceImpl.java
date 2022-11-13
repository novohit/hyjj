package com.hyjj.hyjjservice.service.fill.impl;

import com.github.pagehelper.PageHelper;
import com.hyjj.hyjjservice.controller.fill.dto.FormulaVerificationDto;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportVO;
import com.hyjj.hyjjservice.controller.fill.viewObject.TableHeadInfo;
import com.hyjj.hyjjservice.dao.ComInfoMapper;
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

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private ComInfoMapper comInfoMapper;

    @Override
    public List<ReportDataList> getReportListByUserId(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize == null ? 10 : pageSize);
        return reportDataMapper.getReportDataListByUserId(userId)
                .stream()
                .sorted(Comparator.comparing(ReportDataList::getBeginDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public TableHeadInfo getTableHeadInfo(Long reportId) {
        try{
            TableHeadInfo tableHeadInfo = comInfoMapper.getTableHeadInfo(reportId);
            String organizationCode = tableHeadInfo.getOrganizationCode();
            if(organizationCode != null){
                tableHeadInfo.setCreditCode(organizationCode);
            }
            String creditCode = tableHeadInfo.getCreditCode();

            if(creditCode != null && creditCode.length() == 18){
                organizationCode = creditCode.substring(8,16);
                tableHeadInfo.setOrganizationCode(organizationCode);

            }
            return tableHeadInfo;

        }catch (Exception e){
            return null;
        }

    }

    @Override
    public ReportDataHtml getReportDataHtml(Long id, Long userId) {




        return reportDataMapper.getReportDataHtml(id.intValue(), userId);

    }

    @Override
    public ReportDataHtml getLastYearData(Long id, Long userId){
        int[] array = new int[]{16,17,18,19,20};
        ReportData reportData = reportDataMapper.selectByPrimaryKey(id);
        Date beginDate = reportData.getBeginDate();
        String[] strDate = new SimpleDateFormat("yyyy-MM-dd").format(beginDate).toString().split("-");
        int year = Integer.parseInt(strDate[0]);
        int month = Integer.parseInt(strDate[1]);
        Long reportTemplateId = reportData.getReportTemplateId();
        for (int i : array) {
            if(i == reportTemplateId){
                // TODO 这里数据库数据有问题 会有多条数据
                List<ReportDataHtml> lastYearReports = reportDataMapper.selectLastYearReport(userId, reportTemplateId, year - 1, month);
                if(lastYearReports != null && lastYearReports.size() != 0){
                    return lastYearReports.get(0);
                }
            }
        }
        return null;
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
            status = "待审核";
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
        System.out.println(formulaVerificationDto.getReportId());
        AbstractTargetTemplate template = addTargetStrategyFactory.getAddTargetValueStatus(
                reportDataMapper.selectReportTemplateByReportId(formulaVerificationDto.getReportId()));
        return template.formulaVerification(formulaVerificationDto.getData()) ? "公式校验通过" : "公式校验不通过";
    }

//    @Override
//    public List<String> getFormHeadInfo(){
//
//    }

    @Override
    public Integer getReportListByUserIdSum(Long userId) {
        return reportDataMapper.getReportDataListByUserIdSum(userId);
    }

    @Override
    public Integer getStatementSum(ReportVO reportVO, User user) {
        //指定搜索某一年
        Long userId = user.getId();
        System.out.println(userId);
        String year = reportVO.getYear() + "-01-01 00:00:00";
        String nextYear = (Integer.parseInt(reportVO.getYear()) + 1) + "-01-01 00:00:00";

        String status = null;
        if (reportVO.getStatus() == 1) {
            status = "未填报";
        } else if (reportVO.getStatus() == 2) {
            status = "审核不通过";
        } else if (reportVO.getStatus() == 3) {
            status = "待审核";
        } else if (reportVO.getStatus() == 4) {
            status = "已入库";
        }
        //先查询出所有已选行业
        String industry = reportVO.getIndustry();
        //需要查询的行业id的集合
        List<Integer> industriesId = new ArrayList<>();
        if (industry.charAt(0) == '1') {
            //全选的情况
            return reportDataMapper.reportSelectAllIndustryReportDataSum(reportVO.getType(), status, year, nextYear, userId);
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
        return reportDataMapper.reportSelectReportDataByIndustryIdSum(industriesId, reportVO.getType(), status, year, nextYear, userId);
    }
}
