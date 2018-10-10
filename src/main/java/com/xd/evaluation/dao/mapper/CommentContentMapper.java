package com.xd.evaluation.dao.mapper;

import com.xd.evaluation.domain.CommentContent;

public interface CommentContentMapper {
    int deleteByPrimaryKey(Long commentTextId);

    int insert(CommentContent record);

    CommentContent selectByPrimaryKey(Long commentTextId);

    int updateByPrimaryKey(CommentContent record);
}