package com.oldtimers.demo.repository;

import com.oldtimers.demo.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by korisnik on 04/05/2020.
 */
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer>{

    OrderDetails save(OrderDetails orderDetails);
}
