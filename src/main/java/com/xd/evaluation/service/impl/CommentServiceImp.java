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

    //TODO: 用原生sql改写
    @Override
    public List<CommentInfo> returnAllComments(Long evaluationId, Long userId) throws Exception {
        List<Comment> commentList = commentRepository.findAllByEvaluationId(evaluationId);

        // 假如查询不到结果
        if(commentList.isEmpty()) return null;  // 说明当前评价下没有任何评论

        List<CommentInfo> comments = new ArrayList<>(); // 返回的主体

        for(Comment comment: commentList) { // 查询出commentContent和isLike两个数据
            CommentInfo commentInfo = new CommentInfo();
            CommentContent content = commentContentRepository.findByCommentId(comment.getCommentId());
            if(content == null)
                throw new Exception("查询不到id为" + comment.getCommentId() + "的评论内容");
            commentInfo.setCommentContent(content.getCommentContent());

            UserLike userLike = userLikeRepository
                    .findByLikeTypeAndObjIdAndUserId(11, comment.getCommentId(), userId);
            if(userLike == null) {  // 如果查询不到数据就说明用户没有点赞
                commentInfo.setIsLike(null);
            } else {
                commentInfo.setIsLike(userLike.getIsLike());
            }
            BeanUtils.copyProperties(comment, commentInfo);
            commentInfo.setCreateTime(comment.getCreateTime().getTime());
            comments.add(commentInfo);
        }
        return comments;
    }

    @Override
    public void addComment(Long userId, Long evaluationId, String content) throws Exception {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setEvaluationId(evaluationId);
        comment.setAgreeCount(0);
        comment.setDisagreeCount(0);

        Comment resComment = commentRepository.save(comment);

        CommentContent commentContent = new CommentContent();  // 创建content实体
        commentContent.setCommentId(resComment.getCommentId());    // save操作后会把插入的id返回到res
        commentContent.setCommentContent(content);

        commentContentRepository.save(commentContent);
    }

    @Override
    public void likeComment(Long userId, Long commentId, Boolean isLike) throws Exception {
        // 先查询数据库是否存在记录
        UserLike likeTemp =
                userLikeRepository.findByLikeTypeAndObjIdAndUserId(11, commentId, userId);
        if(null == likeTemp) {  // 没有这样的记录，所以需要添加
            likeTemp = new UserLike();

            if(isLike) likeTemp.setIsLike(true);    // 如果用户点的是赞同，就设为1
            else likeTemp.setIsLike(false);

            likeTemp.setLikeType(11);
            likeTemp.setObjId(commentId);
            likeTemp.setUserId(userId);
            // 创建这样一条记录
            userLikeRepository.save(likeTemp);

            // 同时还要更新evaluation表里的agree_count/disagree_count
            if(isLike) {    // 不能写在外面，因为从反对改为点赞和null改为点赞操作不一样
                // 只需要增加一个点赞数即可
                commentRepository.updateAgreeCountByCommentId(commentId, 1);
            } else {
                commentRepository.updateDisagreeCountByCommentId(commentId, 1);
            }
        } else {    // 如果存在记录，则只需要修改即可
            userLikeRepository.
                    updateIsLikeByLikeTypeAndObjIdAndUserId(11, commentId, userId, isLike);
            if(isLike) {    // 说明用户之前点的是反对，所以要把反对取消，再给点赞+1
                commentRepository.updateDisagreeCountByCommentId(commentId, -1);
                commentRepository.updateAgreeCountByCommentId(commentId, 1);
            } else {
                commentRepository.updateAgreeCountByCommentId(commentId, -1);
                commentRepository.updateDisagreeCountByCommentId(commentId, 1);
            }
        }
    }

    @Override
    public void cancelLikeComment(Long userId, Long commentId, Boolean isLike) throws Exception {
        userLikeRepository.deleteByLikeTypeAndObjIdAndUserId(11, commentId, userId);

        // 还要修改evaluation表里的 赞数/反对数
        if(isLike) {    // 假如取消的是点赞，那么就要对agree_count减1
            commentRepository.updateAgreeCountByCommentId(commentId, -1);
        } else {
            commentRepository.updateDisagreeCountByCommentId(commentId, -1);
        }
    }
}
