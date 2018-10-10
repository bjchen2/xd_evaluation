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
public class EvaluationContent {
    //评价描述id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationContentId;

    //评价id
    private Long evaluationId;

    //评价内容
    private String evaluationContent;

    //创建时间
    private Date createTime;

    //最近更新时间
    private Date updateTime;
}