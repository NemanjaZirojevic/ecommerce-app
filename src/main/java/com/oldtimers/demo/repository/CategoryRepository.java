package com.oldtimers.demo.repository;

import com.oldtimers.demo.entity.Categories;
import com.oldtimers.demo.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by korisnik on 29/04/2020.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Categories,Integer> {


    Categories save(Categories categories);

    Categories findByCategoryId(int id);

    List<Categories> findAll();


}
