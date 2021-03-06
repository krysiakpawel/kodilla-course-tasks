package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Optional;

@Service
public class SimpleEmailService {


    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);

    public void send(final Mail mail){
        LOGGER.info("Starting mail preparation...");

        try {
            javaMailSender.send(createMailMessage(mail));

            LOGGER.info("E-mail has been sent successfully");

        } catch (MailException e) {
            LOGGER.error("Failed to process email sending", e.getMessage(), e);

        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(Optional.ofNullable(mail.getToCc()).orElse(null));
        return mailMessage;
    }
}
