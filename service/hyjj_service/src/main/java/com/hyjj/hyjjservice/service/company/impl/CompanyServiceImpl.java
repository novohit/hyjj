package com.hyjj.hyjjservice.service.company.impl;


import com.hyjj.hyjjservice.controller.company.viewObject.CompanyInfo;
import com.hyjj.hyjjservice.controller.company.viewObject.CompanyInfoPo;
import com.hyjj.hyjjservice.controller.company.viewObject.CompanyVO;
import com.hyjj.hyjjservice.dao.BusinessMapper;
import com.hyjj.hyjjservice.dao.ComInfoAppendixMapper;
import com.hyjj.hyjjservice.dao.ComInfoMapper;
import com.hyjj.hyjjservice.dao.param.ComInfoQueryPo;
import com.hyjj.hyjjservice.dataobject.Business;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.dataobject.ComInfoAppendix;
import com.hyjj.hyjjservice.dataobject.myEnum.Comtype;
import com.hyjj.hyjjservice.service.common.UidService;
import com.hyjj.hyjjservice.service.company.CompanyService;
import com.hyjj.hyjjservice.service.company.model.CompanyAnalyseModel;
import com.hyjj.hyjjservice.service.company.model.DeatailComInfoModel;
import com.hyjj.util.error.BusinessException;
import com.hyjj.util.error.EmBusinessError;
import com.hyjj.util.responce.CommonReturnType;
import com.hyjj.util.tool.RedisUtil;
import com.hyjj.util.validator.ValidatorImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private ComInfoMapper comInfoMapper;


    @Autowired
    private UidService uidService;


    @Autowired
    private BusinessMapper businessMapper;

    @Autowired
    private ComInfoAppendixMapper comInfoAppendixMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ValidatorImpl validator;

    private static final String IndustryAnalyseData_KEY = "analyseData:industryAnalyseData:";

    private static final String ComTypeAnalyseData_KEY = "analyseData:comTypeAnalyseData:";

    private static final String RegionAnalyseData_KEY = "analyseData:regionAnalyseData:";

    private static HashMap<Integer,String> comTypeToName =new HashMap<>();

    private static HashSet<String> countySet = new HashSet<>();

    final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Value("${file.upload.url}")
    private String uploadUrl;

    @PostConstruct
    private void init(){
        comTypeToName.put(0, Comtype.getNameByOrdinal(0));
        comTypeToName.put(1,Comtype.getNameByOrdinal(1));
        comTypeToName.put(2,Comtype.getNameByOrdinal(2));
        comTypeToName.put(3,Comtype.getNameByOrdinal(3));

        countySet.add("台山市");
        countySet.add("鹤山市");
        countySet.add("开平市");
        countySet.add("恩平市");
        countySet.add("新会区");
        countySet.add("蓬江区");
        countySet.add("江海区");


    }

    @Override
    public List<CompanyAnalyseModel> getAnalyseData(Integer type, String dis) {

        List<CompanyAnalyseModel> res = null;
        if(type == 0){
            res = comInfoMapper.countByIndustry(dis);
        }else if(type == 1){
            res = comInfoMapper.countByRegion();
        }else if(type == 2){
            res = comInfoMapper.countByByComType(dis);
            for(CompanyAnalyseModel c : res){
                if(c.getDataName().equals("0") || c.getDataName().equals("1") || c.getDataName().equals("2") || c.getDataName().equals("3"))
                    c.setDataName(comTypeToName.get(Integer.valueOf(c.getDataName())));
            }

        }
        getPercent(res);
        return res;
    }



    @Override
    public Long selectCountCompany(CompanyInfoPo companyInfoPo) {


        ComInfoQueryPo comInfoQueryPo = new ComInfoQueryPo();
        //解析comTypes
        LinkedList<Integer> comTypes = new LinkedList<>();
        String bitmap = companyInfoPo.getComTypes();

        //判断是否没有0，即全1，全1即条件永真，不传参数到mapper即可，避免sql拼接
        boolean hasZero = false;
        for (int i = 0; i < bitmap.length(); i++) {
            char bit = bitmap.charAt(i);
            if (bit == '1') {
                comTypes.add(i);
            } else {
                hasZero = true;
            }
        }

        //- 不含有0的话，条件永真，避免拼接
        if (!hasZero) {
            comInfoQueryPo.setComTypes(null);
        } else {
            comInfoQueryPo.setComTypes(comTypes);
        }

        //解析industrys
        hasZero = false;
        LinkedList<Integer> industrys = new LinkedList<>();
        bitmap = companyInfoPo.getIndustrys();
        for (int i = 0; i < bitmap.length(); i++) {
            char bit = bitmap.charAt(i);
            if (bit == '1') {
                industrys.add(i + 1);
            } else {
                hasZero = true;
            }
        }

        //- 上面有解释
        if (!hasZero) {
            comInfoQueryPo.setIndustryIds(null);
        } else {
            comInfoQueryPo.setIndustryIds(industrys);
        }


        //设置name和county
        String county = companyInfoPo.getCounty();
        if (!StringUtils.isEmpty(county)) {
            comInfoQueryPo.setCounty(county);
        }
        String name = companyInfoPo.getName();
        if (!StringUtils.isEmpty(name)) {
            comInfoQueryPo.setCounty("%"+name+"%");
        }

        return comInfoMapper.selectCountCompany(comInfoQueryPo);
    }

    @Override
    @Transactional
    public boolean addOrUpdateCompany(CompanyVO companyVO,Integer jude,HttpServletRequest request)  throws BusinessException{
        if(validator.validate(companyVO).isHasErrors()){
            throw  new BusinessException(CommonReturnType.error().setErrMsg(validator.validate(companyVO).getErrMsg()));
        }

        if(!countySet.contains(companyVO.getComAddressXian()) || !countySet.contains(companyVO.getComRegaddrXian())){
            logger.info("输入的县不合法");
            throw  new BusinessException(CommonReturnType.error());
        }

        if(companyVO.getComCreateyear() != null && !StringUtils.equals("",companyVO.getComCreateyear()) && (Integer.valueOf(companyVO.getComCreateyear()) < 0 || Integer.valueOf(companyVO.getComCreateyear()) - 1900 > new Date().getYear() )){
            logger.info("输入的注册年不合法");
            throw  new BusinessException(CommonReturnType.error());
        }


        if(companyVO.getComCreatemonth() != null && !StringUtils.equals("",companyVO.getComCreatemonth()) && (Integer.valueOf(companyVO.getComCreatemonth()) < 0 || Integer.valueOf(companyVO.getComCreatemonth()) > 12)){
            logger.info("输入的注册月不合法");
            throw  new BusinessException(CommonReturnType.error());
        }

        int tag = comInfoMapper.judgeComCode(companyVO.getComCode());
        if (tag == 1){
            return false;
        }

        // 上传的文件将保存在项目运行目录下的 uploadFile 文件夹，
        //String realPath = request.getSession().getServletContext().getRealPath("/companyimage/");
        String realPath =  uploadUrl + "companyimage/";
        System.out.println(realPath);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

        // 并且在 uploadFile 文件夹中通过日期对上传的文件归类保存
        // 比如：/uploadFile/2019/06/06/32091e5f-c9e9-4506-9567-43e724f1fe37.png
        String format = sdf.format(new Date());
        File folder = new File(realPath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        MultipartFile uploadFile = companyVO.getImage();
        String filePath = null;
        if(uploadFile != null && !"".equals(uploadFile.getOriginalFilename())){

            // 对上传的文件重命名，避免文件重名
            String oldName = uploadFile.getOriginalFilename();
            String type = oldName.indexOf(".") != -1 ? oldName.substring(oldName.lastIndexOf(".") + 1, oldName.length()) : null;
            if (!"GIF".equals(type.toUpperCase()) && !"PNG".equals(type.toUpperCase()) && !"JPG".equals(type.toUpperCase())&& !"PJP".equals(type.toUpperCase())&& !"PJPEG".equals(type.toUpperCase())&& !"JPEG".equals(type.toUpperCase())&& !"JFIF".equals(type.toUpperCase())) {
                throw new BusinessException(CommonReturnType.error(EmBusinessError.Image_Format_Error));
            }
            String newName = UUID.randomUUID().toString()
                    + oldName.substring(oldName.lastIndexOf("."), oldName.length());

            try {
                // 文件保存
                uploadFile.transferTo(new File(folder, newName));

                // 返回上传文件的访问路径
                filePath = request.getScheme() + "://" + request.getServerName()
                        + ":" + request.getServerPort() + "/companyimage/" + format + newName;
                System.out.println(filePath);

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        // jude为0表示更新，需要对id字段进行非空判断
        if(jude == 0){
            if(companyVO.getId() == null){
                throw new BusinessException(CommonReturnType.error(EmBusinessError.PARAMETER_VALIDATION_ERROR));
            }
            ComInfo comInfo = new ComInfo();
            BeanUtils.copyProperties(companyVO,comInfo);
            if(companyVO.getIndustryId()==14){
                comInfo.setIndustryId(35);
            }
            comInfo.setComAddressCounty(companyVO.getComAddressXian());
            comInfo.setGmtModified(new Date());
            comInfoMapper.updateByPrimaryKeySelective(comInfo);

            ComInfoAppendix comInfoAppendix = new ComInfoAppendix();
            BeanUtils.copyProperties(companyVO,comInfoAppendix);
            if(filePath != null){
                comInfoAppendix.setComImgurl(filePath);
            }
            comInfoAppendix.setGmtModified(new Date());
            comInfoAppendixMapper.updateByPrimaryKeySelective(comInfoAppendix);

            businessMapper.deleteByComInfoId(comInfo.getId());
            if(companyVO.getBussiness() != null && companyVO.getBussiness().size() != 0){
                for(int i = 0 ;i < companyVO.getBussiness().size();i++){
                    if(companyVO.getBussiness().get(i).equals("")){
                        continue;
                    }
                    Business business = new Business();
                    business.setBusinessText(companyVO.getBussiness().get(i));
                    business.setId(uidService.getUid());
                    business.setCominfoId(comInfo.getId());
                    business.setGmtCreate(new Date());
                    business.setGmtModified(new Date());
                    businessMapper.insert(business);
                }
            }
            return true;
        }else if(jude == 1){
            Long id = uidService.getUid();


            ComInfo comInfo = new ComInfo();
            BeanUtils.copyProperties(companyVO,comInfo);
            comInfo.setComAddressCounty(companyVO.getComAddressXian());
            comInfo.setId(id);
            comInfo.setGmtCreate(new Date());
            comInfo.setGmtModified(new Date());
            if(companyVO.getIndustryId()==14){
                comInfo.setIndustryId(35);
            }
            comInfoMapper.insert(comInfo);

            ComInfoAppendix comInfoAppendix = new ComInfoAppendix();
            BeanUtils.copyProperties(companyVO,comInfoAppendix);
            comInfoAppendix.setComImgurl(filePath);
            comInfoAppendix.setId(id);
            comInfoAppendix.setGmtCreate(new Date());
            comInfoAppendix.setGmtModified(new Date());
            comInfoAppendixMapper.insert(comInfoAppendix);

            if(companyVO.getBussiness() != null && companyVO.getBussiness().size() != 0){
                for(int i = 0 ;i < companyVO.getBussiness().size();i++){
                    if(companyVO.getBussiness().get(i).equals("")){
                        continue;
                    }
                    Business business = new Business();
                    business.setBusinessText(companyVO.getBussiness().get(i));
                    business.setId(uidService.getUid());
                    business.setCominfoId(comInfo.getId());
                    business.setGmtCreate(new Date());
                    business.setGmtModified(new Date());
                    businessMapper.insert(business);
                }
            }
            clearCache();
            return true;
        }





        return false;
    }


    @Override
    public DeatailComInfoModel getDeatailComInfo(Long id) {

        DeatailComInfoModel deatailComInfoModel =  comInfoAppendixMapper.selectDetailByPrimaryKey(id);

        List<String> bussiness = businessMapper.selectByComInfoId(id);

        deatailComInfoModel.setBussiness(bussiness);

        return deatailComInfoModel;
    }

    @Override
    public CompanyInfo getCompanyList(Long id) {
        CompanyInfo companyList = comInfoMapper.getCompanyList(id);
        if (companyList == null) {
            companyList = new CompanyInfo();
            companyList.setId(0L);
            companyList.setComName("have not this company");
            companyList.setName("have not this company");
        }
        return companyList;
    }

    private void getPercent(List<CompanyAnalyseModel> list){
        double count = 0;
        for(CompanyAnalyseModel c : list){
            count += c.getDataNum();
        }

        for(CompanyAnalyseModel c : list){
            c.setPercent(BigDecimal.valueOf((double)c.getDataNum() / count * 100).setScale(2,BigDecimal.ROUND_DOWN));
        }

        return;

    }


    private boolean clearCache(){
        redisUtil.del(IndustryAnalyseData_KEY,ComTypeAnalyseData_KEY,RegionAnalyseData_KEY);
        return true;
    }


}
