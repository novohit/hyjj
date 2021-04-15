package com.hyjj.hyjjservice.service.report.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyjj.hyjjservice.controller.report.viewobject.AuditReportVO;
import com.hyjj.hyjjservice.controller.report.viewobject.AuditVO;
import com.hyjj.hyjjservice.dao.*;
import com.hyjj.hyjjservice.dataobject.*;
import com.hyjj.hyjjservice.dataobject.Process;
import com.hyjj.hyjjservice.service.report.AuditService;
import com.hyjj.hyjjservice.service.report.impl.factory.StrategyFactory;
import com.hyjj.hyjjservice.service.report.impl.strategy.GetStatementStrategy;
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
    private ComAuditReportMapper comAuditReportMapper;

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Autowired
    private ComInfoMapper comInfoMapper;

    @Autowired
    private ProcessMapper processMapper;


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

        GregorianCalendar g = new GregorianCalendar();
        g.setTime(new Date());
        String audit = "填报数据";
        Long userId = user.getId();
        if (isManager) {
            audit = "审核";
            userId = null;
        }

        GetStatementStrategy statementStrategy = strategyFactory.getStatementStrategy(select);
        return statementStrategy.getStatement(audit, userId);
        /*switch (select) {
            case 1:                //获取待审核/填报列表
                return reportDataMapper.getStatement(null, null, audit, userId);
            case 2:                //获取本周已审核/填报列表
                return reportDataMapper.getStatement(g.get(Calendar.WEEK_OF_YEAR) - 1, null, "审核%", userId);
            case 3:                //获取本周已审核/填报列表
                return reportDataMapper.getStatement(null, g.get(Calendar.MONTH) + 1, "审核%", userId);
            case 4:                //获取累计审核/填报
                return reportDataMapper.getStatement(null, null, "审核%", userId);
            default:
                return null;
        }*/
    }

    @Override
    public List<AuditReportVO> getStatement(AuditVO auditVO, User user, int pageNum, int pageSize) throws BusinessException {
        PageHelper.startPage(pageNum, pageSize);
        //指定搜索某一年
        String year = auditVO.getYear() + "-01-01 00:00:00";
        String nextYear = (Integer.parseInt(auditVO.getYear()) + 1) + "-01-01 00:00:00";

        String status = null;
        if (auditVO.getStatus() == 1) {
            status = "审核";
        } else if (auditVO.getStatus() == 2) {
            status = "审核通过";
        }
        //先查询出所有已选行业
        String industry = auditVO.getIndustry();
        //需要查询的行业id的集合
        List<Integer> industriesId = new ArrayList<>();
        if (industry.charAt(0) == '1') {
            //全选的情况
            List<AuditReportVO> auditReportVOS = reportDataMapper.selectAllIndustryReportData(auditVO.getType(), status, year, nextYear);
            for (AuditReportVO auditReportVO : auditReportVOS) {
                auditReportVO.setBeginDate(DateUtil.changeDateToStringWithDate(auditReportVO.getReportDate()));
                auditReportVO.setReportDateString(DateUtil.changeDateToStringWithMonth(auditReportVO.getReportDate()));
                auditReportVO.setSubmitDateString(DateUtil.changeDateToStringWithDate(auditReportVO.getSubmitDate()));
            }
            return auditReportVOS;
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
        if (industriesId.size() == 0)
            return null;
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
    public String auditReport(Long reportId, Integer judge, User user) {
        Date date = new Date();     //获取当前时间
        //根据报表id查询流程
        Process process = processMapper.selectByReportId(reportId);
        if (process == null) {
            process = new Process();
            process.setVersionNum(1);
            process.setCreatUser(user.getName());
            process.setCreatTime(date);
            process.setGmtCreate(date);
        } else {
            process.setVersionNum(process.getVersionNum() + 1);
        }
        process.setUpdateUser(user.getName());
        process.setUpdateTime(date);
        process.setRemark("");
        process.setGmtModified(date);

        if (judge == 0) {    //审核不通过
            process.setProcessName("审核不通过");
            process.setProsessDescription("审核不通过");
        } else {             //审核通过
            process.setProcessName("审核通过");
            process.setProsessDescription("审核通过");
        }
        if (process.getId() == null)    //如果id为null说明这是一个现在 new 出来的流程状态，因此叫添加到数据库
            processMapper.insertSelective(process);
        else
            processMapper.updateByPrimaryKeySelective(process);

        reportDataMapper.updateProcessByReportId(reportId, process.getId(), process.getProcessName(), process.getProcessName());

        return "audit success";
    }

    @Override
    public List<ComInfo> selectAllCompany() {
        return comInfoMapper.selectAllCompany();
    }
}
