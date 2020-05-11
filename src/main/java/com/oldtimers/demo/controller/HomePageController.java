package com.oldtimers.demo.controller;

import com.oldtimers.demo.ProductsWrapper;
import com.oldtimers.demo.entity.Categories;
import com.oldtimers.demo.repository.CategoryRepository;
import com.oldtimers.demo.repository.ProductRepository;
import com.oldtimers.demo.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created by nemanja.zirojevic on 04/05/2020.
 */
@Secured({"ROLE_USER","ROLE_ADMIN"})
@Controller
@Slf4j
public class HomePageController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;


    @GetMapping("/")
    public RedirectView redirectToHomePage(){
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/home");
        return redirectView;
    }


    @GetMapping("/products/{categoryId}")
    public @ResponseBody
    ProductsWrapper getAllProducts(@PathVariable Integer categoryId,
                                   @RequestParam(value = "pageNo",defaultValue = "0") Integer pageNo,
                                   @RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize,
                                   @RequestParam(value = "sortBy",defaultValue = "productPrice") String sortBy){
        if(categoryId == -1){
            log.info("[HomePageController] getAllProducts() => categoryId = -1");
            return productServiceImpl.findAll(pageNo,pageSize,sortBy);
        }
        Categories categories = new Categories();
        categories.setCategoryId(categoryId);
        return productServiceImpl.findAllByProductCategoryId(categories,pageNo,pageSize,sortBy);
    }

    @GetMapping("/categories/all")
    @ResponseBody
    public List<Categories> getAllCategories(){
        log.info("[ProductCategoriesAdministrationController] getAllCategories()");
        return categoryRepository.findAll();
    }




}
