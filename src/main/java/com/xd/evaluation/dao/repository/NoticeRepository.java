package com.xd.evaluation.dao.repository;

import com.xd.evaluation.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Cx On 2018/10/4 22:46
 */
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
