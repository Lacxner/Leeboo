package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.SystemLog;
import com.gzy.leeboo.mapper.SystemLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemLogService {
    private SystemLogMapper systemLogMapper;

    @Autowired
    public void setSystemLogMapper(SystemLogMapper systemLogMapper) {
        this.systemLogMapper = systemLogMapper;
    }

    public List<SystemLog> getAllSystemLogs(String username) {
        return systemLogMapper.getAllSystemLogs(username);
    }

    public Boolean addSystemLog(SystemLog systemLog) {
        return systemLogMapper.addSystemLog(systemLog);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Integer deleteAllSystemLogs() {
        return systemLogMapper.deleteAllSystemLogs();
    }
}
