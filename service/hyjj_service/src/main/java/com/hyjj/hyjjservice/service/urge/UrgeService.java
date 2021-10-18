package com.hyjj.hyjjservice.service.urge;

import com.hyjj.hyjjservice.controller.urge.viewobject.UrgeReportVO;
import com.hyjj.hyjjservice.dataobject.UrgeData;
import com.hyjj.hyjjservice.dataobject.User;

import java.util.List;

public interface UrgeService {

    /**
     * 催办
     * @param user  哪个用户发起的催办
     * @param company   催办的公司的集合
     * @return
     */
    String urge(User user, List<String> company);

    /**
     * 获取催办名单
     * @param year  年
     * @param company   公司
     * @param pageNum   第几页
     * @param pageSize  每页大小
     * @return
     */
    List<UrgeReportVO> getUrge(Integer year, String company,int pageNum, int pageSize);

    /**
     * 查看自己有没有被催办
     * @param userId    用户id
     * @return
     */
    List<UrgeData> urgeStatus(Long userId);

    /**
     * 返回催办名单总条数
     * @param year
     * @param company
     * @return
     */
    Integer getUrgeSum(Integer year, String company);

    String urgeAll(User user,Integer year, String company);
}
