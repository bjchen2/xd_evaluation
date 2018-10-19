package com.xd.evaluation.dao.mapper;

import com.xd.evaluation.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User user);

    User selectByPrimaryKey(Long userId);

    /**
     * 仅可修改用户名和用户头像URL
     */
    int updateByPrimaryKey(User user);
}