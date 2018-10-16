package com.xd.evaluation.dao.repository;

import com.xd.evaluation.domain.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created By Cx On 2018/10/9 21:43
 */
@Repository
public interface UserLikeRepository extends JpaRepository<UserLike,Long> {

    UserLike findByLikeTypeAndObjIdAndUserId(Integer likeType, Long objId, Long userId);

    @Modifying
    @Query(value = "UPDATE user_like SET is_like = :isLike " +
            "WHERE like_type = :likeType AND obj_id = :objId AND user_id = :userId",
            nativeQuery = true)
    UserLike updateIsLikeByLikeTypeAndObjIdAndUserId(@Param("likeType") Integer likeType,
                                                     @Param("objId") Long objId,
                                                     @Param("userId") Long userId,
                                                     @Param("isLike") Boolean isLike);   // 新值
}
