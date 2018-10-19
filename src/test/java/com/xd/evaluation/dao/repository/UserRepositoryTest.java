package com.xd.evaluation.dao.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created By Cx On 2018/10/19 18:05
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void gerOne(){
        System.out.println(userRepository.getOne(122L));
    }

}