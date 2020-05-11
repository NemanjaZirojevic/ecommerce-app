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
@Table(name = "optiongroups")
public class OptionGroups {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "OptionGroupID")
    private int optionGroupId;

    @Column(name = "OptionGroupName")
    private String optionGroupName;
}
