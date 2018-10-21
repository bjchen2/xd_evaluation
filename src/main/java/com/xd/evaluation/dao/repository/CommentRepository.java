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
    @Query(value = "UPDATE comment SET agree_count = agree_count + :change " +
            "WHERE comment_id = :commentId",
            nativeQuery = true)
    void updateAgreeCountByCommentId(@Param("commentId") Long commentId,
                                        @Param("change") Integer change);

    @Modifying
    @Query(value = "UPDATE comment SET disagree_count = disagree_count + :change " +
            "WHERE comment_id = :commentId",
            nativeQuery = true)
    void updateDisagreeCountByCommentId(@Param("commentId") Long commentId,
                                           @Param("change") Integer change);

    Integer countByEvaluationId(Long evaluationId);
}
