package com.xd.evaluation.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class User {
    //用户id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    //用户微信openid
    private String userOpenid;

    //用户名
    private String userName;

    //用户头像url
    private String userAvatar;

    //创建时间
    private Date createTime;

    //最近更新时间
    private Date updateTime;

    public User() {
    }

    public User(String userName, String userOpenId, String userAvatar) {
        this.userName = userName;
        this.userOpenid = userOpenId;
        this.userAvatar = userAvatar;
    }
}