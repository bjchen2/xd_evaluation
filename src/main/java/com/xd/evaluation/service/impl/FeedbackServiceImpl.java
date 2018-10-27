package com.xd.evaluation.service.impl;

import com.xd.evaluation.dao.repository.FeedbackRepository;
import com.xd.evaluation.domain.Feedback;
import com.xd.evaluation.exception.EvaluationException;
import com.xd.evaluation.service.FeedbackService;
import com.xd.evaluation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created By Cx On 2018/10/18 18:05
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    UserService userService;

    @Override
    public Feedback create(Feedback feedback) {
        if (userService.findByUserId(feedback.getUserId()) == null){
            //用户不存在
            log.error("[添加反馈]添加失败，用户信息有误，userId={}",feedback.getUserId());
            throw new EvaluationException("添加失败，用户Id不存在，请尝试重新登录");
        }
        return feedbackRepository.save(feedback);
    }
}
