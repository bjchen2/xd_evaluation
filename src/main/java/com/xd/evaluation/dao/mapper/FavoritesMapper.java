package com.xd.evaluation.dao.mapper;

import com.xd.evaluation.domain.Favorite;

public interface FavoritesMapper {
    int deleteByPrimaryKey(Long favoritesId);

    int insertSelective(Favorite favorite);

    Favorite selectByPrimaryKey(Long favoritesId);

    int updateByPrimaryKey(Favorite favorite);
}