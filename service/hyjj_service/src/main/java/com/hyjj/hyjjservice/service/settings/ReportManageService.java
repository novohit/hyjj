package com.hyjj.hyjjservice.service.settings;

import com.hyjj.hyjjservice.dataobject.Formula;

import java.util.List;

public interface ReportManageService {
    List<Formula> getFormulaList();
    List<Formula> getFormulaByFormName(String formName);
    int addFormula(Formula formula, String formName);
}
