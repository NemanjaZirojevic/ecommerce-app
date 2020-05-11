package com.oldtimers.demo.service;

import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;

/**
 * Created by korisnik on 03/05/2020.
 */
@Service
public interface FacebookOauth2Service {
    String facebookLogin();
    String getFacebookAccessToken(String code);
    User getFacebookUserProfile(String accessToken);
}
