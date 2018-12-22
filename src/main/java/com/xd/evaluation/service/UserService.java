package com.xd.evaluation.service;

import com.xd.evaluation.domain.Evaluation;
import com.xd.evaluation.domain.User;
import com.xd.evaluation.dto.EvaluationInfo;

import java.util.List;

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
     * 通过openid查询某个用户
     */
    User findByOpenid(String openId);

    /**
     * 通过userId查询某个用户，如果不存在，返回null
     */
    User findByUserId(Long userId);

    /**
     * 根据用户Id修改用户对象的非空信息
     */
    void updateUserByUserId(User user);

}
