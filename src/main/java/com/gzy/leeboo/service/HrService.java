package com.gzy.leeboo.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.gzy.leeboo.config.aliyun.AliyunOSS;
import com.gzy.leeboo.dto.AuthenticateHr;
import com.gzy.leeboo.dto.BasicHr;
import com.gzy.leeboo.dto.ResetPassword;
import com.gzy.leeboo.entity.Hr;
import com.gzy.leeboo.exception.PhoneNotFoundException;
import com.gzy.leeboo.mapper.HrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@CacheConfig(cacheNames = "hr")
@Service
public class HrService implements UserDetailsService {
    private HrMapper hrMapper;

    @Autowired
    public void setHrMapper(HrMapper hrMapper){
        this.hrMapper = hrMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.getHrByUsername(username);
        // 这里的 UsernameNotFoundException 默认会被封装成 BadCredentialsException 异常，最终被 ExceptionTranslationFilter 拦截处理
        if (hr == null) {
            throw new UsernameNotFoundException("用户名或密码错误！");
        }
        return hr;
    }

    public UserDetails loadUserByPhone(String phone) throws PhoneNotFoundException {
        Hr hr = hrMapper.getHrByPhone(phone);
        if (hr == null) {
            throw new PhoneNotFoundException("手机号码不存在！");
        }
        return hr;
    }

    public BasicHr getHrById(Integer id) {
        return hrMapper.getHrById(id);
    }

    @Cacheable(key = "'allHrs'")
    public List<BasicHr> getAllHrs() {
        return hrMapper.getAllHrs();
    }

    public Integer getHrCountsByRoleId(Integer roleId) {
        return hrMapper.getHrCountsByRoleId(roleId);
    }

    public Integer getHrCountsByRoleIds(List<Integer> roleIds) {
        return hrMapper.getHrCountsByRoleIds(roleIds);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allHrs'")
    public Boolean addHr(AuthenticateHr authenticateHr) {
        return hrMapper.addHr(authenticateHr);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allHrs'")
    public Boolean updateHr(Hr hr) {
        return hrMapper.updateHr(hr);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allHrs'")
    public Boolean updateHrPassword(ResetPassword resetPassword) {
        return hrMapper.updateHrPassword(resetPassword);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allHrs'")
    public Boolean updateHrEnabled(BasicHr basicHr) {
        return hrMapper.updateHrEnabled(basicHr);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allHrs'")
    public Boolean updateHrAvatar(BasicHr basicHr) {
        return hrMapper.updateHrAvatar(basicHr);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allHrs'")
    public Boolean deleteHrById(Integer id) {
        return hrMapper.deleteHrById(id);
    }

    /**
     * 上传头像
     * @param file 前端传来的头像文件
     * @return Aliyun OSS中的访问URL
     */
    public String upload(MultipartFile file) {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(AliyunOSS.endpoint, AliyunOSS.accessKeyId, AliyunOSS.accessKeySecret);
        InputStream inputStream = null;
        // 头像的访问URL
        String fileUrl = null;
        try {
            inputStream = file.getInputStream();
            // 上传文件的全路径
            String  objectName;
            // 上传文件名前面加上时间戳
            long instant = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
                Hr hr = (Hr) authentication.getPrincipal();
                objectName = AliyunOSS.url + "/" + hr.getUsername() + "/" + instant + "-" + file.getOriginalFilename();
                // 上传文件流
                ossClient.putObject(AliyunOSS.bucketName,  objectName, inputStream);
                // 添加后删除原有头像
                String avatarUrl = hrMapper.getAvatarById(hr.getId());
                String originalFilename = avatarUrl.substring(avatarUrl.lastIndexOf("/") + 1);
                String originalObjectName = AliyunOSS.url + "/" + hr.getUsername() + "/" + originalFilename;
                if (ossClient.doesObjectExist(AliyunOSS.bucketName, originalObjectName)) {
                    ossClient.deleteObject(AliyunOSS.bucketName, originalObjectName);
                }
            } else {
                objectName = AliyunOSS.url + "/" + instant + "-" + file.getOriginalFilename();
                ossClient.putObject(AliyunOSS.bucketName,  objectName, inputStream);
            }
            fileUrl = "http://" + AliyunOSS.bucketName + "." + AliyunOSS.endpoint + "/" + objectName;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 关闭OSSClient
            ossClient.shutdown();
        }
        return fileUrl;
    }
}
