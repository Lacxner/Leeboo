package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMapper {
    List<Log> getAllLogs(String username);

    Boolean addLog(Log Log);

    Integer deleteAllLogs();
}
