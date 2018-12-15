package com.xd.evaluation.controller;

import com.xd.evaluation.VO.ResultVO;
import com.xd.evaluation.domain.Notice;
import com.xd.evaluation.service.NoticeService;
import com.xd.evaluation.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Cx On 2018/10/18 18:33
 */
@RestController
@Slf4j
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    //获取所有type类的公告概要(不包括公告内容)，公告类型，10：通知，11：活动
    @GetMapping("/list")
    public ResultVO getAllNotice(int type){
        return ResultUtil.success(noticeService.getAllByType(type));
    }

    @GetMapping("/detail/{noticeId}")
    public ResultVO getDetailByNoticeId(@PathVariable()Long noticeId){
        return ResultUtil.success(noticeService.getDetailByNoticeId(noticeId));
    }
}
