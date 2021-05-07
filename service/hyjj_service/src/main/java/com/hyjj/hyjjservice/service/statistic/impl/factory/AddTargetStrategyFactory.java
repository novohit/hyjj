package com.hyjj.hyjjservice.service.statistic.impl.factory;

import com.hyjj.hyjjservice.service.statistic.impl.template.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AddTargetStrategyFactory implements InitializingBean {

    @Autowired
    TemplateId1 templateId1;
    @Autowired
    TemplateId6 templateId6;
    @Autowired
    TemplateId9 templateId9;
    @Autowired
    TemplateId10 templateId10;
    @Autowired
    TemplateId11 templateId11;
    @Autowired
    TemplateId13 templateId13;
    @Autowired
    TemplateId14 templateId14;
    @Autowired
    TemplateId16 templateId16;
    @Autowired
    TemplateId18 templateId18;
    @Autowired
    TemplateId19 templateId19;
    @Autowired
    TemplateId20 templateId20;
    @Autowired
    TemplateId21 templateId21;
    @Autowired
    TemplateId22 templateId22;
    @Autowired
    TemplateId23 templateId23;
    @Autowired
    TemplateId24 templateId24;
    @Autowired
    TemplateId25 templateId25;
    @Autowired
    TemplateId26 templateId26;
    @Autowired
    TemplateId27 templateId27;
    @Autowired
    TemplateId29 templateId29;
    @Autowired
    TemplateId30 templateId30;
    @Autowired
    TemplateId31 templateId31;
    @Autowired
    TemplateId32 templateId32;
    @Autowired
    TemplateId33 templateId33;


    private final Map<Long, AddTargetTemplate> map = new HashMap<>();

    public AddTargetTemplate getAddTargetValueStatus(Long id) {
        return map.get(id);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        map.put(1L, templateId1);
        map.put(6L, templateId6);
        map.put(9L, templateId9);
        map.put(10L, templateId10);
        map.put(11L, templateId11);
        map.put(13L, templateId13);
        map.put(14L, templateId14);
        map.put(16L, templateId16);
        map.put(18L, templateId18);
        map.put(19L, templateId19);
        map.put(20L, templateId20);
        map.put(21L, templateId21);
        map.put(22L, templateId22);
        map.put(23L, templateId23);
        map.put(24L, templateId24);
        map.put(25L, templateId25);
        map.put(26L, templateId26);
        map.put(27L, templateId27);
        map.put(29L, templateId29);
        map.put(30L, templateId30);
        map.put(31L, templateId31);
        map.put(32L, templateId32);
        map.put(33L, templateId33);
    }
}
