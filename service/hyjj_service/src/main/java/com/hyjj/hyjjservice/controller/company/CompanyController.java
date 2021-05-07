package com.hyjj.hyjjservice.controller.company;

import com.hyjj.hyjjservice.controller.company.viewObject.*;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.dataobject.Industry;
import com.hyjj.hyjjservice.dataobject.myEnum.ComBusinessStatus;
import com.hyjj.hyjjservice.dataobject.myEnum.Comtype;
import com.hyjj.hyjjservice.service.company.CompanyInfoService;
import com.hyjj.hyjjservice.service.company.CompanyService;
import com.hyjj.hyjjservice.service.company.IndustryService;
import com.hyjj.hyjjservice.service.company.model.CompanyAnalyseModel;
import com.hyjj.hyjjservice.service.company.model.DeatailComInfoModel;
import com.hyjj.util.error.BusinessException;
import com.hyjj.util.error.EmBusinessError;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("company")
@Validated
@ControllerAdvice
@Api(tags = "企业分析")
public class CompanyController {

    private final Logger log = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private IndustryService industryService;

    @Autowired
    private CompanyInfoService companyInfoService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("industry")
    @ApiOperation("获取行业信息")
    public CommonReturnType getIndustry(){

        List<Industry> industries = industryService.getIndustry();
        List<IndustryVO> res = new LinkedList<>();
        for(Industry i : industries){
            IndustryVO iv = new IndustryVO();
            BeanUtils.copyProperties(i,iv);

            res.add(iv);
        }

        return CommonReturnType.ok().add("industries",res);
    }


    @GetMapping("detailCominfo")
    @ApiOperation("获取详细的企业信息")
    public CommonReturnType getDetailComInfo(Long id){

        DeatailComInfoModel deatailComInfoModel =  companyService.getDeatailComInfo(id);
        if(deatailComInfoModel == null){
            return CommonReturnType.error();
        }
        DetailComInfoVO detailComInfoVO = new DetailComInfoVO();
        BeanUtils.copyProperties(deatailComInfoModel,detailComInfoVO);
        if(detailComInfoVO.getBussiness() == null){
            detailComInfoVO.setBussiness(new LinkedList<>());
        }

        int length =detailComInfoVO.getBussiness().size();
        for(int i = length;i < 3;i++){
            detailComInfoVO.getBussiness().add("");
        }

        return CommonReturnType.ok().add("detailComInfo",detailComInfoVO);
    }


    @GetMapping("analyse")
    @ApiOperation("分析企业信息")
    public CommonReturnType analyseCompany(@ApiParam("分析的对象，例如所属行业 ，地区，单位类型 分别对应(0,1,2)") Integer type){


        List<CompanyAnalyseModel> res = companyService.getAnalyseData(type);
        return CommonReturnType.ok().add("analyseData",res).add("sum",companyService.selectCountCompany());
    }


    @PostMapping
    @ApiOperation("添加企业")
    public CommonReturnType addOrUpdateCompany(@ApiParam("表单提交的数据")CompanyVO companyVO,@ApiParam("修改0 添加1")Integer jude, HttpServletRequest request) throws Exception{

        boolean res =  companyService.addOrUpdateCompany(companyVO,jude,request);

        if(!res){
            return CommonReturnType.error();
        }
        return CommonReturnType.ok();

    }



    @GetMapping
    @ApiOperation("根据所属行业，单位类型，所属地区，企业名称等查询企业名录")
    @Transactional
    public CommonReturnType getCompanyByIndustryEtc(
            @Valid CompanyInfoPo companyInfoPo,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        //参数校验全放到controller，统一处理
        if (companyInfoPo == null ||
                companyInfoPo.getComTypes() == null ||
                companyInfoPo.getIndustrys() == null) {
            throw new IllegalArgumentException();
        }
        log.info("接收到请求参数{}, page: {}, size: {}", companyInfoPo, page, size);
    //分页查询
        List<ComInfo> comInfos = companyInfoService.selectByIndustryEtc(companyInfoPo, page, size);
        //转换Bo为Vo
        List<CompanyInfoVo> collect = comInfos.stream().map(comInfo -> {
            CompanyInfoVo companyInfoVo = new CompanyInfoVo();
            BeanUtils.copyProperties(comInfo, companyInfoVo);
            if (comInfo.getIndustry() != null) {
                companyInfoVo.setIndustryName(comInfo.getIndustry().getName());
            }
            //转换并设置单位类型
            String comType = comInfo.getComComtype();
            if (StringUtils.isNotEmpty(comType)) {
                companyInfoVo.setComComtype(Comtype.getNameByOrdinal(comType));
            }

            //转换并设置营业状态
            String comBusinessStatus = comInfo.getComBusinessstatus();
            if (StringUtils.isNotEmpty(comBusinessStatus)) {
                String name = ComBusinessStatus.getNameByOrdinal(comBusinessStatus);
                companyInfoVo.setComBusinessstatus(name);
            }

            return companyInfoVo;
        }).collect(Collectors.toList());
    //查询总数
        Long companyCounty = companyService.selectCountCompany();
        log.debug("响应到companyCount：{}", companyCounty);
        log.debug("响应companyVos为：{}", collect);
        return CommonReturnType.ok().add("companies", collect).add("sum", companyCounty);
    }

    @ExceptionHandler(Exception.class)
    public CommonReturnType exceptionHandler(Exception e) {
        if(e instanceof BusinessException){
            log.error(((BusinessException)(e)).getErrMsg());
            CommonReturnType commonReturnType = ((BusinessException)(e)).getCommonReturnType();
            if(commonReturnType.getCode() == 10003){
                return commonReturnType;
            }
        } else{
            log.error(e.getMessage());
        }
        return CommonReturnType.error(EmBusinessError.PARAMETER_VALIDATION_ERROR);
    }

    @GetMapping("/sum")
    @ApiOperation("查询企业总数，则分页总数为sum / 每页的大小（向上取整）")
    public CommonReturnType getCompanyCount() {
        Long companyCounty = companyService.selectCountCompany();
        log.debug("查询到companyCount：{}", companyCounty);
        return CommonReturnType.ok().add("sum", companyCounty);
    }
}
