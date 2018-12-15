package com.xd.evaluation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xd.evaluation.utils.serializer.Date2LongSerializer;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created By Cx On 2018/10/22 18:29
 */
public interface NoticeDetail {
    //公告id
    Long getNoticeId();
    //公告类型，10：通知，11：活动
    int getType();
    //公告标题
    String getTitle();
    //公告内容
    String getNoticeContent();
    //作者名
    String getAuthorName();
    //图片url
    String getImage();
    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    Date getCreateTime();
}
