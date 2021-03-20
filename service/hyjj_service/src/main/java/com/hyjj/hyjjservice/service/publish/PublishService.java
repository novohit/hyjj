package com.hyjj.hyjjservice.service.publish;

import com.hyjj.hyjjservice.controller.publlish.viewobject.PublishVO;
import com.hyjj.hyjjservice.dataobject.Publish;
import com.hyjj.util.error.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PublishService {
    List<Publish> selectPublishToIndex();

    String uploadImage(MultipartFile image,HttpServletRequest request) throws BusinessException;


    boolean upLoadPublish(PublishVO publishVO) throws Exception;
}
