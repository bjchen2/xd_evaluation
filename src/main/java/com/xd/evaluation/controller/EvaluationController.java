package com.xd.evaluation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 进行评价有关的操作（注：评论的点赞操作也在这里）
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 8:38 2018/10/16
 */
@RestController
@RequestMapping(value = "/evaluation")
public class EvaluationController {
    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluationController.class);


}
