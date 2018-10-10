package com.xd.evaluation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created By Cx On 2018/10/9 20:08
 */
@Getter
@AllArgsConstructor
public enum CourseTypeEnum implements CodeEnum {
    SCHOOL_ELECTIVE(10,"校选修"),
    REQUIRED(11,"必修"),
    EXPERIMENT(12,"实验"),
    ;

    private final Integer code;
    private final String msg;
}
