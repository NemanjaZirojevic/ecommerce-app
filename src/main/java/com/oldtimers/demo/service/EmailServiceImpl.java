package com.oldtimers.demo.service;

import com.oldtimers.demo.entity.Order;
import com.oldtimers.demo.helpermodel.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * Created by korisnik on 06/05/2020.
 */
@Component
public class EmailServiceImpl implements IEmailService {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Override
    public void sendOrderToEmail(OrderForm orderForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(orderForm.getOrderEmail());
        message.setFrom("nemanjax90@gmail.com");
        message.setText("Your order has been successefully proceeded!");
        javaMailSender.send(message);
    }
}
