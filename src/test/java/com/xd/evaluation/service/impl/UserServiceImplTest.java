package com.xd.evaluation.service.impl;

import com.xd.evaluation.config.WeChatAccountConfig;
import com.xd.evaluation.controller.CommentController;
import com.xd.evaluation.dao.repository.CommentRepository;
import com.xd.evaluation.dao.repository.FeedbackRepository;
import com.xd.evaluation.domain.User;
import com.xd.evaluation.dto.CommentInfo;
import com.xd.evaluation.service.CommentService;
import com.xd.evaluation.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created By Cx On 2018/10/5 0:24
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void updateUserByUserId() {
        User user = new User();
        user.setUserId(22L);
        user.setUserAvatar("hello");
        userService.updateUserByUserId(user);
    }

    @Test
    public void findByUserId() {
        System.out.println(userService.findByUserId(112L));
    }


}