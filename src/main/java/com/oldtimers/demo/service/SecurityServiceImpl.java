package com.oldtimers.demo.service;

import com.oldtimers.demo.entity.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by korisnik on 04/05/2020.
 */
@Component
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public void autoLogin(String email, String password, String role, HttpServletRequest httpServletRequest) {
         log.info("[SecurityServiceImpl] autoLogin ");
        if(StringUtils.hasText(password)){
            log.info("[SecurityServiceImpl] StringUtils.hasText(password) ");
            UserDetails customUserDetails = userDetailsService.loadUserByUsername(email);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password,customUserDetails.getAuthorities()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else {
            log.info("[SecurityServiceImpl]  ");
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role));
            Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        httpServletRequest.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,SecurityContextHolder.getContext());
    }
}
