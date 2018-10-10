package com.xd.evaluation.service;

import com.xd.evaluation.domain.User;

/**
 * 用户有关service   微信端
 * Created By Cx On 2018/10/4 22:16
 */
public interface UserService {

    /**
     * 新增某个用户
     */
    User save(User user);

    /**
     * 查询某个用户
     */
    User findByOpenid(String openId);

}
