package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Log;
import com.gzy.leeboo.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogService {
    private LogMapper LogMapper;

    @Autowired
    public void setLogMapper(LogMapper LogMapper) {
        this.LogMapper = LogMapper;
    }

    public List<Log> getAllLogs(String username) {
        return LogMapper.getAllLogs(username);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean addLog(Log Log) {
        return LogMapper.addLog(Log);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Integer deleteAllLogs() {
        return LogMapper.deleteAllLogs();
    }
}
