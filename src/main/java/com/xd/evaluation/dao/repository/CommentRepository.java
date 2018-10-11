package com.xd.evaluation.dao.repository;

import com.xd.evaluation.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Cx On 2018/10/4 22:45
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByEvaluationId(Long evaluationId);
}
