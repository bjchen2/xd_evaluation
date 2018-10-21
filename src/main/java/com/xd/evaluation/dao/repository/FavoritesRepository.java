package com.xd.evaluation.dao.repository;

import com.xd.evaluation.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Cx On 2018/10/4 22:45
 */
@Repository
public interface FavoritesRepository extends JpaRepository<Favorite, Long> {

    Favorite findByEvaluationIdAndUserId(Long evaluationId, Long userId);
}
