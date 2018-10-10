package com.xd.evaluation.dto;

import lombok.Data;

/**
 * 获取小程序数据的封装类
 * Created By Cx On 2018/10/4 22:15
 */
@Data
public class WxInfo {
    /**
     * 临时登录凭证code码
     */
    private String code;
    /**
     * 用户敏感信息加密数据
     */
    private String encryptedData;
    /**
     * 加密算法的初始向量
     */
    private String iv;
}
