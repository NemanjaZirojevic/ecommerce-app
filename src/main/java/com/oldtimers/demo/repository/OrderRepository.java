package com.oldtimers.demo.repository;

import com.oldtimers.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by korisnik on 04/05/2020.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

     Order save(Order order);

}
