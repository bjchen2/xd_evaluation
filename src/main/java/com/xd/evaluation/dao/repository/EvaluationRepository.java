package com.xd.evaluation.dao.repository;

import com.xd.evaluation.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created By Cx On 2018/10/4 22:45
 */
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Modifying
    @Query(value = "UPDATE evaluation SET agree_count = agree_count + :change " +
            "WHERE evaluation_id = :evaluationId",
            nativeQuery = true)
    void updateAgreeCountByEvaluationId(@Param("evaluationId") Long evaId,
                                        @Param("change") Integer change);

    @Modifying
    @Query(value = "UPDATE evaluation SET disagree_count = disagree_count + :change " +
            "WHERE evaluation_id = :evaluationId",
            nativeQuery = true)
    void updateDisagreeCountByEvaluationId(@Param("evaluationId") Long evaId,
                                        @Param("change") Integer change);
}
