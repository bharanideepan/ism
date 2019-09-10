package com.ideas2it.ism.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String... args) {
        try {
        	SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(args[0]);
	        msg.setSubject(args[1]);
	        msg.setText(args[2]);
	        javaMailSender.send(msg);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

}
