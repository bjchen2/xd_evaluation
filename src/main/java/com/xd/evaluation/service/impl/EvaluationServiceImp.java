package com.xd.evaluation.service.impl;

import com.xd.evaluation.dao.repository.EvaluationRepository;
import com.xd.evaluation.dao.repository.UserLikeRepository;
import com.xd.evaluation.domain.UserLike;
import com.xd.evaluation.service.EvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 8:50 2018/10/16
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EvaluationServiceImp implements EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private UserLikeRepository userLikeRepository;

    @Override
    public void likeEvaluation(Long userId, Long evaId, Boolean isLike) throws Exception {
        // 先查询数据库是否存在记录
        UserLike likeTemp =
                userLikeRepository.findByLikeTypeAndObjIdAndUserId(10, evaId, userId);
        if(null == likeTemp) {  // 没有这样的记录，所以需要添加
            likeTemp = new UserLike();
            likeTemp.setIsLike(true);
            likeTemp.setLikeType(10);
            likeTemp.setObjId(evaId);
            likeTemp.setUserId(userId);
            // 创建这样一条记录
            userLikeRepository.save(likeTemp);

            // 同时还要更新evaluation表里的agree_count/disagree_count
            if(isLike) {    // 不能写在外面，因为从反对改为点赞和null改为点赞操作不一样
                // 只需要增加一个点赞数即可
                evaluationRepository.updateAgreeCountByEvaluationId(evaId, 1);
            } else {
                evaluationRepository.updateDisagreeCountByEvaluationId(evaId, 1);
            }
        } else {    // 如果存在记录，则只需要修改即可
            userLikeRepository.
                    updateIsLikeByLikeTypeAndObjIdAndUserId(10, evaId, userId, isLike);
            if(isLike) {    // 说明用户之前点的是反对，所以要把反对取消，再给点赞+1
                evaluationRepository.updateDisagreeCountByEvaluationId(evaId, -1);
                evaluationRepository.updateAgreeCountByEvaluationId(evaId, 1);
            } else {
                evaluationRepository.updateAgreeCountByEvaluationId(evaId, -1);
                evaluationRepository.updateDisagreeCountByEvaluationId(evaId, 1);
            }
        }
    }

    @Override
    public void cancelLikeEvaluation(Long userId, Long evaId, Boolean isLike) throws Exception {
    }
}
