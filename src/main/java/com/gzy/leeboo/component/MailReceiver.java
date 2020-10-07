package com.gzy.leeboo.component;

import com.gzy.leeboo.entity.Employee;
import com.gzy.leeboo.entity.Hr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;

/**
 * 该组件中的 <code>handle</code> 方法会接受 RabbitMQ 中的消息，然后发送邮件。
 */
@Component
@ConfigurationProperties(prefix = "mail.welcome")
public class MailReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailReceiver.class);

    private JavaMailSender javaMailSender;
    private MailProperties mailProperties;
    private TemplateEngine templateEngine;
    private String company;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    public void setMailProperties(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Autowired
    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @RabbitListener(queues = "mail.welcome")
    public void handle(Employee employee) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            // 设置邮件主题
            mimeMessageHelper.setSubject("入职通知");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // 设置发送方
            if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof Hr) {
                    Hr hr = (Hr) principal;
                    mimeMessageHelper.setFrom(new InternetAddress(mailProperties.getUsername(), hr.getName(), "UTF-8"));
                }
            }
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            // 设置接收方
            mimeMessageHelper.setTo(employee.getEmail());

            // 设置Thymeleaf模板引擎的上下文，从而传入数据
            Context context = new Context();
            context.setVariable("name", employee.getName());
            context.setVariable("company", company);
            context.setVariable("department", employee.getDepartment().getName());
            context.setVariable("position", employee.getPosition().getName());
            context.setVariable("rank", employee.getRank().getName());
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
            context.setVariable("beginDate", dateTimeFormatter.format(employee.getBeginDate()));
            context.setVariable("conversionDate", dateTimeFormatter.format(employee.getConversionDate()));
            // 模板引擎进行渲染
            String mailContent = templateEngine.process("mail", context);
            // 设置邮件内容
            mimeMessageHelper.setText(mailContent, true);
            // 发送邮件
            javaMailSender.send(mimeMessage);
            LOGGER.info("邮件发送成功！成功给 " + employee.getName() + " 发送入职通知邮件[" + employee.getEmail() + "]");
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            LOGGER.error("邮件发送失败！");
        }
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
