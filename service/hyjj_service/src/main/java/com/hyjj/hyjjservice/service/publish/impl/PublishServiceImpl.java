package com.hyjj.hyjjservice.service.publish.impl;

import com.hyjj.hyjjservice.controller.publlish.viewobject.PublishVO;
import com.hyjj.hyjjservice.dao.PublishKeywordMapper;
import com.hyjj.hyjjservice.dao.PublishMapper;
import com.hyjj.hyjjservice.dataobject.Publish;
import com.hyjj.hyjjservice.dataobject.PublishKeyword;
import com.hyjj.hyjjservice.service.common.UidService;
import com.hyjj.hyjjservice.service.publish.PublishService;
import com.hyjj.util.error.BusinessException;
import com.hyjj.util.error.EmBusinessError;
import com.hyjj.util.responce.CommonReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class PublishServiceImpl implements PublishService {

    final Logger log = LoggerFactory.getLogger(PublishServiceImpl.class);

    private final PublishMapper publishMapper;

    @Autowired
    private PublishKeywordMapper publishKeywordMapper;

    @Autowired
    private UidService uidService;

    @Value("${file.upload.url}")
    private String uploadUrl;

    private final String preFilePath = "publishimage";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public PublishServiceImpl(PublishMapper publishMapper) {
        this.publishMapper = publishMapper;
    }

    @Override
    public List<Publish> selectPublishToIndex() {
        List<Publish> publishes = publishMapper.selectPublishOrderByGmtCreate();
        if (publishes == null || publishes.isEmpty()) {
            log.info("没有查询到最新的publish");
        }
        return publishes;
    }

    @Override
    public String uploadImage(MultipartFile image,HttpServletRequest request) throws BusinessException{
        String realPath =  uploadUrl + preFilePath +"/";

        System.out.println(realPath);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

        // 并且在 uploadFile 文件夹中通过日期对上传的文件归类保存
        // 比如：/uploadFile/2019/06/06/32091e5f-c9e9-4506-9567-43e724f1fe37.png
        String format = sdf.format(new Date());
        File folder = new File(realPath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        MultipartFile uploadFile = image;
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
                        + ":" + request.getServerPort() + "/" + preFilePath +"/" + format + newName;
                System.out.println(filePath);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            throw new BusinessException(CommonReturnType.error(EmBusinessError.Image_Format_Error));
        }


        return filePath;
    }


    @Override
    @Transactional
    public boolean upLoadPublish(PublishVO publishVO) throws Exception {

        Publish publish = new Publish();
        BeanUtils.copyProperties(publishVO,publish);
        publish.setId(uidService.getUid());
        publish.setGmtCreate(new Date());
        publish.setGmtModified(new Date());
        int res = 0;
        res = publishMapper.insert(publish);
        if(res == 0){
            throw new BusinessException(CommonReturnType.error(EmBusinessError.PARAMETER_VALIDATION_ERROR));
        }

        List<PublishKeyword> list = new LinkedList<>();
        if(publishVO.getKeyWords() == null){
            return true;
        }
        for(String s : publishVO.getKeyWords()){
            PublishKeyword publishKeyword = new PublishKeyword();

            publishKeyword.setKeyword(s);
            publishKeyword.setGmtCreate(new Date());
            publishKeyword.setGmtModified(new Date());
            publishKeyword.setPublishId(publish.getId());
            list.add(publishKeyword);
        }

        if(list != null){
            res = publishKeywordMapper.insertBatch(list);
            if(res != list.size()){
                throw new BusinessException(CommonReturnType.error(EmBusinessError.PARAMETER_VALIDATION_ERROR));
            }
        }


        return true;
    }
}
