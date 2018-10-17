package com.xd.evaluation.dao.mapper;

import com.xd.evaluation.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User user);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKey(User user);
}