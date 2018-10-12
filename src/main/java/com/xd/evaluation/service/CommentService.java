package com.xd.evaluation.service;

import com.xd.evaluation.dto.CommentInfo;

import java.util.List;

/**
 * @Description:
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 23:05 2018/10/11
 */
public interface CommentService {


    /**
     * 返回某评价的所有评论
     * @param evaluationId
     * @param userId 进行查询用户的id，而不是评论人的id
     * @return
     * @throws Exception
     */
    List<CommentInfo> returnAllComments(Long evaluationId, Long userId) throws Exception;

    /**
     * 添加一条评价的评论
     * @param userId 评论人id
     * @param evaluationId 评论的评价id
     * @param content 评论内容
     * @throws Exception
     */
    void addComment(Long userId, Long evaluationId, String content) throws Exception;
}
