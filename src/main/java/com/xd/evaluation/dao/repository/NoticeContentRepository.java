package com.xd.evaluation.dao.repository;

import com.xd.evaluation.domain.NoticeContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Cx On 2018/10/20 19:32
 */
@Repository
public interface NoticeContentRepository extends JpaRepository<NoticeContent,Long> {
}
