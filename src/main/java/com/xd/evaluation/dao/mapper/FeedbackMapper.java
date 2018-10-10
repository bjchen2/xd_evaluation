package com.xd.evaluation.dao.mapper;

import com.xd.evaluation.domain.Feedback;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Long feedbackId);

    int insert(Feedback record);

    Feedback selectByPrimaryKey(Long feedbackId);

    int updateByPrimaryKey(Feedback record);
}