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
public class Comment {
    //评论id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    //评论人id
    private Long userId;

    //评价id
    private Long evaluationId;

    //评论内容id
    private Long commentContentId;

    //赞同人数
    private Integer agreeCount;

    //反对人数
    private Integer disagreeCount;

    //创建时间
    private Date createTime;

    //最近更新时间
    private Date updateTime;
}