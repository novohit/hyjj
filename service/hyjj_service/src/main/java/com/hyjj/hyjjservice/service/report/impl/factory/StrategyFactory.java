package com.hyjj.hyjjservice.service.report.impl.factory;

import com.hyjj.hyjjservice.dao.ReportDataMapper;
import com.hyjj.hyjjservice.service.report.AuditService;
import com.hyjj.hyjjservice.service.report.impl.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StrategyFactory {

    @Autowired
    private ReportDataMapper reportDataMapper;

    private final Map<Integer, GetStatementStrategy> map = new HashMap<>();

    public StrategyFactory(ReportDataMapper reportDataMapper){
        map.put(0,new GetNull());
        map.put(1, new SelectID1(reportDataMapper));
        map.put(2, new SelectID2(reportDataMapper));
        map.put(3, new SelectID3(reportDataMapper));
        map.put(4, new SelectID4(reportDataMapper));
    }

    public GetStatementStrategy getStatementStrategy(Integer select){
        if (select == null || select >= map.size())
            return map.get(0);
        return map.get(select);
    }
}
