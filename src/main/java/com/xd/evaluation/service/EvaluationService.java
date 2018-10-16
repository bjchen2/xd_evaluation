package com.xd.evaluation.service;

/**
 * @Description: 进行评价信息操作的接口
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 8:17 2018/10/16
 */
public interface EvaluationService {

    /**
     * 点赞/反对 一条评价
     * @param userId 点赞人id
     * @param evaId 点赞的评价id
     * @param isLike 是否赞同 true：是
     * @throws Exception
     */
    void likeEvaluation(Long userId, Long evaId, Boolean isLike) throws Exception;

    /**
     * 删除一条 点赞/反对 评价信息
     * @param userId
     * @param evaId
     * @throws Exception
     */
    void cancelLikeEvaluation(Long userId, Long evaId) throws Exception;
}
