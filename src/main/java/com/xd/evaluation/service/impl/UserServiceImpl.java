package com.xd.evaluation.service.impl;

import com.xd.evaluation.dao.mapper.UserMapper;
import com.xd.evaluation.dao.repository.UserRepository;
import com.xd.evaluation.domain.User;
import com.xd.evaluation.dto.EvaluationInfo;
import com.xd.evaluation.exception.EvaluationException;
import com.xd.evaluation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created By Cx On 2018/10/4 22:17
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByOpenid(String openId) {
        return null;
    }

    @Override
    public User findByUserId(Long userId) {
        return  userRepository.findById(userId).orElse(null);
    }

    @Override
    public void updateUserByUserId(User user) {
        if (user.getUserAvatar() == null && user.getUserName() == null){
            log.error("[更改用户信息]失败，用户信息为空，userAvatar={}, userName={}"
                    ,user.getUserAvatar(),user.getUserName());
            throw new EvaluationException("更改用户信息失败，用户信息为空");
        }
        if (findByUserId(user.getUserId()) == null){
            log.error("[更改用户信息]失败，用户id不存在，userId={}",user.getUserId());
            throw new EvaluationException("更改用户信息失败，用户id不存在");
        }
        if (userMapper.updateByPrimaryKey(user) < 0){
            log.error("[更改用户信息]失败，服务器异常");
            throw new EvaluationException("更改用户信息失败，服务器异常");
        }
    }

}


