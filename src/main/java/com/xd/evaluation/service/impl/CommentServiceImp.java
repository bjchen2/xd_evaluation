package com.xd.evaluation.service.impl;

import com.xd.evaluation.dao.repository.CommentContentRepository;
import com.xd.evaluation.dao.repository.CommentRepository;
import com.xd.evaluation.dao.repository.UserLikeRepository;
import com.xd.evaluation.domain.Comment;
import com.xd.evaluation.domain.CommentContent;
import com.xd.evaluation.domain.UserLike;
import com.xd.evaluation.dto.CommentInfo;
import com.xd.evaluation.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 23:08 2018/10/11
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentContentRepository commentContentRepository;

    @Autowired
    private UserLikeRepository userLikeRepository;

    @Override
    public List<CommentInfo> returnAllComments(Long evaluationId, Long userId) throws Exception {
        List<Comment> commentList = commentRepository.findAllByEvaluationId(evaluationId);

        // 假如查询不到结果
        if(commentList.isEmpty())
            throw new Exception("查询不到id为" + evaluationId + "的评价下的评论");
        List<CommentInfo> comments = new ArrayList<>(); // 返回的主体

        for(Comment comment: commentList) { // 查询出commentContent和isLike两个数据
            CommentInfo commentInfo = new CommentInfo();
            CommentContent content = commentContentRepository.findByCommentId(comment.getCommentId());
            if(content == null)
                throw new Exception("查询不到id为" + comment.getCommentId() + "的评论内容");
            commentInfo.setCommentContent(content.getCommentContent());

            UserLike userLike = userLikeRepository
                    .findByLikeTypeAndObjIdAndUserId(10, comment.getCommentId(), userId);
            if(userLike == null) {  // 如果查询不到数据就说明用户没有点赞
                commentInfo.setIsLiked(null);
            } else {
                commentInfo.setIsLiked(userLike.getIsLike());
            }
            BeanUtils.copyProperties(comment, commentInfo);
            comments.add(commentInfo);
        }
        return comments;
    }
}
