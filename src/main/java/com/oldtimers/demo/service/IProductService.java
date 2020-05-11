package com.oldtimers.demo.service;
import com.oldtimers.demo.ProductsWrapper;
import com.oldtimers.demo.entity.Categories;
import org.springframework.stereotype.Service;


/**
 * Created by korisnik on 08/05/2020.
 */
@Service
public interface IProductService {

    ProductsWrapper findAll(int pageNo , int pageSize, String sortBy);
    ProductsWrapper findAllByProductCategoryId(Categories category,int pageNo,int pageSize,String sortBy);
}
