package com.oldtimers.demo.controller;

import com.oldtimers.demo.entity.Categories;
import com.oldtimers.demo.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by korisnik on 29/04/2020.
 */
@Slf4j
@Secured("ROLE_ADMIN")
@RequestMapping(value = "categories")
@Controller
public class ProductCategoriesAdministrationController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/display")
    public String displayAdmingPage(){
        log.info("[ProductCategoriesAdministrationController] displayAdminPage()");
        return "categories";
    }


    @PostMapping("/add")
    public ResponseEntity addNewCategory(@Valid @RequestBody Categories category, Errors errors){
        log.info("[ProductCategoriesAdministrationController] addNewCategory()");
        log.info("[ProductCategoriesAdministrationController] category : "+category);
        Categories newCategory = categoryRepository.save(category);
        return new ResponseEntity(newCategory, HttpStatus.CREATED);

    }



}
