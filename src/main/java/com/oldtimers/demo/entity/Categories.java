package com.oldtimers.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by korisnik on 19/04/2020.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productcategories")
public class Categories {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )

    @Column(name = "CategoryId")
    private int categoryId;

    @JsonProperty("categoryName")
    @Column(name = "CategoryName")
    @NotNull(message = "Category name should not be empty!")
    @NotEmpty(message = "Category name should not be empty!")
    private String categoryName;


    @OneToMany(mappedBy = "productCategoryId",fetch = FetchType.LAZY)
    private List<Products> productsList;

    @Override
    public String toString() {
        return "Categories{" +
                "categoryId=" + categoryId +
                '}';
    }
}
