package com.xd.evaluation.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
public class Evaluation {
    //评价id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;

    //评价人id
    private Long userId;

    //教师姓名
    private String teacherName;

    //课程名
    private String courseName;

    //课程类别，10：校选修 11：必修 12：实验
    private Integer courseType;

    //是否推荐，0不推荐，1推荐
    private Boolean isRecommended;

    //赞同人数
    private Integer agreeCount;

    //反对人数
    private Integer disagreeCount;

    //创建时间
    private Date createTime;

    //最近更新时间
    private Date updateTime;
}