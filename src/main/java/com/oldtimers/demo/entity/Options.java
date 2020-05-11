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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "options")
public class Options {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "OptionId")
    private int id;

    @Column(name = "OptionName")
    private String optionName;

    @ManyToOne
    @JoinColumn(name = "OptionGroupID")
    OptionGroups optionGroups;

}
