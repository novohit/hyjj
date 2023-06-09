package com.hyjj.hyjjservice.service.settings;

import com.hyjj.hyjjservice.controller.fill.viewObject.UploadVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.UserInfoVO;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.dataobject.User;
import org.springframework.stereotype.Service;
import com.hyjj.hyjjservice.controller.settings.viewObject.ReportNameAndIdVO;
import java.util.List;

public interface SysApplicationService {


    List<UserInfoVO> getUserInfoList(UserInfoVO userInfoVO, Integer pageNum, Integer pageSize);
    int getUserInfoListSum(UserInfoVO userInfoVO);

    List<UserInfoVO> getDeleteUserInfoList(Integer pageNum, Integer pageSize);
    int getDeleteUserInfoListSum();

    User getUserDetail(Long id);
    int updateUserInfo(User user);
    boolean insertUserInfo(User user);
    boolean deleteUser(Long id);
    List<ComInfo> getNotUseCom();
    boolean checkUserName(String name);
    boolean enableUser(Long id);
    boolean batchUpload(UploadVO uploadVOS);
    List<ReportNameAndIdVO> getReportInfo();

    List<ComInfo> searchNotUseCom(String comName);

}
