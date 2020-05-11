package com.oldtimers.demo;

import com.oldtimers.demo.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by korisnik on 09/05/2020.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsWrapper {

    private List<Products> products;

    private int totalPagesNo;
}
