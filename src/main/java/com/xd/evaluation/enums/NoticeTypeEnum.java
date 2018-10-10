package com.xd.evaluation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created By Cx On 2018/10/9 20:08
 */
@Getter
@AllArgsConstructor
public enum NoticeTypeEnum implements CodeEnum {
    NOTICE(10,"通知"),
    ACTIVITY(11,"活动");

    private final Integer code;
    private final String msg;
}
