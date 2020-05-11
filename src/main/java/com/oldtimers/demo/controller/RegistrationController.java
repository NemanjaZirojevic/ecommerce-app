package com.oldtimers.demo.controller;
import com.oldtimers.demo.entity.User;
import com.oldtimers.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

/**
 * Created by nemanja.zirojevic on 20/04/2020.
 */
@Controller
@Slf4j
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping(value = "/register")
    public String registrationHandler(@Valid User user, Errors errors){
        log.info("[RegistrationController] user : " +user.toString());
        if(errors.hasErrors())
        {
            return "singup";
        }
        String passwordEncoded = bCryptPasswordEncoder.encode(user.getUserPassword());
        user.setUserPassword(passwordEncoded);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/singup")
    public String singupPage(@ModelAttribute User user){
        return "singup";
    }
}
