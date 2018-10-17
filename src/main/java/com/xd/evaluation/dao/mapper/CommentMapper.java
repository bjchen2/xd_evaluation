package com.xd.evaluation.dao.mapper;

import com.xd.evaluation.domain.Comment;

public interface CommentMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(Comment comment);

    Comment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKey(Comment comment);
}