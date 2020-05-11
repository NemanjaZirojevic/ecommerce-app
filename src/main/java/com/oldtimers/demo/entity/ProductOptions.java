package com.oldtimers.demo.entity;

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
@Table(name = "productoptions")
public class ProductOptions {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "ProductOptionId")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OptionId")
    private Options option;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductId")
    private Products product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OptionGroupId")
    private OptionGroups optionGroups;

    @Column(name = "OptionPriceIncrement")
    private double optionPriceIncrement;
}
