package com.hyjj.hyjjservice.controller.company;

import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.controller.company.viewObject.*;
import com.hyjj.hyjjservice.controller.fill.util.FileUtil;
import com.hyjj.hyjjservice.dao.ReportTemplateMapper;
import com.hyjj.hyjjservice.dao.param.ComInfoQueryPo;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.dataobject.Industry;
import com.hyjj.hyjjservice.dataobject.ReportTemplate;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.dataobject.myEnum.ComBusinessStatus;
import com.hyjj.hyjjservice.dataobject.myEnum.Comtype;
import com.hyjj.hyjjservice.service.company.CompanyInfoService;
import com.hyjj.hyjjservice.service.company.CompanyService;
import com.hyjj.hyjjservice.service.company.IndustryService;
import com.hyjj.hyjjservice.service.company.model.CompanyAnalyseModel;
import com.hyjj.hyjjservice.service.company.model.DeatailComInfoModel;
import com.hyjj.hyjjservice.service.fill.FillService;
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

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("company")
@Validated
@ControllerAdvice
@Api(tags = "企业分析")
public class CompanyController {

    private final Logger log = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private IndustryService industryService;

    @Autowired
    private CompanyInfoService companyInfoService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private FillService fillService;

    @Autowired
    private ReportTemplateMapper reportTemplateMapper;

    @Resource(name = "userThreadLocal")
    private ThreadLocal<User> threadLocal;

    @GetMapping("industry")
    @ApiOperation("获取行业信息")
    public CommonReturnType getIndustry() {

        List<Industry> industries = industryService.getIndustry();
        List<IndustryVO> res = new LinkedList<>();
        for (Industry i : industries) {
            IndustryVO iv = new IndustryVO();
            BeanUtils.copyProperties(i, iv);

            res.add(iv);
        }

        return CommonReturnType.ok().add("industries", res);
    }


    @GetMapping("detailCominfo")
    @ApiOperation("获取详细的企业信息")
    public CommonReturnType getDetailComInfo(Long id) {

        DeatailComInfoModel deatailComInfoModel = companyService.getDeatailComInfo(id);
        if (deatailComInfoModel == null) {
            return CommonReturnType.error();
        }
        DetailComInfoVO detailComInfoVO = new DetailComInfoVO();
        BeanUtils.copyProperties(deatailComInfoModel, detailComInfoVO);
        if (detailComInfoVO.getBussiness() == null) {
            detailComInfoVO.setBussiness(new LinkedList<>());
        }

        int length = detailComInfoVO.getBussiness().size();
        for (int i = length; i < 3; i++) {
            detailComInfoVO.getBussiness().add("");
        }

        return CommonReturnType.ok().add("detailComInfo", detailComInfoVO);
    }


    @GetMapping("analyse")
    @ApiOperation("分析企业信息")
    public CommonReturnType analyseCompany(@ApiParam("分析的对象，例如所属行业 ，地区，单位类型 分别对应(0,1,2)") Integer type, String dis) {
        List<CompanyAnalyseModel> res = companyService.getAnalyseData(type, "江门市".equals(dis) ? null : dis);
        return CommonReturnType.ok().add("analyseData", res);
    }

