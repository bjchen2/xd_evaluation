package com.xd.evaluation.dao.mapper;

import com.xd.evaluation.domain.Evaluation;

public interface EvaluationMapper {
    int deleteByPrimaryKey(Long evaluationId);

    int insert(Evaluation evaluation);

    Evaluation selectByPrimaryKey(Long evaluationId);

    int updateByPrimaryKey(Evaluation evaluation);
}