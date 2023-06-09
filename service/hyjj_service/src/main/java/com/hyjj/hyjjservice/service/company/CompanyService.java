package com.hyjj.hyjjservice.service.company;

import com.hyjj.hyjjservice.controller.company.viewObject.CompanyInfo;
import com.hyjj.hyjjservice.controller.company.viewObject.CompanyInfoPo;
import com.hyjj.hyjjservice.controller.company.viewObject.CompanyInfoVo;
import com.hyjj.hyjjservice.controller.company.viewObject.CompanyVO;
import com.hyjj.hyjjservice.dao.param.ComInfoQueryPo;
import com.hyjj.hyjjservice.service.company.model.CompanyAnalyseModel;
import com.hyjj.hyjjservice.service.company.model.DeatailComInfoModel;
import com.hyjj.util.error.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CompanyService {

    List<CompanyAnalyseModel> getAnalyseData(Integer type, String dis);
    boolean addOrUpdateCompany(CompanyVO companyVO,Integer jude, HttpServletRequest request) throws BusinessException;
    //boolean update(CompanyVO companyVO, HttpServletRequest request);
    Long selectCountCompany(CompanyInfoPo queryPo);

    DeatailComInfoModel getDeatailComInfo(Long id);

    CompanyInfo getCompanyList(Long id);

    boolean deleteCompany(Long id);
}
