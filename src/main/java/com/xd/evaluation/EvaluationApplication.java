package com.xd.evaluation;

import com.xd.evaluation.VO.UserVO;
import com.xd.evaluation.config.WeChatAccountConfig;
import com.xd.evaluation.service.impl.UserServiceImp;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.xd.evaluation.dao.mapper")
public class EvaluationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvaluationApplication.class, args);
    }
}
