package com.ysu.graduationproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

@SpringBootTest
class GraduationprojectApplicationTests {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;

    @Test
    void contextLoads() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("这是测试邮件主题"); // 邮件主题
        msg.setFrom(mailProperties.getUsername()); // 邮件发送者
        msg.setTo("1161107215@qq.com"); // 邮件接收者，可以有多个
//        msg.setCc("799737179@qq.com"); // 邮件抄送人，可以有多个
//        msg.setBcc("799737179@qq.com"); // 隐秘抄送人，可以有多个
        msg.setSentDate(new Date()); // 邮件发送日期
        msg.setText("这是测试邮件内容"); // 邮件正文
        javaMailSender.send(msg);
    }

}
