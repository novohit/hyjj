package com.hyjj.hyjjservice.service.settings.impl;

import com.github.pagehelper.PageHelper;
import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.controller.settings.viewObject.ReportTemplateInfoVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.ReportTemplateVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.UnitFillReportVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.FormulaListVO;
import com.hyjj.hyjjservice.dao.*;
import com.hyjj.hyjjservice.dataobject.*;
import com.hyjj.hyjjservice.dataobject.Process;
import com.hyjj.hyjjservice.service.settings.ReportManageService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReportManageServiceImpl implements ReportManageService {


    @Autowired
    private FormulaMapper formulaMapper;

    @Autowired
    private ReportTemplateMapper reportTemplateMapper;

    @Autowired
    private ComInfoMapper comInfoMapper;

    @Autowired
    private ComFillReportMapper comFillReportMapper;

    @Autowired
    private GdpMapper gdpMapper;

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<FormulaListVO> getFormulaList(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize==null?10:pageSize);
        List<FormulaListVO> formulaList = formulaMapper.getFormulaList();
        return formulaList;
    }

    @Override
    public List<FormulaListVO> getFormulaByFormName(String formName,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum, pageSize==null?10:pageSize);
        List<FormulaListVO> formulaByFormName = formulaMapper.getFormulaByFormName(formName);
        return formulaByFormName;
    }


    @Override
    public List<ReportTemplateVO> getReportTemplateList(ReportTemplateVO reportTemplateVO){
        PageHelper.startPage(reportTemplateVO.getPageNum(), reportTemplateVO.getPageSize()==null?10: reportTemplateVO.getPageSize());
        List<ReportTemplateVO> reportTemplateList = reportTemplateMapper.getReportTemplateList(reportTemplateVO);
        return reportTemplateList;
    }

    @Override
    public Boolean setFillReportList(Long comInfoId, String reportIds) {
        if(reportIds == null||reportIds == ""){
            int i = comFillReportMapper.deleteAllFillReport(comInfoId);
            return i != 0;
        }
        Date date = new Date();
        String[] stringSplit = reportIds.split(",");
        List<Integer> newReportIds = new ArrayList<>();
        List<Integer> oldReportIds = comFillReportMapper.selectReportTemplateId(comInfoId);
        for (String s : stringSplit) {
            boolean isRepeat = false;
            for (int i = 0; i < oldReportIds.size(); i++) {
                if(oldReportIds.get(i).equals(Integer.parseInt(s))){
                    isRepeat = true;
                    oldReportIds.remove(i);
                }
            }
            if(!isRepeat){
                newReportIds.add(Integer.parseInt(s));
            }
        }
        int tag1 = 0;
        if(newReportIds.size() != 0){
            for (Integer integer : newReportIds) {
                comFillReportMapper.insertFillReport(comInfoId,integer,date);
                tag1++;
            }
        }
        int tag2 = 0;
        if(oldReportIds.size() != 0){
            int tag = 0;
            for (Integer integer : oldReportIds) {
                comFillReportMapper.deleteFillReport(comInfoId,integer);
                tag2++;
            }
        }
        Boolean isMember = redisTemplate.boundSetOps("NewUserComInfoId").isMember(comInfoId);
        if(isMember){
            try{
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH,1);
                manualCreateReport(dateFormat.format(calendar.getTime()),comInfoId);
                redisTemplate.boundSetOps("NewUserComInfoId").remove(comInfoId);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return tag1 == newReportIds.size()&&tag2 == oldReportIds.size();
    }

    @Override
    public List<UnitFillReportVO> getNeededFillList(Long id) {
        List<UnitFillReportVO> unitFillReportVOS = comFillReportMapper.selectFillReport(id);
        return unitFillReportVOS;
    }

    @Override
    public List<UnitFillReportVO> getNotNeededFillList(Long id) {
        List<UnitFillReportVO> unitFillReportVOS = comFillReportMapper.selectNotFillReport(id);
        return unitFillReportVOS;
    }

    @Override
    public List<ComInfo> getComInfoList(String name,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize==null?10:pageSize);
        List<ComInfo> comInfos = comInfoMapper.getCompanyNameList(name);
        return comInfos;
    }

    @Override
    public ReportTemplateInfoVO getReportTemplateInfo(Integer id){
        ReportTemplateInfoVO reportTemplateInfo = reportTemplateMapper.getReportTemplateInfo(id);
        return reportTemplateInfo;
    }

    @Override
    public List<Gdp> getCurrentYearData(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize==null?10:pageSize);
        List<Gdp> gdps = gdpMapper.selectCurrentYearData();
        return gdps;
    }

    @Override
    public int updateGdpData(Gdp gdpObj) {
        Date date = new Date();
        gdpObj.setGmtModified(date);
        return gdpMapper.updateGdpData(gdpObj);
    }

    @Override
    public int deleteGdpDataById(Integer id) {
        return gdpMapper.deleteGdpDataById(id);
    }

    @Override
    public int insertGdpDataById(Gdp gdpObj) {
        Date date = new Date();
        gdpObj.setGmtCreate(date);
        return gdpMapper.insertGdpData(gdpObj);
    }

    @Override
    public List<Gdp> searchGdpData(String district, String year,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize==null?10:pageSize);
        return gdpMapper.searchGdpData(district, year);
    }



    @Override
    public List<Gdp> getPassYearData(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize==null?10:pageSize);
        List<Gdp> gdps = gdpMapper.selectPassYearData();
        return gdps;
    }

    @Override
    public Gdp getGdpDataById(Integer id) {
        return gdpMapper.selectGdpDataById(id);
    }

    @Override
    public boolean manualCreateReport(String endDate, Long id) throws Exception{
        User user = userMapper.selectByComInfoId(id);
        if(user == null){
            return false;
        }
        Date beginDate = new Date();
        ReportData reportData = new ReportData();
        Process process = new Process();
        process.setProcessName("填报数据");
        process.setProsessDescription("填报数据");
        process.setCreatTime(beginDate);
        process.setGmtCreate(beginDate);
        endDate = endDate +" 00:00:00";
        ComInfo comInfo = comInfoMapper.selectByPrimaryKey(id);
        reportData.setFillUnit(comInfo.getComName());
        reportData.setAreaName(comInfo.getComAddressCounty());
        reportData.setEnterpriseName(comInfo.getComName());
        reportData.setOrgCode(comInfo.getComCode());
        reportData.setBeginDate(beginDate);
        reportData.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate));
        reportData.setGmtModified(null);
        reportData.setReportDate(beginDate);
        reportData.setProStatus("填报数据");
        reportData.setProStatusName("1");
        reportData.setAreaCode("04");
        reportData.setGmtCreate(beginDate);
        reportData.setExpireDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate));
        reportData.setDataFrom("null");
        reportData.setFillPerson(user.getName());
        reportData.setIsSave(0);
        reportData.setUserId(user.getId());
        List<Integer> integers = comFillReportMapper.selectReportTemplateId(id);
        System.out.println(integers);
        int i = 0;
        for (Integer integer : integers) {
            processMapper.insertSelective(process);
            ReportTemplate reportTemplate = reportTemplateMapper.selectByPrimaryKey(integer.longValue());
            reportData.setProcessId(process.getId());
            reportData.setHeadHtml(reportTemplate.getHeadHtml());
            reportData.setBodyHtml(reportTemplate.getBodyHtml());
            reportData.setTailHtml(reportTemplate.getTailHtml());
            reportData.setTitle(reportTemplate.getTitle());
            reportData.setNumber(reportTemplate.getNumber());
            reportData.setReportTemplateId(reportTemplate.getId());
            process.setId(null);
            if(reportDataMapper.insertSelective(reportData)==1)
                i++;
        }
            return i==integers.size();



    }

    @Override
    public int getFormulaListSum() {
        return formulaMapper.getFormulaListSum();
    }

    @Override
    public int getSearchFormulaSum(String formName) {
        return formulaMapper.getSearchFormulaListSum(formName);
    }

    @Override
    public int getCurrentYearDataSum() {
        return gdpMapper.getCurrentYearGdpDataSum();
    }

    @Override
    public int getPassYearDataSum() {
        return gdpMapper.getPassYearGdpDataSum();
    }

    @Override
    public int getSearchGpaDataSum(String district, String year) {
        return gdpMapper.getSearchGdpDataSum(district,year);
    }

    @Override
    public int getReportTemplateListSum(ReportTemplateVO reportTemplateVO) {
        return reportTemplateMapper.getReportTemplateListSum(reportTemplateVO);
    }

    @Override
    public int getComInfoListSum(String name) {
        return comInfoMapper.getCompanyNameListSum(name);
    }



    @Scheduled(cron = "0 0 0 1 * ?")
    public void autoCreateReport() throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Long> ids = comInfoMapper.selectComInfoIds();
        for (Long id : ids) {
            System.out.println(id);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,1);
        for (Long id : ids) {
            manualCreateReport(dateFormat.format(calendar.getTime()),id);
        }

    }

}
