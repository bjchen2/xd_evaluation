package com.xd.evaluation.dao.repository;

import com.xd.evaluation.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created By Cx On 2018/10/22 18:49
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class NoticeRepositoryTest {

    @Autowired
    NoticeRepository noticeRepository;

    @Test
    public void findAllByType() {
    }

    @Test
    public void findDetailByNoticeId() {
        System.out.println("====================================================");
        System.out.println(noticeRepository.findDetailByNoticeId(21L).get(0).getCreateTime());
    }
}