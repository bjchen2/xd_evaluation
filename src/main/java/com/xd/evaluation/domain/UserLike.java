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
@DynamicUpdate
@DynamicInsert
@Entity
@NoArgsConstructor
public class UserLike {

    //赞同记录id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    //用户id
    private Long userId;

    //赞同对象id，需要由like_type判断在哪张表，再通过obj_id查找
    private Long objId;

    //是否赞同
    private Boolean isLike;

    //赞同对象类型，10:评价  11：评论
    private Integer likeType;

    //创建时间
    private Date createTime;

    //最近更新时间
    private Date updateTime;
}