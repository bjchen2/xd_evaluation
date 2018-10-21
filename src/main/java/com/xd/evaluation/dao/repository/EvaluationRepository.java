package com.xd.evaluation.dao.repository;

import com.xd.evaluation.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Cx On 2018/10/4 22:45
 */
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    List<Evaluation> findAllByUserId(Long userId);

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

    /**
     * 查询所有评价信息，并按创建时间排序
     * @param notUse 为true时表示不使用courseType
     */
    @Query(value = "SELECT a.evaluation_id, user_id, b.evaluation_content, teacher_name, " +
            "course_name, course_type, is_recommended, agree_count, disagree_count, " +
            "a.create_time FROM evaluation a, evaluation_content b " +
            "WHERE a.evaluation_id = b.evaluation_id " +
            "AND (:notUse OR course_type = :courseType) " +
            "ORDER BY a.evaluation_id DESC",
            nativeQuery = true)
    List<Object[]> findEvaluationMetaInfoOrderByCreateTime(
                                                @Param("notUse") Boolean notUse,
                                                @Param("courseType") Integer courseType);

    /**
     * 查询所有评价信息，并按点赞数排序
     */
    @Query(value = "SELECT a.evaluation_id, user_id, b.evaluation_content, teacher_name, " +
            "course_name, course_type, is_recommended, agree_count, disagree_count, " +
            "a.create_time FROM evaluation a, evaluation_content b " +
            "WHERE a.evaluation_id = b.evaluation_id " +
            "AND (:notUse OR course_type = :courseType) " +
            "ORDER BY a.agree_count DESC",
            nativeQuery = true)
    List<Object[]> findEvaluationMetaInfoOrderByAgreeCount(
                                                @Param("notUse") Boolean notUse,
                                                @Param("courseType") Integer courseType);

    /**
     * 查询所有评价信息，并按创建时间排序
     * @param key 关键词
     */
    @Query(value = "SELECT a.evaluation_id, user_id, b.evaluation_content, " +
            "teacher_name, course_name, course_type, is_recommended, agree_count, " +
            "disagree_count, a.create_time " +
            "FROM evaluation a, evaluation_content b " +
            "WHERE a.evaluation_id = b.evaluation_id " +
            "AND (:notUse OR course_type = :courseType) " +
            "AND b.evaluation_content LIKE :keyword " +
            "ORDER BY a.evaluation_id DESC",
            nativeQuery = true)
    List<Object[]> findEvaluationMetaInfoWithKeyWordOrderByCreateTime(
                                                @Param("notUse") Boolean notUse,
                                                @Param("courseType") Integer courseType,
                                                @Param("keyword") String key);

    /**
     * 查询所有评价信息，并按点赞数排序
     * @param key 关键词
     */
    @Query(value = "SELECT a.evaluation_id, user_id, b.evaluation_content, teacher_name, " +
            "course_name, course_type, is_recommended, agree_count, disagree_count, " +
            "a.create_time FROM evaluation a, evaluation_content b " +
            "WHERE a.evaluation_id = b.evaluation_id " +
            "AND (:notUse OR course_type = :courseType) " +
            "AND b.evaluation_content LIKE :keyword " +
            "ORDER BY a.agree_count DESC",
            nativeQuery = true)
    List<Object[]> findEvaluationMetaInfoWithKeyWordOrderByAgreeCount(
                                                @Param("notUse") Boolean notUse,
                                                @Param("courseType") Integer courseType,
                                                @Param("keyword") String key);

}
