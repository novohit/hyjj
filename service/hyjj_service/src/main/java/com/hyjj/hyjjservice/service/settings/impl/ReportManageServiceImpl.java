package com.hyjj.hyjjservice.service.settings.impl;

import com.hyjj.hyjjservice.dao.FormulaMapper;
import com.hyjj.hyjjservice.dataobject.Formula;
import com.hyjj.hyjjservice.service.settings.ReportManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportManageServiceImpl implements ReportManageService {

    @Autowired
    private FormulaMapper formulaMapper;

    @Override
    public List<Formula> getFormulaList() {
        List<Formula> formulaList = formulaMapper.getFormulaList();
        return formulaList;
    }

    @Override
    public List<Formula> getFormulaByFormName(String formName){
        List<Formula> formulaByFormName = formulaMapper.getFormulaByFormName(formName);
        return formulaByFormName;
    }

    @Override
    public int addFormula(Formula formula, String formName) {
        Date date = new Date();
        formula.setGmtCreate(date);
        return formulaMapper.addFormula(formula, formName);


    }
}
