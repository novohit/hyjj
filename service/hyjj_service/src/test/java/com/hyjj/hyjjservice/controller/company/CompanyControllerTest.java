package com.hyjj.hyjjservice.controller.company;

import com.hyjj.hyjjservice.controller.company.viewObject.CompanyInfoPo;
import com.hyjj.util.responce.CommonReturnType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyControllerTest {


    @Autowired
    public CompanyController companyController;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void getCompanyByIndustryEtc() {
        CompanyInfoPo companyInfoPo = new CompanyInfoPo();
        companyInfoPo.setComTypes("1111");
        companyInfoPo.setIndustrys("1111111111111111111");
        companyInfoPo.setName("");
        companyInfoPo.setCounty("");
        CommonReturnType returnType = companyController.getCompanyByIndustryEtc(companyInfoPo, 0, 10);
        Object companyInfoVos = returnType.getData().get("companies");
        assertEquals(10, ((List)companyInfoVos).size());
        assertEquals(38L, returnType.getData().get("sum"));
    }


}