package com.xd.evaluation.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信小程序有关配置
 * Created By Cx On 2018/10/4 22:10
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {
    /**
     * 小程序appId
     */
    private String appid;

    /**
     * 小程序appSecret
     */
    private String secret;

    /**
     * 数据格式
     */
    private String msgDataFormat;
}
