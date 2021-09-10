package com.hyjj.hyjjservice.service.report.impl;

import com.github.pagehelper.PageHelper;
import com.hyjj.hyjjservice.controller.report.viewobject.AuditReportVO;
import com.hyjj.hyjjservice.controller.report.viewobject.AuditVO;
import com.hyjj.hyjjservice.controller.report.viewobject.Degital;
import com.hyjj.hyjjservice.dao.*;
import com.hyjj.hyjjservice.dataobject.*;
import com.hyjj.hyjjservice.dataobject.Process;
import com.hyjj.hyjjservice.service.report.AuditService;
import com.hyjj.hyjjservice.service.report.impl.factory.StrategyFactory;
import com.hyjj.util.Date.DateUtil;
import com.hyjj.util.error.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private StrategyFactory strategyFactory;

    @Autowired
    private IndustryMapper industryMapper;

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Autowired
    private ComInfoMapper comInfoMapper;

    @Autowired
    private ProcessMapper processMapper;

    private static final Character IS_SELECT = '1';
    private static final int INDUSTRY_NUMBER = 14;
    private static final int ALL_INDUSTRY_NUMBER = 35;

    @Override
    public List<Industry> getIndustry() {
        List<Industry> industries = industryMapper.selectSomeIndustry();
        Industry industry = new Industry();
        industry.setId(14);
        industry.setName("其他");
        industry.setGmtCreate(new Date());
        industries.add(industry);
        return industries;
    }

    @Override
    public List<ReportData> getStatement(Integer select, User user, Boolean isManager, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String audit = "填报数据";
        Long userId = user.getId();
        if (isManager) {
            audit = "审核数据";
            userId = null;
        }
        return strategyFactory.getStatementStrategy(select).getStatement(audit, userId);
    }

    @Override
    public List<AuditReportVO> getStatement(AuditVO auditVO, User user, int pageNum, int pageSize) throws BusinessException {

        PageHelper.startPage(pageNum, pageSize);
        //指定搜索某一年
        String year = auditVO.getYear() + "-01-01 00:00:00";
        String nextYear = (Integer.parseInt(auditVO.getYear()) + 1) + "-01-01 00:00:00";

        String status = "审核%";
        if (auditVO.getStatus() == 1) {
            status = "审核数据";
        } else if (auditVO.getStatus() == 2) {
            status = "审核通过";
        }

        //先查询出所有已选行业
        String industry = auditVO.getIndustry();
        //需要查询的行业id的集合
        List<Integer> industriesId = new ArrayList<>();
        if (industry.charAt(0) == IS_SELECT) {
            //全选的情况
            List<AuditReportVO> auditReportVOS = reportDataMapper.selectAllIndustryReportData(auditVO.getType(), status, year, nextYear);
            for (AuditReportVO auditReportVO : auditReportVOS) {
                auditReportVO.setBeginDate(DateUtil.changeDateToStringWithDate(auditReportVO.getReportDate()));
                auditReportVO.setReportDateString(DateUtil.changeDateToStringWithMonth(auditReportVO.getReportDate()));
                auditReportVO.setSubmitDateString(DateUtil.changeDateToStringWithDate(auditReportVO.getSubmitDate()));
            }

            return auditReportVOS;
        } else {
            for (int i = 1; i < INDUSTRY_NUMBER; i++) {
                if (industry.charAt(i) == IS_SELECT) {
                    industriesId.add(i);
                }
            }
            //勾选了其他选项
            if (industry.charAt(INDUSTRY_NUMBER) == IS_SELECT) {
                for (int i = INDUSTRY_NUMBER; i < ALL_INDUSTRY_NUMBER; i++) {
                    industriesId.add(i);
                }
            }
        }
        if (industriesId.size() == 0) {
            return null;
        }
        List<AuditReportVO> auditReportVOS = reportDataMapper.selectReportDataByIndustryId(industriesId, auditVO.getType(), status, year, nextYear);
        for (AuditReportVO auditReportVO : auditReportVOS) {
            auditReportVO.setBeginDate(DateUtil.changeDateToStringWithDate(auditReportVO.getReportDate()));
            auditReportVO.setReportDateString(DateUtil.changeDateToStringWithMonth(auditReportVO.getReportDate()));
            auditReportVO.setSubmitDateString(DateUtil.changeDateToStringWithDate(auditReportVO.getSubmitDate()));
        }
        return auditReportVOS;
    }

    @Override
    public ReportData getDetailReport(Long reportId) {
        return reportDataMapper.selectByPrimaryKey(reportId);
    }


    @Override
    public String batchAuditReport(Map<Long, Degital> map, User user) {
        for (Long reportId : map.keySet()) {
            auditReport(reportId, map.get(reportId).getJudge(), map.get(reportId).getTailHtml(), user);
        }
        return "audit success";
    }

    /**
     * 返回总记录数
     *
     * @param auditVO
     * @param user
     * @return
     */
    @Override
    public Integer getStatementSum(AuditVO auditVO, User user) {
        //指定搜索某一年
        String year = auditVO.getYear() + "-01-01 00:00:00";
        String nextYear = (Integer.parseInt(auditVO.getYear()) + 1) + "-01-01 00:00:00";

        String status = "审核%";
        if (auditVO.getStatus() == 1) {
            status = "审核数据";
        } else if (auditVO.getStatus() == 2) {
            status = "审核通过";
        }

        //先查询出所有已选行业
        String industry = auditVO.getIndustry();
        //需要查询的行业id的集合
        List<Integer> industriesId = new ArrayList<>();
        if (industry.charAt(0) == IS_SELECT) {
            return reportDataMapper.selectAllIndustryReportDataSum(auditVO.getType(), status, year, nextYear);
        } else {
            for (int i = 1; i < INDUSTRY_NUMBER; i++) {
                if (industry.charAt(i) == IS_SELECT) {
                    industriesId.add(i);
                }
            }
            //勾选了其他选项
            if (industry.charAt(INDUSTRY_NUMBER) == IS_SELECT) {
                for (int i = INDUSTRY_NUMBER; i < ALL_INDUSTRY_NUMBER; i++) {
                    industriesId.add(i);
                }
            }
        }
        if (industriesId.size() == 0) {
            return null;
        }
        return reportDataMapper.selectReportDataByIndustryIdSum(industriesId, auditVO.getType(), status, year, nextYear);

    }

    /**
     * 1.先查看该报表是否有流程记录，要是有则通过流程记录查询数据库，没有则新增流程记录
     * 2.修改流程，然后新增或插入
     *
     * @param reportId
     * @param judge
     * @param user
     * @return
     */
    @Override
    public String auditReport(Long reportId, Integer judge, String tailHtml, User user) {
        //获取当前时间
        Date date = new Date();
        Integer isSave;
        //根据报表id查询流程
        Process process = processMapper.selectByReportId(reportId);
        if (process == null) {
            process = new Process();
            process.setVersionNum(1);
            process.setCreatUser(user.getName());
            process.setCreatTime(date);
            process.setGmtCreate(date);
        } else {
            process.setVersionNum(process.getVersionNum() == null ? 1 : process.getVersionNum() + 1);
        }
        process.setUpdateUser(user.getName());
        process.setUpdateTime(date);
        process.setRemark("");
        process.setGmtModified(date);

        isSave = judge == 0 ? 0 : null;
        process.setProcessName(judge == 0 ? "审核不通过" : "审核通过");
        process.setProsessDescription(judge == 0 ? "审核通过" : "审核通过");

        //如果id为null说明这是一个现在 new 出来的流程状态，因此叫添加到数据库
        if (process.getId() == null) {
            processMapper.insertSelective(process);
        } else {
            processMapper.updateByPrimaryKeySelective(process);
        }

        reportDataMapper.updateProcessByReportId(reportId, process.getId(), process.getProcessName(), judge == 0 ? "2" : "3", tailHtml, isSave);

        return "audit success";
    }

    @Override
    public List<ComInfo> selectAllCompany() {
        return comInfoMapper.selectAllCompany();
    }
}