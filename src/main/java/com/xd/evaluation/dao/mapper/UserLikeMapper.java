package com.xd.evaluation.dao.mapper;

import com.xd.evaluation.domain.UserLike;

public interface UserLikeMapper {
    int deleteByPrimaryKey(Long likeId);

    int insert(UserLike record);

    UserLike selectByPrimaryKey(Long likeId);

    int updateByPrimaryKey(UserLike record);
}