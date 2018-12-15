package com.xd.evaluation.service;

import com.xd.evaluation.domain.Notice;
import com.xd.evaluation.dto.NoticeDetail;

import java.util.List;

/**
 * Created By Cx On 2018/10/20 19:22
 */
public interface NoticeService {

    /**
     * 通过公告类型，获取所有该类型的公告概要（即不包括content）
     * 公告类型，10：通知，11：活动
     */
    List<Notice> getAllByType(int type);

    List<NoticeDetail> getDetailByNoticeId(Long noticeId);
}
