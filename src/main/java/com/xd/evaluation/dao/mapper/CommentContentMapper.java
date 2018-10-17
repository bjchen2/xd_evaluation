package com.xd.evaluation.dao.mapper;

import com.xd.evaluation.domain.CommentContent;

public interface CommentContentMapper {
    int deleteByPrimaryKey(Long commentContentId);

    int insert(CommentContent commentContent);

    CommentContent selectByPrimaryKey(Long commentContentId);

    int updateByPrimaryKey(CommentContent commentContent);
}