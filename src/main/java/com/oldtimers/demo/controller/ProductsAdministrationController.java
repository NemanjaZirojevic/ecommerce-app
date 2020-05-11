package com.oldtimers.demo.controller;

import com.oldtimers.demo.entity.Categories;
import com.oldtimers.demo.entity.Products;
import com.oldtimers.demo.repository.CategoryRepository;
import com.oldtimers.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by korisnik on 29/04/2020.
 */

@Slf4j
@Secured("ROLE_ADMIN")
@RequestMapping(value = "products")
@Controller
public class ProductsAdministrationController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;


    @GetMapping("/display")
    public String displayAdmingPage(){
        log.info("[AdministrationController] displayAdminPage()");
        return "products";
    }

    @PostMapping("/add")
    public ResponseEntity addNewProduct(@Valid @RequestBody Products product,@RequestParam("categoryId") int categoryId){
        log.info("[AdministrationController] addNewProduct()");
        log.info("[AdministrationController] addNewProduct() categoryId : "+categoryId);
        Categories category = categoryRepository.findByCategoryId(categoryId);
        product.setProductCategoryId(category);
        if(category.getProductsList() == null){
            ArrayList<Products> products = new ArrayList<>();
            products.add(product);
            category.setProductsList(products);
        }else {
            category.getProductsList().add(product);
        }
        categoryRepository.save(category);
        Products newProduct = productRepository.save(product);
        category.getProductsList().stream().forEach(System.out::println);
        return new ResponseEntity(newProduct, HttpStatus.CREATED);

    }


}
