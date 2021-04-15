package com.hyjj.hyjjservice.controller.settings;


import com.hyjj.hyjjservice.service.settings.NewsReleaseService;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings/news")
@Api(tags = "设置中的资料发布")
public class NewsReleaseController {
    @Autowired
    private NewsReleaseService newsReleaseService;

    @GetMapping("/getNewsList")
    public CommonReturnType getNews(){
        return CommonReturnType.ok().add("newsList",newsReleaseService.getNewsTrends());
    }
}
