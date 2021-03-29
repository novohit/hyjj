package com.hyjj.hyjjservice.service.report.impl;

import com.hyjj.hyjjservice.controller.report.viewobject.AuditVO;
import com.hyjj.hyjjservice.dao.*;
import com.hyjj.hyjjservice.dataobject.*;
import com.hyjj.hyjjservice.dataobject.Process;
import com.hyjj.hyjjservice.service.report.AuditService;
import com.hyjj.util.error.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AuditServiceImpl implements AuditService {

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

    @Autowired
    private UrgeDataMapper urgeDataMapper;


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
    public List<ReportData> getStatement(Integer select, User user) {
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(new Date());
        switch (select) {
            case 1:         //获取待审核列表
                return reportDataMapper.getStatement(null, null, "审核", user.getId());
            case 2:         //获取本周已审核列表
                return reportDataMapper.getStatement(g.get(Calendar.WEEK_OF_YEAR), null, "审核%", user.getId());
            case 3:         //获取本周已审核列表
                return reportDataMapper.getStatement(null, g.get(Calendar.MONTH), "审核%", user.getId());
            case 4:         //获取累计审核
                return reportDataMapper.getStatement(null, null, "审核%", user.getId());
            default:
                return null;
        }
    }

    @Override
    public List<ReportData> getStatement(AuditVO auditVO, User user) throws BusinessException {
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
            return reportDataMapper.selectAllIndustryReportData(auditVO.getType(), status, year, nextYear);
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
        return reportDataMapper.selectReportDataByIndustryId(industriesId, auditVO.getType(), status, year, nextYear);
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

    /**
     * 这个是增加催办名单，并不是获取催办名单= -
     *
     * @param user
     * @param year
     * @param company
     * @return
     */
    @Override
    public String urge(User user, String year, String company) {
        //指定搜索某一年
        String preYear = year + "-01-01 00:00:00";
        String nextYear = (Integer.parseInt(year) + 1) + "-01-01 00:00:00";
        Date date = new Date();

        UrgeData urgeData = new UrgeData();
        urgeData.setSendUser(user.getName());
        //根据用户id查询公司名称
        urgeData.setSendGroup(comInfoMapper.selectCompanyNameByUserId(user.getId()));
        urgeData.setAcceptGroup(company);
        urgeData.setCreateDate(date);
        urgeData.setIsRead((byte) 0);
        urgeData.setReatDate(date);
        urgeData.setGmtCreate(date);
        urgeData.setGmtModified(date);

        urgeDataMapper.insert(urgeData);
        return "urge success";
    }

    @Override
    public UrgeData getUrge(String year, String company) {
        //指定搜索某一年
        String preYear = year + "-01-01 00:00:00";
        String nextYear = (Integer.parseInt(year) + 1) + "-01-01 00:00:00";
        return urgeDataMapper.selectUrgeDataByYearAndCompanyName(preYear, nextYear, company);
    }
}
