package com.gzy.leeboo.config.aliyun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Aliyun {
    // 连接的Id
    public static String accessKeyId;
    // 连接的密钥
    public static String accessKeySecret;

    @Value("${aliyun.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    @Value("${aliyun.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
