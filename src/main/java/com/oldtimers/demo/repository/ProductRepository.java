package com.oldtimers.demo.repository;

import com.oldtimers.demo.ProductsWrapper;
import com.oldtimers.demo.entity.Categories;
import com.oldtimers.demo.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by korisnik on 29/04/2020.
 */
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Products,Integer> {

    Products save(Products product);
    Page<Products> findAllByProductCategoryId(Categories categories,Pageable pageable);
    List<Products> findAllByProductCategoryId(Categories categories);
}
