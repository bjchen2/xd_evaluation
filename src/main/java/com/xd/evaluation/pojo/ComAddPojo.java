package com.xd.evaluation.pojo;


import lombok.Data;

/**
 * @Description: 接受添加评论的业务对象
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 20:27 2018/12/15
 */
@Data
public class ComAddPojo {
    private Long userId;        // 评论人id

    private Long evaluationId;  // 评论的评价id

    private String commentContent;  // 评论内容
}
