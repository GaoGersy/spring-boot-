package com.piesat.project.service.system;

import com.piesat.project.entity.User;

public interface UserService {
    User queryUserInfoByOpeanId(String openId);
}
