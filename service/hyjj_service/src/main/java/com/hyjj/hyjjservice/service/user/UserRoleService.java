package com.hyjj.hyjjservice.service.user;

import java.util.List;

public interface UserRoleService {
    List<Integer> selectRoleIdByUserId(Long UserId);
}
