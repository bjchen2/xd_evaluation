package com.xd.evaluation.service.impl;

import com.xd.evaluation.config.WeChatAccountConfig;
import com.xd.evaluation.dao.repository.CommentRepository;
import com.xd.evaluation.dao.repository.FeedbackRepository;
import com.xd.evaluation.dao.repository.UserLikeRepository;
import com.xd.evaluation.domain.Comment;
import com.xd.evaluation.domain.Feedback;
import com.xd.evaluation.domain.UserLike;
import com.xd.evaluation.dto.CommentInfo;
import com.xd.evaluation.enums.LikeTypeEnum;
import com.xd.evaluation.service.CommentService;
import com.xd.evaluation.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created By Cx On 2018/10/5 0:24
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceImpTest {

    @Autowired
    UserService userService;
    @Autowired
    WeChatAccountConfig config;
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;

    @Test
    public void getOne() {
        System.out.println("===========================================================");
        System.out.println(config.getAppid());
        System.out.println(config.getMsgDataFormat());
    }

    @Test
    @Rollback(false)
    public void save(){
        Feedback feedback = feedbackRepository.getOne(21L);
        System.out.println();
        System.out.println(feedback.toString());
    }

    @Test
    public void getCommentsTest() throws Exception {
    //    commentRepository.findAllByEvaluationId(1L);
       List<CommentInfo> comments = commentService.returnAllComments(1L, 20L);
    }

}