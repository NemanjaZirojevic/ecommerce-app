package com.oldtimers.demo.service;

import com.oldtimers.demo.ProductsWrapper;
import com.oldtimers.demo.entity.Categories;
import com.oldtimers.demo.entity.Products;
import com.oldtimers.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by korisnik on 08/05/2020.
 */
@Slf4j
@Component
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;


    @Override
    public ProductsWrapper findAll(int pageNo , int pageSize, String sortBy) {
        ProductsWrapper productsWrapper = new ProductsWrapper();
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Products> pagedResult = productRepository.findAll(pageable);
        if(pagedResult.hasContent()) {
             productsWrapper.setProducts(pagedResult.getContent());
             productsWrapper.setTotalPagesNo(pagedResult.getTotalPages());
        } else {
            List<Products> products = new ArrayList<>();
            productsWrapper.setProducts(products);
            productsWrapper.setTotalPagesNo(0);
        }
        return productsWrapper;
    }

    @Override
    public ProductsWrapper findAllByProductCategoryId(Categories category, int pageNo, int pageSize, String sortBy) {
        log.info("[ProductServiceImpl] findAllByProductCategoryId() => "+category.getCategoryId() + ", "+pageNo+" , "+pageSize+" , "+sortBy);
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        Page<Products> productsPage = productRepository.findAllByProductCategoryId(category,pageable);
        List<Products> list = productRepository.findAllByProductCategoryId(category);
        list.stream().forEach(System.out::println);
        ProductsWrapper productsWrapper = new ProductsWrapper();
        if(productsPage.hasContent()){
            productsWrapper.setTotalPagesNo(productsPage.getTotalPages());
            productsWrapper.setProducts(productsPage.getContent());
        }else {
            List<Products> products = new ArrayList<>();
            productsWrapper.setProducts(products);
            productsWrapper.setTotalPagesNo(0);
        }
        return productsWrapper;
    }
}
