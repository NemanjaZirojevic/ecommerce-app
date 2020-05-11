package com.oldtimers.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by korisnik on 19/04/2020.
 */
public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        String userRole = user.getUserRole();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole);
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getUserState().equalsIgnoreCase("active");
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getUserState().equalsIgnoreCase("active");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getUserState().equalsIgnoreCase("active");
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
