package com.hyjj.hyjjservice.controller.user;


import com.google.code.kaptcha.impl.DefaultKaptcha;


import com.hyjj.hyjjservice.controller.user.viewobject.CaptchaVO;
import com.hyjj.hyjjservice.service.user.CaptchaService;
import com.hyjj.util.responce.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private DefaultKaptcha producer;

    @Autowired
    private CaptchaService captchaService;

    @ResponseBody
    @GetMapping("/get")
    public CommonReturnType getCaptcha() throws IOException {

        // 生成文字验证码
        String content = producer.createText();
        System.out.println(content);
        // 生成图片验证码
        ByteArrayOutputStream outputStream = null;
        BufferedImage image = producer.createImage(content);

        outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray()).replace("\n", "").replace("\r", "");

        CaptchaVO captchaVO  = captchaService.cacheCaptcha(content);
        captchaVO.setBase64Img(base64Img);

        return  CommonReturnType.ok().add("captchaInformation",captchaVO);
    }


    @ResponseBody
    @GetMapping("/sayHello")
    public CommonReturnType sayHello() throws IOException {

      return CommonReturnType.ok().add("message","Hello");
    }

}


