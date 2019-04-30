package com.arouri.security.config.service.impl;

import com.arouri.entities.AppUser;
import com.arouri.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Nidhal on 13/03/2019.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountService accountService;

    @Autowired
    public UserDetailsServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    //=============================================================
    @Override
    // loadUserByUsername : if the user exists it will retrieve and load it
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser u = accountService.findUserByUsername(username);
        if (u == null) throw new UsernameNotFoundException("username not found");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        u.getRoles().forEach(
                r -> {
                    authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
                }
        );
        // the password passed in parameter to the User below is encrypted and that's why we have specified the encoder in
        // SecurityConfig.class
        //Here we return a spring security User object that implements UserDetails interface and which is loaded
        // with some the AppUser object's username, password and authorities
        return new User(u.getUsername(), u.getPassword(), authorities);
    }
}
