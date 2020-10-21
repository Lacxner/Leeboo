package com.gzy.leeboo.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gzy.leeboo.config.aliyun.Aliyun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class SMSCodeService {
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 使用Aliyun SMS发送验证码短信，默认使用6位验证码
     * @param phone 接收短信的手机号码
     * @return 是否发送成功
     */
    public Boolean sendShortMessage(String phone) {
        try {
            // regionId无需改动
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", Aliyun.accessKeyId, Aliyun.accessKeySecret);
            // 生成客户端
            IAcsClient client = new DefaultAcsClient(profile);

            // 设置短信服务的参数
            CommonRequest request = new CommonRequest();
            // 无需改动
            request.setSysMethod(MethodType.POST);
            request.setSysDomain("dysmsapi.aliyuncs.com");
            request.setSysVersion("2017-05-25");
            request.setSysAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            // 接收短信的手机号码
            request.putQueryParameter("PhoneNumbers", phone);
            // 短信签名名称
            request.putQueryParameter("SignName", "LeeBoo");
            // 短信模板ID
            request.putQueryParameter("TemplateCode", "SMS_204761503");
            // 模板变量必须使用JSON格式字符串
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> map = new HashMap<>();
            // 生成短信验证码
            String smsCode = generateSMCode(6);
            map.put("code", smsCode);
            String paramJSON = objectMapper.writeValueAsString(map);
            // 短信模板变量对应的实际值
            request.putQueryParameter("TemplateParam", paramJSON);

            CommonResponse response = client.getCommonResponse(request);
            // 判断短信是否发送成功
            boolean isSendSuccess = response.getHttpResponse().isSuccess();
            // 只有当短信发送成功时才将短信验证码存入Redis
            if (isSendSuccess) {
                stringRedisTemplate.opsForValue().set(phone, smsCode, 5, TimeUnit.MINUTES);
                return true;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 随机生成纯数字并且指定长度的短信验证码
     * @param length 短信验证码的长度
     * @return 短信验证码字符串
     */
    private String generateSMCode(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 1; i <= length; i++) {
            sb.append(new Random().nextInt(10) + 1);
        }
        return sb.toString();
    }
}
