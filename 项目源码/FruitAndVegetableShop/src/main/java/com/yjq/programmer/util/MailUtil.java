package com.yjq.programmer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-08 16:39
 */

/**
 * 电子邮箱工具类
 */
@Component
public class MailUtil {

    private static JavaMailSender mailSender;

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        MailUtil.mailSender = mailSender;
    }



    private static String from;

    public String getFrom() {
        return from;
    }

    @Value("${spring.mail.username}")
    public void setFrom(String from) {
        MailUtil.from = from;
    }



    private static final Logger log = LoggerFactory.getLogger(MailUtil.class);

    /**
     * 邮件发送
     * @param code
     * @param to
     */
    public static void sendMail(Integer code, String to, String context) {
        //创建简单邮件信息
        SimpleMailMessage message = new SimpleMailMessage();
        //发送者
        message.setFrom(from);
        //接收者
        message.setTo(to);
        //邮件标题
        message.setSubject("水果蔬菜商城消息提醒");
        switch(code){
            //code=1：用户注册成功邮件发送
            case 1:
                //邮件内容
                message.setText("您已成功注册水果蔬菜商城的用户，请记住您的用户名称和密码哦(^o^)");
                try {
                    mailSender.send(message);
                } catch (MailException e) {
                    e.printStackTrace();
                }
                break;
            //code=2：用户提交订单成功后邮件发送
            case 2:
                //邮件内容
                message.setText("您已在水果蔬菜商城成功下单(^o^)，订单号="+context+"。祝您体验愉快！");
                try {
                    mailSender.send(message);
                } catch (MailException e) {
                    e.printStackTrace();
                }
                break;
            default:
                log.info("没有符合条件的操作，code={}",code);
        }

    }
}
