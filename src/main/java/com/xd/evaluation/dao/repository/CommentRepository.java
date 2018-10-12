package com.xd.evaluation.dao.repository;

import com.xd.evaluation.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created By Cx On 2018/10/4 22:45
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByEvaluationId(Long evaluationId);

    @Modifying
    @Query(value = "UPDATE comment SET comment_content_id = :contentId WHERE comment_id = :commentId",
            nativeQuery = true)
    int updateCommentContentIdByCommentId(@Param("contentId") Long contentId,
                                           @Param("commentId") Long commentId);
}
