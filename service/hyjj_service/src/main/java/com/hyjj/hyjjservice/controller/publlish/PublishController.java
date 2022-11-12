package com.hyjj.hyjjservice.controller.publlish;

import com.hyjj.hyjjservice.controller.company.CompanyController;
import com.hyjj.hyjjservice.controller.publlish.viewobject.PublishVO;
import com.hyjj.hyjjservice.dataobject.Publish;
import com.hyjj.hyjjservice.service.publish.PublishService;
import com.hyjj.util.error.BusinessException;
import com.hyjj.util.error.EmBusinessError;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("publish")
@Api(tags = "新闻发布相关的")
public class PublishController {

    private final PublishService publishService;

    private final Logger log = LoggerFactory.getLogger(CompanyController.class);

    public PublishController(PublishService publishService) {
        this.publishService = publishService;
    }

    @ApiOperation(value = "首页获取最新的8条新闻")
    @GetMapping
    public CommonReturnType getPublishToIndex() {
        return  CommonReturnType.ok().add("journalism",publishService.selectPublishToIndex());
    }


    @ApiOperation(value = "上传图片")
    @PostMapping("image")
    public CommonReturnType uploadImage(MultipartFile image, HttpServletRequest request) throws Exception{
        String path = publishService.uploadImage(image,request);
        return CommonReturnType.ok().add("path",path);
    }

    @ApiOperation(value = "上传新闻发布信息")
    @PostMapping("news")
    public CommonReturnType uploadPublish(PublishVO publishVO) throws Exception{
        boolean jude = publishService.upLoadPublish(publishVO);
        if(!jude){
            return CommonReturnType.error(EmBusinessError.UNKNOWN_ERROR);
        }
        return CommonReturnType.ok();
    }

    // TODO 异常捕获不要写这
    @ExceptionHandler(Exception.class)
    public CommonReturnType exceptionHandler(Exception e) {
        if(e instanceof BusinessException){
            log.error(((BusinessException)(e)).getErrMsg());
            CommonReturnType commonReturnType = ((BusinessException)(e)).getCommonReturnType();
            if(commonReturnType.getCode() == 10003){
                return commonReturnType;
            }

        }
        else{
            log.error(e.getMessage());

        }
        return CommonReturnType.error(EmBusinessError.PARAMETER_VALIDATION_ERROR);
    }


}
