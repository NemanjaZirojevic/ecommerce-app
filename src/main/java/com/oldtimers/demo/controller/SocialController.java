package com.oldtimers.demo.controller;

import com.oldtimers.demo.repository.UserRepository;
import com.oldtimers.demo.service.FacebookOauth2Service;
import com.oldtimers.demo.service.SecurityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by korisnik on 03/05/2020.
 */
@Controller
@Slf4j
public class SocialController {


    @Autowired
    private FacebookOauth2Service facebookService;

    @Autowired
    private SecurityServiceImpl securityService;

    @Autowired
    private UserRepository userRepository;



    @GetMapping(value = "/facebooklogin")
    public RedirectView facebookLogin(){
        log.info("[SocialController] facebookLogin()");
        RedirectView redirectView = new RedirectView();
        String url = facebookService.facebookLogin();
        redirectView.setUrl(url);
        return redirectView;
    }

    @GetMapping(value = "/facebook")
    public String facebook(@RequestParam("code") String code){
        log.info("[SocialController] facebook()");
       String accessToken = facebookService.getFacebookAccessToken(code);
       return "redirect:facebookprofiledata/"+accessToken;
    }

    @GetMapping(value = "/facebookprofiledata/{accessToken:.+}")
    public String facebookProfileData(@PathVariable("accessToken") String token,HttpServletRequest httpServletRequest){
        User user = facebookService.getFacebookUserProfile(token);
        Optional<com.oldtimers.demo.entity.User> user1 = userRepository.findUserByUserEmail(user.getEmail());
        String role = "ROLE_USER";
        if(user1.isPresent() && user1.get()!=null){
            log.info("[SocialController] facebookProfileData() user present");
            log.info("[SocialController] facebookProfileData() user : "+user1.get().toString());
            user1.get().setUserFirstName(user.getFirstName());
            user1.get().setUserLastName(user.getLastName());
            if(user1.get().getUserAddress()!=null) {
                user1.get().setUserAddress(user.getAddress().getCountry());
            }
            userRepository.save(user1.get());
        }else {
            log.info("[SocialController] facebookProfileData() user not present");
            com.oldtimers.demo.entity.User user2 = new com.oldtimers.demo.entity.User();
            user2.setUserFirstName(user.getFirstName());
            user2.setUserLastName(user.getLastName());
            user2.setUserEmail(user.getEmail());
            user2.setUserState("active");
            user2.setUserPhone(user.getEmail());
            user2.setUserAddress(user.getAddress().getCountry());
            user2.setUserRole(role);
            userRepository.save(user2);
        }
        log.info("[SocialController] facebookProfileData()");
        securityService.autoLogin(user.getEmail(),null,"ROLE_USER",httpServletRequest);
        return "redirect:/home";
    }
}