    @GetMapping("update")
    public CommonReturnType update(HttpServletRequest request) throws Exception {
        String fileName = "D:\\WeChat Files\\wxid_173poa14wlvf22\\FileStorage\\File\\2021-10\\系统导入数据.txt";

        // 读取文件内容到Stream流中，按行读取
        Stream<String> lines = Files.lines(Paths.get(fileName));
        Map<String, Integer> map = new HashMap<>(15);
        map.put("海洋渔业",1);
        map.put("海洋油气业",2);
        map.put("海洋矿业",3);
        map.put("海洋盐业",4);
        map.put("海洋船舶工业",5);
        map.put("海洋化工业",6);
        map.put("海洋药物和生物制品业",7);
        map.put("海洋工程建筑业",8);
        map.put("海洋可再生能源利用业",9);
        map.put("海水利用业",10);
        map.put("海洋交通运输业",11);
        map.put("海洋旅游业",12);
        map.put("海洋工程装备业",13);


        // 随机行顺序进行数据处理
        CompanyVO companyVO = new CompanyVO();
        lines.forEach(ele -> {
            String[] strings = ele.split("\t");
            companyVO.setComName(strings[1]);
            companyVO.setComCode(strings[2]);
            companyVO.setComComtype(strings[3]);
            //System.out.println(map.get(strings[4]));
            companyVO.setIndustryId(map.containsKey(strings[4]) ? map.get(strings[4]) : 14);
            companyVO.setComRegaddrXian(strings[5]);
            companyVO.setComAddressXian(strings[6]);
            companyVO.setComAddressXiang(strings[7]);
            try {
                addOrUpdateCompany(companyVO,1,request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return CommonReturnType.ok();
    }

    @PostMapping
    @ApiOperation("添加企业")
    public CommonReturnType addOrUpdateCompany(@ApiParam("表单提交的数据") CompanyVO companyVO, @ApiParam("修改0 添加1") Integer jude, HttpServletRequest request) throws Exception {

        boolean res = companyService.addOrUpdateCompany(companyVO, jude, request);

        if (!res) {
            return CommonReturnType.error();
        }
        return CommonReturnType.ok();

    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除企业")
    public CommonReturnType deleteCompany(@PathVariable(name = "id") Long id) {

        boolean res = companyService.deleteCompany(id);

        if (!res) {
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
                if (comType.equals("0") || comType.equals("1") ||comType.equals("2") || comType.equals("3"))
                    companyInfoVo.setComComtype(Comtype.getNameByOrdinal(comType));
                else companyInfoVo.setComComtype(comType);
            }

            //转换并设置营业状态
            String comBusinessStatus = comInfo.getComBusinessstatus();
            if (StringUtils.isNotEmpty(comBusinessStatus)) {
                String name = ComBusinessStatus.getNameByOrdinal(comBusinessStatus);
                companyInfoVo.setComBusinessstatus(name);
            }

            return companyInfoVo;
        }).collect(Collectors.toList());
        log.debug("响应companyVos为：{}", collect);
        return CommonReturnType.ok().add("companies", collect);
    }

    @ExceptionHandler(Exception.class)
    public CommonReturnType exceptionHandler(Exception e) {
        if (e instanceof AccessDeniedException) {
            log.error(e.getMessage());
            return CommonReturnType.error(EmBusinessError.USER_DONOT_HVER_PERMISSION);
        }
        else if (e instanceof BusinessException) {
            log.error(((BusinessException) (e)).getErrMsg());
            CommonReturnType commonReturnType = ((BusinessException) (e)).getCommonReturnType();
            if (commonReturnType.getCode() == 10003) {
                return commonReturnType;
            }
        } else {
            e.printStackTrace();
        }
        return CommonReturnType.error(EmBusinessError.PARAMETER_VALIDATION_ERROR);
    }

    @GetMapping("list")
    @GetUser
    public CommonReturnType getCompanyList(){
        User user = threadLocal.get();
        return CommonReturnType.ok().add("list",companyService.getCompanyList(user.getCominfoId()));
    }


    @GetMapping("sum")
    @ApiOperation("查询企业总数，则分页总数为sum / 每页的大小（向上取整）")
    public CommonReturnType getCompanyCount(@Valid CompanyInfoPo companyInfoPo) {
        Long companyCounty = companyService.selectCountCompany(companyInfoPo);
        log.debug("查询到companyCount：{}", companyCounty);
        return CommonReturnType.ok().add("sum", companyCounty);
    }

    @PostMapping("/download")
    @ApiOperation("导出excel/下载")
    public void download(HttpServletResponse response) {
        //TODO 记得把表存入服务器
        fileUtil.download("新增企业信息", response);
    }

    @PostMapping("/upload")
    @ApiOperation("导入excel/上传")
    public CommonReturnType upload(MultipartFile file) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(file.getBytes());
        ReportTemplate rowAndCol = reportTemplateMapper.getRowAndColByTemplateId(35);
        rowAndCol.setCol(rowAndCol.getDataCol());
        rowAndCol.setRow(rowAndCol.getDataRow());
        List<Object> cellList = fileUtil.getCellList(rowAndCol, inputStream);
        return CommonReturnType.ok().add("value", cellList);
    }
}
