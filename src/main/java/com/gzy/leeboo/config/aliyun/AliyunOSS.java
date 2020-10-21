package com.gzy.leeboo.config.aliyun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliyunOSS {
    // 连接的Id
    public static String accessKeyId;
    // 连接的密钥
    public static String accessKeySecret;
    // 连接区域地址
    public static String endpoint;
    // 需要存储的bucketName
    public static String bucketName;
    // 图片保存路径
    public static String url;

    @Value("${aliyun.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    @Value("${aliyun.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    @Value("${aliyun.oss.endpoint}")
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Value("${aliyun.oss.bucketName}")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @Value("${aliyun.oss.url}")
    public void setUrl(String url) {
        this.url = url;
    }
}
