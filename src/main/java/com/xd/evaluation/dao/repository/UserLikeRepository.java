package com.xd.evaluation.dao.repository;

import com.xd.evaluation.domain.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Cx On 2018/10/9 21:43
 */
@Repository
public interface UserLikeRepository extends JpaRepository<UserLike,Long> {

    UserLike findByLikeTypeAndObjIdAndUserId(Integer likeType, Long objId, Long userId);
}
