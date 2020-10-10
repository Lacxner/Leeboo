package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.SystemConfig;
import com.gzy.leeboo.mapper.SystemConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemConfigService {
    private SystemConfigMapper systemConfigMapper;

    @Autowired
    public void setSystemInfoMapper(SystemConfigMapper systemConfigMapper) {
        this.systemConfigMapper = systemConfigMapper;
    }

    public SystemConfig getSystemConfig() {
        return systemConfigMapper.getSystemConfig();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean updateSystemConfig(SystemConfig systemConfig) {
        return systemConfigMapper.updateSystemConfig(systemConfig);
    }
}
