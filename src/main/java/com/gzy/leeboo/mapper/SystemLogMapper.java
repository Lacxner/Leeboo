package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.SystemLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemLogMapper {
    List<SystemLog> getAllSystemLogs(String username);

    Boolean addSystemLog(SystemLog systemLog);

    Integer deleteAllSystemLogs();
}
