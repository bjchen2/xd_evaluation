package com.xd.evaluation.service;

import com.xd.evaluation.dto.EvaluationInfo;

import java.util.List;

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
     * @param isLike true：要删除的是点赞信息
     * @throws Exception
     */
    void cancelLikeEvaluation(Long userId, Long evaId, Boolean isLike) throws Exception;

    /**
     * 查询所有评价信息，并按时间顺序排序
     * @param userId 进行查询的用户id
     * @param key 搜索关键字
     * @param type 类别：0（默认）：全部，10：校选修 11：必修 12：实验
     * @param sortType 排序方式：0（默认）：按最新排序，1：按点赞量排序
     * @return
     * @throws Exception
     */
    List<EvaluationInfo> returnAllEvaluationOrder(Long userId, String key,
                                                  Integer type, Integer sortType)
            throws Exception;

    /**
     * 添加评价
     * @param isRecommended true: 推荐
     * @throws Exception
     */
    void addEvaluation(Long userId, String content, String teacherName,
                       String courseName, Integer courseType, Boolean isRecommended)
            throws Exception;

    /**
     * 查询某用户写的全部评价
     * @param userId
     * @throws Exception
     */
    List<EvaluationInfo> returnUserAllEvaluation(Long userId) throws Exception;
}
