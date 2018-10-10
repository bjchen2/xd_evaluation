package com.xd.evaluation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created By Cx On 2018/10/4 22:06
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(0,"请求成功"),
    ERROR(1,"请求失败");

    private final Integer code;
    private final String msg;
}
