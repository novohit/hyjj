package com.hyjj.hyjjservice.service.urge;

import com.hyjj.hyjjservice.controller.urge.viewobject.UrgeReportVO;
import com.hyjj.hyjjservice.dataobject.UrgeData;
import com.hyjj.hyjjservice.dataobject.User;

import java.util.List;

public interface UrgeService {
    String urge(User user, List<String> company);

    List<UrgeReportVO> getUrge(Integer year, String company);

    List<UrgeData> urgeStatus(Long userId);
}
