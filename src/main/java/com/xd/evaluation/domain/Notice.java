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
public class Notice {
    //公告id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    //公告类型，10：通知，11：活动
    private Integer type;

    //公告标题
    private String title;

    //作者名
    private String authorName;

    //图片url
    private String image;

    //公告内容
    private String content;

    //创建时间
    private Date createTime;

    //最近更新时间
    private Date updateTime;
}