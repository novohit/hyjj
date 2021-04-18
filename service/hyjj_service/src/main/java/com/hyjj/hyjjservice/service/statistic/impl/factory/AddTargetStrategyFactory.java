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

    private final Map<Long, AddTargetTemplate> map = new HashMap<>();

    public AddTargetTemplate getAddTargetValueStatus(Long id) {
        return map.get(id);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        map.put(1l, templateId1);
        map.put(6l, templateId6);
        map.put(9l, templateId9);
        map.put(10l, templateId10);
    }
}
