package com.xd.evaluation.dao.mapper;

import com.xd.evaluation.domain.Feedback;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Long feedbackId);

    int insert(Feedback feedback);

    Feedback selectByPrimaryKey(Long feedbackId);

    int updateByPrimaryKey(Feedback feedback);
}