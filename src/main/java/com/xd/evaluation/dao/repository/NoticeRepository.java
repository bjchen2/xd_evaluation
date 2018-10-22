package com.xd.evaluation.dao.repository;

import com.xd.evaluation.domain.Notice;
import com.xd.evaluation.dto.NoticeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Cx On 2018/10/4 22:46
 */
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByType(int type);

    //todo 好像不支持驼峰命名，要自己取别名
    @Query(value = "select a.notice_id noticeId,type,title,author_name authorName,image," +
            "notice_content noticeContent,a.create_time createTime " +
            "from notice a,notice_content b " +
            "where a.notice_id = :noticeId and a.notice_id = b.notice_id",nativeQuery = true)
    List<NoticeDetail> findDetailByNoticeId(@Param("noticeId") Long noticeId);
}
