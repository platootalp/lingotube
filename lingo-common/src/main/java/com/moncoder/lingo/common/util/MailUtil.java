package com.moncoder.lingo.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Moncoder
 * @version 1.0
 * @description 邮件发送工具类
 * @date 2024/11/30 18:45
 */
//@Component
//public class MailUtil {
//    @Autowired
//    private JavaMailSender javaMailSender;
//    @Autowired
//    private MailProperties mailProperties;
//
//    @Async
//    public void sendSimpleMail(String toAccount, String subject, String content) {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom(mailProperties.getUsername());
//            message.setTo(toAccount);
//            message.setSubject(subject);
//            message.setText(content);
//            javaMailSender.send(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
