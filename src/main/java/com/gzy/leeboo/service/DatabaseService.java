package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Database;
import com.gzy.leeboo.mapper.DatabaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class DatabaseService {
    private DataSourceProperties dataSourceProperties;
    private DatabaseMapper databaseMapper;

    @Autowired
    public void setDataSourceProperties(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Autowired
    public void setDatabaseMapper(DatabaseMapper databaseMapper) {
        this.databaseMapper = databaseMapper;
    }

    public Database getDatabase() {
        return databaseMapper.getDatabase();
    }

    public Boolean backupDatabase() {
        // 数据库用户名
        String username = dataSourceProperties.getUsername();
        // 数据库密码
        String password = dataSourceProperties.getPassword();
        // 连接的数据库名称
        String name;
        int beginIndex = dataSourceProperties.getUrl().indexOf("/", 13);
        if (dataSourceProperties.getUrl().indexOf("?") != -1) {
            int endIndex = dataSourceProperties.getUrl().indexOf("?");
            name = dataSourceProperties.getUrl().substring(beginIndex + 1, endIndex);
        } else {
            name = dataSourceProperties.getUrl().substring(beginIndex);
        }
        try {
            // 在Linux中的备份数据库操作
            String[] cmd = new String[] {
                    "/bin/sh",
                    "-c",
                    "/usr/bin/mysqldump -u" + username + " -p" + password + " " + name + " > /" + name + "/backup.sql"
            };
            Process exec = Runtime.getRuntime().exec(cmd);
            // 判断是否备份成功
            if (exec.waitFor() == 0) {
                databaseMapper.updateDatabase(new Database(LocalDateTime.now(), null));
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean restoreDatabase() {
        // 数据库用户名
        String username = dataSourceProperties.getUsername();
        // 数据库密码
        String password = dataSourceProperties.getPassword();
        // 连接的数据库名称
        String name;
        int beginIndex = dataSourceProperties.getUrl().indexOf("/", 13);
        if (dataSourceProperties.getUrl().indexOf("?") != -1) {
            int endIndex = dataSourceProperties.getUrl().indexOf("?");
            name = dataSourceProperties.getUrl().substring(beginIndex + 1, endIndex);
        } else {
            name = dataSourceProperties.getUrl().substring(beginIndex);
        }
        try {
            // 在Linux中的还原数据库操作
            String[] cmd = new String[] {
                    "/bin/sh",
                    "-c",
                    "/usr/bin/mysql -u" + username + " -p" + password + " " + name + " < /" + name + "/backup.sql"
            };
            Process exec = Runtime.getRuntime().exec(cmd);
            // 判断是否还原成功
            if (exec.waitFor() == 0) {
                databaseMapper.updateDatabase(new Database(null, LocalDateTime.now()));
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
