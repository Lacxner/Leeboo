package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.SystemConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemConfigMapper {
    SystemConfig getSystemConfig();

    Boolean updateSystemConfig(SystemConfig systemConfig);
}
