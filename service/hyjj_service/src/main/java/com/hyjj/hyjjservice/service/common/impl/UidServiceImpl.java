package com.hyjj.hyjjservice.service.common.impl;

import com.hyjj.hyjjservice.service.common.UidService;
import com.xfvape.uid.UidGenerator;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UidServiceImpl implements UidService {

    @Resource(name = "cachedUidGenerator")
    private UidGenerator uidGenerator;

    @Override
    public long getUid() {
        return uidGenerator.getUID();
    }
}