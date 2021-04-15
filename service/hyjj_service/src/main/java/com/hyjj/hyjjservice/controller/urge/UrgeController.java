package com.hyjj.hyjjservice.controller.urge;

import com.hyjj.hyjjservice.annotation.GetUser;
import com.hyjj.hyjjservice.controller.urge.viewobject.UrgeVO;
import com.hyjj.hyjjservice.dataobject.UrgeData;
import com.hyjj.hyjjservice.dataobject.User;
import com.hyjj.hyjjservice.service.urge.UrgeService;
import com.hyjj.hyjjservice.service.user.UserRoleService;
import com.hyjj.util.error.EmBusinessError;
import com.hyjj.util.responce.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("urge")
@Api("催办相关的接口")
public class UrgeController {

    @Resource(name = "userThreadLocal")
    private ThreadLocal<User> threadLocal;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UrgeService urgeService;

    @GetMapping("getUrge")
    @ApiOperation("获取催办名单")
    @GetUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年份", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "company", value = "要催办的公司的名称", required = true, dataTypeClass = String.class)
    })
    public CommonReturnType getUrge(Integer year, String company) {
        User user = threadLocal.get();
        return checkUser(user) ? CommonReturnType.error(EmBusinessError.USER_DONOT_HVER_PERMISSION)
                : CommonReturnType.ok().add("result", urgeService.getUrge(year, company));
    }

    @PostMapping("urge")
    @ApiOperation("催办业务")
    @GetUser
    public CommonReturnType urge(@RequestBody List<String> company) {
        User user = threadLocal.get();
        System.out.println(user);
        return checkUser(user) ? CommonReturnType.error(EmBusinessError.USER_DONOT_HVER_PERMISSION)
                : CommonReturnType.ok().add("status", urgeService.urge(user, company));
    }

    @GetMapping("urgeStatus")
    @ApiOperation("查看有没有被催办")
    @GetUser
    public CommonReturnType urgeStatus(){
        User user = threadLocal.get();
        List<UrgeData> urgeData = urgeService.urgeStatus(user.getId());
        List<UrgeVO> urgeVOList = new ArrayList<>();
        for (UrgeData urgeDatum : urgeData)
            urgeVOList.add(new UrgeVO(urgeDatum.getSendUser(), urgeDatum.getSendGroup()));

        return CommonReturnType.ok().add("urgeStatus", urgeVOList);
    }

    public Boolean checkUser(User user) {
        //如果用户不是管理员则返回错误代码
        List<Integer> userRoleIds = userRoleService.selectRoleIdByUserId(user.getId());
        for (Integer userRoleId : userRoleIds)
            if (userRoleId < 3)
                return true;
        return false;
    }
}
