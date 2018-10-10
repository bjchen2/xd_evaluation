package com.xd.evaluation.service.impl;

import com.xd.evaluation.config.WeChatAccountConfig;
import com.xd.evaluation.dao.mapper.UserMapper;
import com.xd.evaluation.dao.repository.UserRepository;
import com.xd.evaluation.domain.User;
import com.xd.evaluation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created By Cx On 2018/10/4 22:17
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserServiceImp implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        userMapper.insert(user);
        return null;
    }

    @Override
    public User findByOpenid(String openId) {
        return null;
    }
}


