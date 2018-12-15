package com.xd.evaluation.service.impl;

import com.xd.evaluation.dao.repository.NoticeRepository;
import com.xd.evaluation.domain.Notice;
import com.xd.evaluation.dto.NoticeDetail;
import com.xd.evaluation.enums.NoticeTypeEnum;
import com.xd.evaluation.exception.EvaluationException;
import com.xd.evaluation.service.NoticeService;
import com.xd.evaluation.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created By Cx On 2018/10/20 19:22
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeRepository noticeRepository;

    @Override
    public List<Notice> getAllByType(int type) {
        if (EnumUtil.getByCode(type, NoticeTypeEnum.class) == null){
            //如果type值有误
            log.error("[获取公告]获取失败，公告类型不存在，type={}",type);
            throw new EvaluationException("获取失败，公告类型不存在");
        }
        return noticeRepository.findAllByType(type);
    }

    @Override
    public List<NoticeDetail> getDetailByNoticeId(Long noticeId) {
        return noticeRepository.findDetailByNoticeId(noticeId);
    }
}
