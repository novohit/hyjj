package com.hyjj.hyjjservice.service.urge.impl;

import com.github.pagehelper.PageHelper;
import com.hyjj.hyjjservice.controller.urge.viewobject.UrgeReportVO;
import com.hyjj.hyjjservice.dao.ComInfoMapper;
import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.dao.UrgeDataMapper;
import com.hyjj.hyjjservice.dataobject.UrgeData;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.service.urge.UrgeService;
import com.hyjj.util.Date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UrgeServiceImpl implements UrgeService {
    @Autowired
    private ReportDataMapper reportDataMapper;

    @Autowired
    private ComInfoMapper comInfoMapper;

    @Autowired
    private UrgeDataMapper urgeDataMapper;

    @Override
    @Transactional
    public String urge(User user, List<String> company) {
        Date date = new Date();
        int result = 0;

        for (String s : company) {
            UrgeData urgeData = new UrgeData();
            urgeData.setSendUser(user.getName());
            //根据用户id查询公司名称
            urgeData.setSendGroup(comInfoMapper.selectCompanyNameByUserId(user.getId()));
            urgeData.setAcceptGroup(s);
            urgeData.setCreateDate(date);
            urgeData.setIsRead((byte) 0);
            urgeData.setReatDate(date);
            urgeData.setGmtCreate(date);
            urgeData.setGmtModified(date);

            result += urgeDataMapper.insert(urgeData);
        }

        return result > 0 ? "urge success" : "urge fail";
    }

    @Override
    public List<UrgeReportVO> getUrge(Integer year, String company, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if ("全部".equals(company)) {
            company = null;
        }
        List<UrgeReportVO> urgeReportVOS = reportDataMapper.selectByYearAndCompany(year, company);
        for (UrgeReportVO urgeReportVO : urgeReportVOS) {
            urgeReportVO.setReportDate(DateUtil.changeDateToStringWithMonth(urgeReportVO.getBeginDate()));
            urgeReportVO.setBeginDateToString(DateUtil.changeDateToStringWithDate(urgeReportVO.getBeginDate()));
            urgeReportVO.setEndDateToString(DateUtil.changeDateToStringWithDate(urgeReportVO.getEndDate()));
        }
        return urgeReportVOS;
    }

    @Override
    public List<UrgeData> urgeStatus(Long userId) {
        List<UrgeData> urgeData = urgeDataMapper.getUrgeData(userId);
        List<Long> ids = new ArrayList<>();
        for (UrgeData urgeDatum : urgeData)
            ids.add(urgeDatum.getId());
        if(ids.size() != 0) urgeDataMapper.updateByPrimaryKeysSelective(ids);
        return urgeData;
    }

    /**
     * 返回催办名单总条数
     *
     * @param year
     * @param company
     * @return
     */
    @Override
    public Integer getUrgeSum(Integer year, String company) {
        return reportDataMapper.selectByYearAndCompanySum(year, "全部".equals(company) ? null : company);
    }

    @Override
    public String urgeAll(User user,Integer year, String company) {
        if ("全部".equals(company)) {
            company = null;
        }
        List<UrgeReportVO> urgeReportVOS = reportDataMapper.selectByYearAndCompany(year, company);
        Set<String> companies = new HashSet<>();
        for (UrgeReportVO urgeReportVO : urgeReportVOS) {
            companies.add(urgeReportVO.getFillUnit());
        }
        String urge = urge(user, new ArrayList<>(companies));
        return urge;
    }
}
