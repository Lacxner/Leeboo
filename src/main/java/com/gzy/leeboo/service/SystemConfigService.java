package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.SystemConfig;
import com.gzy.leeboo.mapper.NoticeMapper;
import com.gzy.leeboo.mapper.SystemConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemConfigService {
    private SystemConfigMapper systemConfigMapper;
    private NoticeMapper noticeMapper;

    @Autowired
    public void setSystemConfigMapper(SystemConfigMapper systemConfigMapper) {
        this.systemConfigMapper = systemConfigMapper;
    }

    @Autowired
    public void setNoticeMapper(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public SystemConfig getSystemConfig() {
        return systemConfigMapper.getSystemConfig();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean updateSystemConfig(SystemConfig systemConfig) {
        if (!systemConfigMapper.updateSystemConfig(systemConfig) || !noticeMapper.updateNotice(systemConfig.getNotice())) {
            return false;
        }
        return true;
    }
}
