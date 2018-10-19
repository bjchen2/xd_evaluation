package com.xd.evaluation.service.impl;

import com.xd.evaluation.dto.CommentInfo;
import com.xd.evaluation.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 22:44 2018/10/17
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CommentServiceImpTest {

    @Autowired
    private CommentService commentService;

    // 通过
    @Test
    public void returnAllComments() throws Exception {
        List<CommentInfo> infos = commentService.returnAllComments(1L, 20L);
        return ;
    }

    // 通过
    @Test
    @Rollback(false)
    public void addComment() throws Exception {
        commentService.addComment(2L, 11L, "这是测试2-11");
    }
}