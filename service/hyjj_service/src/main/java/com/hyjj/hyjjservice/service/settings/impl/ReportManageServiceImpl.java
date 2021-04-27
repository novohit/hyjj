package com.hyjj.hyjjservice.service.settings.impl;

import com.github.pagehelper.PageHelper;
import com.hyjj.hyjjservice.controller.settings.viewObject.ReportTemplateInfoVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.ReportTemplateVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.UnitFillReportVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.FormulaListVO;
import com.hyjj.hyjjservice.dao.ComFillReportMapper;
import com.hyjj.hyjjservice.dao.ComInfoMapper;
import com.hyjj.hyjjservice.dao.FormulaMapper;
import com.hyjj.hyjjservice.dao.ReportTemplateMapper;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.dataobject.Formula;
import com.hyjj.hyjjservice.service.settings.ReportManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<FormulaListVO> getFormulaList(Integer id,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize==null?10:pageSize);
        List<FormulaListVO> formulaList = formulaMapper.getFormulaList(id);
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
        if(reportIds == ""){
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
    public List<ComInfo> getComInfoList(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize==0?10:pageSize);
        List<ComInfo> comInfos = comInfoMapper.getCompanyNameList();
        return comInfos;
    }

    @Override
    public ReportTemplateInfoVO getReportTemplateInfo(Integer id){
        ReportTemplateInfoVO reportTemplateInfo = reportTemplateMapper.getReportTemplateInfo(id);
        System.out.println(reportTemplateInfo);
        return reportTemplateInfo;
    }
}
