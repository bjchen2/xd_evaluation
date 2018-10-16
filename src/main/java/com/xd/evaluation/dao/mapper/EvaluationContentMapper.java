package com.xd.evaluation.dao.mapper;

import com.xd.evaluation.domain.EvaluationContent;

public interface EvaluationContentMapper {
    int deleteByPrimaryKey(Long evaluationContentId);

    int insert(EvaluationContent record);

    EvaluationContent selectByPrimaryKey(Long evaluationContentId);

    int updateByPrimaryKey(EvaluationContent record);


}