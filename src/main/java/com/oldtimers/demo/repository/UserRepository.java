package com.oldtimers.demo.repository;

import com.oldtimers.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by korisnik on 19/04/2020.
 */

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    Optional<User> findUserByUserEmail(String userEmail);

    @Override
    User save(User user);

    @Override
    void delete(User user);


}
