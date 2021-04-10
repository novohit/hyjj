package com.hyjj.hyjjservice.service.company;

import com.hyjj.hyjjservice.controller.company.viewObject.CompanyInfoPo;
import com.hyjj.hyjjservice.dataobject.ComInfo;

import java.util.List;

public interface CompanyInfoService {

    List<ComInfo> selectByIndustryEtc(CompanyInfoPo companyInfoPo, int page, int size);

    List<ComInfo> selectAllCompany();
}
