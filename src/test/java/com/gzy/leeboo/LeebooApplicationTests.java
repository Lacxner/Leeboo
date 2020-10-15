package com.gzy.leeboo;

import com.gzy.leeboo.config.aliyunoss.AliyunOSSConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@SpringBootTest
class LeebooApplicationTests extends HttpServlet {
    private DataSourceProperties dataSourceProperties;
    private AliyunOSSConfig aliyunOSSConfig;

    @Autowired
    public void setDataSourceProperties(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Autowired
    public void setAliyunOSSConfig(AliyunOSSConfig aliyunOSSConfig) {
        this.aliyunOSSConfig = aliyunOSSConfig;
    }

    @Test
    void contextLoads() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(resp.getHeaders("Set-Cookie"));
    }
}