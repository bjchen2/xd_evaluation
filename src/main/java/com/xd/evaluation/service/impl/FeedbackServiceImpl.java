package com.xd.evaluation.service.impl;

import com.xd.evaluation.dao.repository.FeedbackRepository;
import com.xd.evaluation.domain.Feedback;
import com.xd.evaluation.exception.EvaluationException;
import com.xd.evaluation.form.FeedbackForm;
import com.xd.evaluation.service.FeedbackService;
import com.xd.evaluation.service.UserService;
import com.xd.evaluation.utils.OSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

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
    @Autowired
    OSSUtil ossUtil;

    @Override
    public Feedback create(FeedbackForm feedbackForm, Long userId) {
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(feedbackForm,feedback);
        if (feedbackForm.getFile() != null){
            //有图片，进行图片上传
            feedback.setUrl(ossUtil.upload(feedbackForm.getFile()));
        }
        feedback.setUserId(userId);
        if (userService.findByUserId(userId) == null){
            //用户不存在
            log.error("[添加反馈]添加失败，用户信息有误，userId={}",userId);
            throw new EvaluationException("添加失败，用户Id不存在，请尝试重新登录");
        }
        return feedbackRepository.save(feedback);
    }
}
