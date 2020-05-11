package com.oldtimers.demo.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by korisnik on 03/05/2020.
 */
@Service
public interface SecurityService {

    void autoLogin(String username, String password, String role, HttpServletRequest httpServletRequest);
}
