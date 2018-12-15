package com.xd.evaluation.pojo;

import lombok.Data;

/**
 * @Description: 接收请求点赞评价的对象
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 20:16 2018/12/15
 */
@Data
public class EvaLikePojo {
    private Long userId;    // 点赞的用户

    private Long objId;     // objId 被点赞的 评论/评价 id

    private Boolean isLike; // isLike 是否赞同 true：赞同

    private Integer likeType;   // likeType 评价还是评价 10：评价 11：评论
}
