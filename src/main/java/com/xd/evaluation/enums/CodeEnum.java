package com.xd.evaluation.enums;

/**
 * 用于其他枚举类实现，保证有getCode方法，使实现类能够通过EnumUtil类遍历查询code值符合要求的枚举
 * Created By Cx On 2018/10/4 21:30
 */
public interface CodeEnum {
    Integer getCode();
}
