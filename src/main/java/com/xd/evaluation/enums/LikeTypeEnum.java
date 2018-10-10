package com.xd.evaluation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created By Cx On 2018/10/9 20:08
 */
@Getter
@AllArgsConstructor
public enum LikeTypeEnum implements CodeEnum {
    EVALUATION(10,"评价"),
    COMMENT(11,"评论");

    private final Integer code;
    private final String msg;
}
