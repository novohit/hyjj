package com.hyjj.hyjjservice.service.company.impl;

import com.hyjj.hyjjservice.HyjjApplication;
import com.hyjj.hyjjservice.controller.company.viewObject.CompanyInfoPo;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.service.company.CompanyInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContextManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@SpringBootTest(classes = HyjjApplication.class)
public class CompanyInfoServiceImplTest {

    @Autowired
    public CompanyInfoService companyInfoService;

    @Parameterized.Parameter(0)
    public CompanyInfoPo companyInfoPo;

    @Parameterized.Parameter(1)
    public int page;

    @Parameterized.Parameter(2)
    public int size;

    @Parameterized.Parameter(3)
    public int resultSize;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        //正常黑盒测试
        CompanyInfoPo companyInfoPo = new CompanyInfoPo();
        companyInfoPo.setComTypes("1011");
        companyInfoPo.setIndustrys("10100000000000000000010000000000000");
        companyInfoPo.setCounty("恩平");
        CompanyInfoPo companyInfoPo2 = new CompanyInfoPo();
        //测试全1
        companyInfoPo2.setComTypes("111");
        companyInfoPo2.setIndustrys("10100000000000000000010000000000000010000000000000");
        companyInfoPo2.setName("为");

        //测试空串
        CompanyInfoPo companyInfoPo3 = new CompanyInfoPo();
        companyInfoPo3.setComTypes("");
        companyInfoPo3.setIndustrys("");
        //测试全0
        return Arrays.asList(new Object[][]{{companyInfoPo, 0, 1, 1}, {companyInfoPo2, 0, 1, 1}, {companyInfoPo3, 2, 2, 2}});
    }

    /**
     * 加载Spring
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }

    //参数化测试
    @Test
    public void selectByIndustryEtc() {
        List<ComInfo> comInfos = companyInfoService.selectByIndustryEtc(companyInfoPo, page, size);
        assertEquals(resultSize, comInfos.size());
    }

    //异常测试，取消
    //@Test
    public void selectByIndustryEtcException() {
        try {
            CompanyInfoPo companyInfoPo = new CompanyInfoPo();
            companyInfoPo.setComTypes("");
            companyInfoService.selectByIndustryEtc(companyInfoPo, 10, 10);
            assertTrue(1 == 0);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }
}