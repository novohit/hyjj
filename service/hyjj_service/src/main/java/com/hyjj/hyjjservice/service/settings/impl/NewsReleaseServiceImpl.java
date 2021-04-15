package com.hyjj.hyjjservice.service.settings.impl;

import com.hyjj.hyjjservice.dao.PublishMapper;
import com.hyjj.hyjjservice.dataobject.Publish;
import com.hyjj.hyjjservice.service.settings.NewsReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsReleaseServiceImpl implements NewsReleaseService {
    @Autowired
    PublishMapper publishMapper;
    @Override
    public List<Publish> getNewsTrends() {
        return publishMapper.selectNewsTrends();
    }
}
