package com.z.eshop.service;

import com.z.eshop.model.User;

/**
 * UserService业务接口
 */
public interface UserService extends  BaseService<User>{
    //判断邮箱是否已经被注册
    public boolean isRegisted(String email);
}
