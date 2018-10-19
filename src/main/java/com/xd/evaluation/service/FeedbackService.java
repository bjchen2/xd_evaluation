package com.xd.evaluation.service;

import com.xd.evaluation.domain.Feedback;

/**
 * Created By Cx On 2018/10/18 18:04
 */
public interface FeedbackService {

    //创建反馈
    Feedback create(Feedback feedback);
}
