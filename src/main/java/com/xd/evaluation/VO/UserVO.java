package com.xd.evaluation.VO;

import lombok.Data;

/**
 * Created By Cx On 2018/10/4 22:20
 */
@Data
public class UserVO {

    //用户id
    private Long userId;

    //用户微信openid
    private String userOpenid;

    //用户名
    private String userName;

    //用户头像url
    private String userAvatar;

}
