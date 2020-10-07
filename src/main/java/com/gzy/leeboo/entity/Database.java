package com.gzy.leeboo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Database implements Serializable {
    private static final long serialVersionUID = 9124601121915495101L;

    private Integer id;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime lastBackupTime;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime lastRestoreTime;

    public Database() {}

    public Database(LocalDateTime lastBackupTime, LocalDateTime lastRestoreTime) {
        this.lastBackupTime = lastBackupTime;
        this.lastRestoreTime = lastRestoreTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getLastBackupTime() {
        return lastBackupTime;
    }

    public void setLastBackupTime(LocalDateTime lastBackupTime) {
        this.lastBackupTime = lastBackupTime;
    }

    public LocalDateTime getLastRestoreTime() {
        return lastRestoreTime;
    }

    public void setLastRestoreTime(LocalDateTime lastRestoreTime) {
        this.lastRestoreTime = lastRestoreTime;
    }
}
