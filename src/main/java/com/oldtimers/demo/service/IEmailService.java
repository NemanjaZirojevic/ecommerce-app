package com.oldtimers.demo.service;

import com.oldtimers.demo.entity.Order;
import com.oldtimers.demo.helpermodel.OrderForm;
import org.springframework.stereotype.Service;

/**
 * Created by korisnik on 06/05/2020.
 */
@Service
public interface IEmailService {

    void sendOrderToEmail(OrderForm form);
}
