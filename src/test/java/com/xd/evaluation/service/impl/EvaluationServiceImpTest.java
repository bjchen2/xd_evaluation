package com.xd.evaluation.service.impl;

import com.xd.evaluation.service.EvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 15:09 2018/10/17
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EvaluationServiceImpTest {

    @Autowired
    private EvaluationService evaluationService;

    // 通过
    @Test
    @Rollback(false)
    public void likeEvaluation() throws Exception {
        evaluationService.likeEvaluation(20L, 22L, false);
    }
}