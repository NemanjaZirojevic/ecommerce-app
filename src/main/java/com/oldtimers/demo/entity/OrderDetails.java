package com.oldtimers.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by korisnik on 19/04/2020.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderdetails")
public class OrderDetails {



    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "DetailID")
    private int detailId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "DetailOrderID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "DetailProductId")
    private Products detailProductId;

    @Column(name = "DetailName")
    private String detailName;

    @Column(name = "DetailPrice")
    private float detailPrice;

    @Column(name = "DetailSKU")
    private  String detailSku;

    @Column(name = "DetailQuantity")
    private int detailQuantity;

    @Override
    public String toString() {
        return "OrderDetails{" +
                "detailId=" + detailId +
                ", detailProductId=" + detailProductId.getProductId() +
                ", detailName='" + detailName + '\'' +
                ", detailPrice=" + detailPrice +
                ", detailSku='" + detailSku + '\'' +
                ", detailQuantity=" + detailQuantity +
                '}';
    }


}
