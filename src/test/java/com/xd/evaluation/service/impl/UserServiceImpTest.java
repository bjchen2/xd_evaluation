package com.xd.evaluation.service.impl;

import com.xd.evaluation.config.WeChatAccountConfig;
import com.xd.evaluation.controller.CommentController;
import com.xd.evaluation.dao.repository.CommentRepository;
import com.xd.evaluation.dao.repository.FeedbackRepository;
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

    private MockMvc mvc;

    @Test
    public void getOne() {
        System.out.println("===========================================================");
        System.out.println(config.getAppid());
        System.out.println(config.getMsgDataFormat());
    }

    @Test
    @Rollback(false)
    public void save(){
        System.out.println("===================================================");
        System.out.println();
    }

    @Test
    public void getCommentsTest() throws Exception {
    //    commentRepository.findAllByEvaluationId(1L);
       List<CommentInfo> comments = commentService.returnAllComments(1L, 20L);
    }

    @Test
    @Rollback(false)
    public void addCommentTest() throws Exception {
       commentService.addComment(30L,25L, "测试内容测试内容3");
    }

    @Test
    public void requestQueryCommentTest() throws Exception {
        this.mvc = MockMvcBuilders.standaloneSetup(new CommentController()).build();
        String url = "http://localhost:8080/comment/1/20";
        RequestBuilder req = get("/comment/1/20");
        mvc.perform(req).andExpect(status().isOk());
    }

//    @Test
//    public void requestAddCommentTest() throws Exception {
//        String url = "http://localhost:8080/comment";
//        Map<String, Object> map = new HashMap<>();
//        map.put("userId", 30L);
//        map.put("evaluationId", 20L);
//        map.put("commentContent", "测试444444");
//        ResultVO res = template.postForObject(url, map, ResultVO.class);
//        int a = 1;
//    }

}