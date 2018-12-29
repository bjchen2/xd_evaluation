package com.xd.evaluation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Feedback {
    //反馈id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    //用户id
    private Long userId;

    //反馈标题
    private String title;

    //反馈内容
    private String content;

    //图片url
    private String url;

    //创建时间
    @JsonIgnore
    private Date createTime;

    //最近更新时间
    @JsonIgnore
    private Date updateTime;
}