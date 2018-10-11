package com.xd.evaluation.dto;

import com.xd.evaluation.domain.Comment;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 返回查询到指定评价所有评论对象
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 22:59 2018/10/11
 */
@Data
public class CommentInfo {

    // 评论id
    private Long commentId;

    //评论人id
    private Long userId;

    //评价id
    private Long evaluationId;

    //评论内容
    private String commentContent;

    //赞同人数
    private Integer agreeCount;

    //反对人数
    private Integer disagreeCount;

    // 是否点赞了
    private Boolean isLiked;

    //创建时间
    private Date createTime;
}
