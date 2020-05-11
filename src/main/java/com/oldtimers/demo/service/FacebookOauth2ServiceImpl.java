package com.oldtimers.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Component;

/**
 * Created by korisnik on 03/05/2020.
 */
@Component
public class FacebookOauth2ServiceImpl implements FacebookOauth2Service {

    @Value("${spring.social.facebook.appId}")
    private String appId;

    @Value("${spring.social.facebook.appSecret}")
    private String appSecret;


    private FacebookConnectionFactory createConnectionFactory(){
        return  new FacebookConnectionFactory(appId,appSecret);
    }

    @Override
    public String facebookLogin() {
        OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
        oAuth2Parameters.setRedirectUri("http://localhost:8080/facebook");
        oAuth2Parameters.setScope("public_profile,email");
        return createConnectionFactory().getOAuthOperations().buildAuthenticateUrl(oAuth2Parameters);
    }

    @Override
    public String getFacebookAccessToken(String code) {
        return createConnectionFactory().getOAuthOperations().exchangeForAccess(code,"http://localhost:8080/facebook",null).getAccessToken();
    }

    @Override
    public User getFacebookUserProfile(String accessToken) {
        Facebook facebook = new FacebookTemplate(accessToken);
        String fields [] = {"id","first_name","last_name","cover","email"};
        return facebook.fetchObject("me",User.class,fields);
    }
}
