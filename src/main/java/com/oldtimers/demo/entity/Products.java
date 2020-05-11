package com.oldtimers.demo.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by korisnik on 19/04/2020.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "ProductId")
    private int productId;

    @Column(name = "ProductSKU")
    private String productSKU;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "ProductPrice")
    private float productPrice;

    @Column(name = "ProductWeight")
    private float productWeight;

    @Column(name = "ProductCartDesc")
    private String productCartDesc;

    @Column(name = "ProductShortDesc")
    private String productShortDesc;

    @Column(name = "ProductLongDesc",columnDefinition = "TEXT")
    private String productLongDesc;

    @Column(name = "ProductThumb")
    private String productThumb;

    @Column(name = "ProductImage")
    private String productImage;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ProductCategoryId")
    private Categories productCategoryId;

    @Column(name = "ProductUpdateDate")
    private Timestamp productUpdateDate;

    @Column(name = "ProductStock")
    private float productStock;

    @Column(name = "ProductLive",columnDefinition = "BIT", length = 1)
    private boolean productLive=false;

    @Column(name = "ProductUnlimited",columnDefinition = "BIT",length = 1)
    private boolean productUnlimited=false;

    @Column(name = "ProductLocation")
    private String productLocation;


    @JsonIgnore
    @OneToMany(mappedBy = "detailProductId")
    List<OrderDetails> orderDetails;

    @Transient
    @JsonProperty("orderDetailsToSet")
    List<OrderDetails> orderDetailsToSet;

    @Transient
    @JsonProperty("productQuantity")
    private int productQuantity;

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", productSKU='" + productSKU + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productWeight=" + productWeight +
                ", productCartDesc='" + productCartDesc + '\'' +
                ", productShortDesc='" + productShortDesc + '\'' +
                ", productLongDesc='" + productLongDesc + '\'' +
                ", productThumb='" + productThumb + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productCategoryId=" + productCategoryId +
                ", productUpdateDate=" + productUpdateDate +
                ", productStock=" + productStock +
                ", productLive=" + productLive +
                ", productUnlimited=" + productUnlimited +
                ", productLocation='" + productLocation + '\'' +
                ", orderDetails=" + orderDetailsToSet +
                ", productQuantity=" + productQuantity +
                '}';
    }
}
