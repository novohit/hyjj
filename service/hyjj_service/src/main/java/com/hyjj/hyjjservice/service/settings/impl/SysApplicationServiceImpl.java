package com.hyjj.hyjjservice.service.settings.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hyjj.hyjjservice.controller.fill.util.FileUtil;
import com.hyjj.hyjjservice.controller.fill.viewObject.UploadVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.ReportNameAndIdVO;
import com.hyjj.hyjjservice.controller.settings.viewObject.UserInfoVO;
import com.hyjj.hyjjservice.dao.*;
import com.hyjj.hyjjservice.dataobject.ComInfo;
import com.hyjj.hyjjservice.dataobject.ReportTemplate;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.dataobject.UserRole;
import com.hyjj.hyjjservice.service.fill.FillService;
import com.hyjj.hyjjservice.service.settings.SysApplicationService;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import com.hyjj.security.security.DefaultPasswordEncoder;
import com.hyjj.util.error.BusinessException;
import com.hyjj.util.responce.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysApplicationServiceImpl implements SysApplicationService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private ComInfoMapper comInfoMapper;
    @Autowired
    private FillService fillService;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private ReportTemplateMapper reportTemplateMapper;
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean enableUser(Long id) {
        return userMapper.enableUser(id) != 0;
    }

    @Override
    public boolean batchUpload(UploadVO uploadVO) {
        if (uploadVO.getFiles().length == 0) {
            return false;
        }
        int i = 0;
        List<Double>[] cellList = new ArrayList[uploadVO.getFiles().length];
        ReportTemplate reportTemplate = reportTemplateMapper.getRowAndColByTemplateId(Integer.parseInt(uploadVO.getReportId()));
        reportTemplate.setRow(reportTemplate.getDataRow());
        reportTemplate.setCol(reportTemplate.getDataCol());
        System.out.println(reportTemplate.getRow());
        for (MultipartFile file : uploadVO.getFiles()) {

            try {
                byte[] bytes = file.getBytes();
                InputStream is = new ByteArrayInputStream(bytes);
                cellList[i] = fileUtil.getCellList(reportTemplate, is);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int sum = 0;
        for (List<Double> doubles : cellList) {
            Integer integer = statisticService.addTargetKeyValue(Long.parseLong(uploadVO.getReportId().toString()), doubles);
            sum += integer;
        }

        return sum != 0;

    }

    @Override
    public List<ComInfo> getNotUseCom() {
        return comInfoMapper.getNotUsedCompanyList();
    }

    @Override
    public List<UserInfoVO> getUserInfoList(UserInfoVO userInfoVO, Integer pageNum, Integer pageSize) {
        Page Page = PageHelper.startPage(pageNum, pageSize == null ? 10 : pageSize);
        List<UserInfoVO> userInfoVOS = userMapper.selectUserInfoList(userInfoVO);
        return userInfoVOS;
    }

    @Override
    public int getUserInfoListSum(UserInfoVO userInfoVO) {
        return userMapper.selectUserInfoListSum(userInfoVO);
    }

    @Override
    public List<UserInfoVO> getDeleteUserInfoList(Integer pageNum, Integer pageSize) {
        Page Page = PageHelper.startPage(pageNum, pageSize == null ? 10 : pageSize);
        List<UserInfoVO> userInfoVOS = userMapper.selectDeleteUserInfoList();
        return userInfoVOS;
    }

    @Override
    public int getDeleteUserInfoListSum() {
        return userMapper.selectDeleteUserInfoListSum();
    }

    @Override
    public User getUserDetail(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        return userMapper.deleteUser(id) != 0;
    }

    @Override
    public int updateUserInfo(User user) {
        Date date = new Date();
        user.setGmtModified(date);
        if (user.getPassword() != null) {
            if (!user.getPassword().equals(userMapper.selectByPrimaryKey(user.getId()).getPassword())) {
                user.setPassword(new DefaultPasswordEncoder().encode(user.getPassword()));
            }
        }
        if (user.getStatue().equals("禁用")) {
            userMapper.disableUser(user.getId());
        }
        UserRole ur = new UserRole();
        ur.setId(user.getCominfoId());
        if (user.getUserType().equals("超级管理员")) {
            ur.setRoleId(4);
        } else if (user.getUserType().equals("管理员")) {
            ur.setRoleId(3);
        } else if (user.getUserType().equals("普通用户")) {
            ur.setRoleId(2);
        }
        int i = userRoleMapper.updateByPrimaryKeySelective(ur);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public boolean checkUserName(String name) {
        return userMapper.checkUserName(name) == 1;
    }

    @Override
    public List<ReportNameAndIdVO> getReportInfo() {
        return reportTemplateMapper.selectReportNameAndId();
    }

    @Override
    @Transactional
    public boolean insertUserInfo(User user) {


/*        if(user.getCominfoId()==null){
            return false;
        }*/
        ComInfo comInfo = comInfoMapper.selectByComName(user.getComName());
        if (comInfo == null) {
            throw new BusinessException(CommonReturnType.error("cominfo表中没有该企业"));
        }
        Date date = new Date();
        user.setCominfoId(comInfo.getId());
        user.setGmtCreate(date);
        user.setGmtModified(date);
        user.setPassword(new DefaultPasswordEncoder().encode(user.getPassword()));
        user.setStatue("正常");
        int i = userMapper.insertSelective(user);
        UserRole userRole = new UserRole();
        userRole.setId(user.getCominfoId());
        userRole.setUserId(user.getId());
        if (user.getUserType().equals("超级管理员")) {
            userRole.setRoleId(4);
        } else if (user.getUserType().equals("管理员")) {
            userRole.setRoleId(3);
        } else if (user.getUserType().equals("普通用户")) {
            userRole.setRoleId(2);
        }
        userRole.setIsUsed(false);
        userRole.setGmtCreate(date);
        userRole.setGmtModified(date);
        UserRole dbUserRole = userRoleMapper.selectByPrimaryKey(comInfo.getId());
        if (dbUserRole != null) {
            throw new BusinessException(CommonReturnType.error("user_role该企业用户已经存在"));
        }
        int j = userRoleMapper.insertSelective(userRole);
        redisTemplate.boundSetOps("NewUserComInfoId").add(user.getCominfoId());
        return i == j;

    }


    @Override
    public List<ComInfo> searchNotUseCom(String comName) {
        return comInfoMapper.searchCompanyList(comName);
    }


}
