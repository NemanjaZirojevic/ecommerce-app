package com.oldtimers.demo.helpermodel;

import com.oldtimers.demo.entity.Order;
import com.oldtimers.demo.entity.Products;
import com.oldtimers.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by korisnik on 05/05/2020.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {

    @Min(value = 0L, message = "The value must be positive")
    private int orderAmount;

    private String orderShipName;

    private String orderShipAddress;

    @NotEmpty
    private String orderCity;

    @NotEmpty
    private String orderZip;

    @NotEmpty
    private String orederCountry;

    @NotEmpty
    private String orderPhone;

    private String orderFax;

    private float orderShipping;

    private float orderTax;

    @NotNull
    @Email(message = "Email should be valid")
    private String orderEmail;

    @FutureOrPresent
    private Timestamp orderDate;

    private boolean orderShipped=false;

    private String orderTrackingNumber;

    private String products;

    private String token;

    private boolean cardPayment;

    public Order createOrder(User user){
        Order order = new Order();
        order.setUser(user);
        order.setOrderCity(this.orderCity);
        order.setOrderAmount(this.orderAmount);
        order.setOrderShipAddress(this.orderShipAddress);
        order.setOrderShipAddress2(this.orderShipAddress);
        order.setOrderState(this.orederCountry);
        order.setOrderZip(this.orderZip);
        order.setOrederCountry(this.orederCountry);
        order.setOrderEmail(this.orderEmail);
        order.setOrderShipping(0);
        order.setOrderTax(0);
        order.setOrderFax("fax");
        order.setOrderDate(new Timestamp(new Date().getTime()));
        order.setOrderPhone(this.orderPhone);
        order.setOrderShipName("bla");
        return order;
    }
}
