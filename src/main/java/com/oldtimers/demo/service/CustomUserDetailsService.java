package com.oldtimers.demo.service;

import com.oldtimers.demo.entity.CustomUserDetails;
import com.oldtimers.demo.entity.User;
import com.oldtimers.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by korisnik on 19/04/2020.
 */
@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUserEmail(userEmail);
        log.info("[CustomUserDetailsService.class] userEmail : "+userEmail);
        log.info("[CustomUserDetailsService.class] user: "+user.get());
        if (user.isPresent()) {
            return new CustomUserDetails(user.get());
        }
            throw new UsernameNotFoundException("User not found!");
    }
}
