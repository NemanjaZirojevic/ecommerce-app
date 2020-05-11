package com.oldtimers.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by korisnik on 19/04/2020.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "UserID")
    private int id;

    @NotNull
    @NotEmpty
    @Column(name = "UserEmail")
    private String userEmail;


    @Column(name = "UserPassword")
    private String userPassword;

    @NotNull
    @NotEmpty
    @Column(name = "ROLE")
    private String userRole = "USER";

    @Column(name = "UserFirstName")

    private String userFirstName;

    @Column(name = "UserLastName")
    private String userLastName;

    @Column(name = "UserCity")
    private String userCity;

    @Column(name = "UserState")
    private String userState="active";

    @Column(name = "UserZip")
    private String userZip;

    @Column(name = "UserEmailVerified",columnDefinition = "BIT",length = 1)
    private boolean userEmailVerified=false;

    @Column(name = "UserRegistrationDate")
    private Timestamp userRegistrationDate;

    @Column(name = "UserVerificationCode")
    private String userVerificationCode;

    @Column(name = "UserIP")
    private String userIp;

    @NotNull(message = "Phone number is required!")
    @NotEmpty(message = "Phone number is required!")
    @Column(name = "UserPhone")
    private String userPhone;

    @Column(name = "UserFax")
    private String userFax;

    @Column(name = "UserCountry")
    private String userCountry;

    @Column(name = "UserAddress")
    private String userAddress;

    @Column(name = "UserAddress2")
    private String userAddress2;


    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Order> orders;




}
