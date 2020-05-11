package com.oldtimers.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by korisnik on 19/04/2020.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "OrderId")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "OrderUserID")
    private User user;

    @Column(name = "OrderAmount")
    private float orderAmount;

    @Column(name = "OrderShipName")
    private String orderShipName;

    @Column(name = "OrderShipAddress")
    private String orderShipAddress;

    @Column(name = "OrderShipAddress2")
    private String orderShipAddress2;

    @Column(name = "OrderCity")
    private String orderCity;

    @Column(name = "OrderState")
    private String orderState;

    @Column(name = "OrderZip")
    private String orderZip;

    @Column(name = "OrderCountry")
    private String orederCountry;

    @Column(name = "OrderPhone")
    private String orderPhone;

    @Column(name = "OrderFax")
    private String orderFax;

    @Column(name = "OrderShipping")
    private float orderShipping;

    @Column(name = "OrderTax")
    private float orderTax;

    @Column(name = "OrderEmail")
    private String orderEmail;

    @Column(name = "OrderDate")
    private Timestamp orderDate;

    @Column(name = "OrderShipped",columnDefinition = "BIT", length = 1)
    private boolean orderShipped=false;

    @Column(name = "OrderTrackingNumber")
    private String orderTrackingNumber;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderAmount=" + orderAmount +
                ", orderShipName='" + orderShipName + '\'' +
                ", orderShipAddress='" + orderShipAddress + '\'' +
                ", orderShipAddress2='" + orderShipAddress2 + '\'' +
                ", orderCity='" + orderCity + '\'' +
                ", orderState='" + orderState + '\'' +
                ", orderZip='" + orderZip + '\'' +
                ", orederCountry='" + orederCountry + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", orderFax='" + orderFax + '\'' +
                ", orderShipping=" + orderShipping +
                ", orderTax=" + orderTax +
                ", orderEmail='" + orderEmail + '\'' +
                ", orderDate=" + orderDate +
                ", orderShipped=" + orderShipped +
                ", orderTrackingNumber='" + orderTrackingNumber + '\'' +
                '}';
    }

}
