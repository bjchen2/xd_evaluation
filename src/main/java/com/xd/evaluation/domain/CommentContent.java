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
public class CommentContent {
    //评论描述id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentContentId;

    //评论id
    private Long commentId;

    //评论内容
    private String commentContent;

    //创建时间
    private Date createTime;

    //最近更新时间
    private Date updateTime;
}