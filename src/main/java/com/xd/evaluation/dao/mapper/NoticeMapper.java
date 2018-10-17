package com.xd.evaluation.dao.mapper;

import com.xd.evaluation.domain.Notice;

public interface NoticeMapper {
    int deleteByPrimaryKey(Long noticeId);

    int insert(Notice notice);

    Notice selectByPrimaryKey(Long noticeId);

    int updateByPrimaryKey(Notice notice);
}