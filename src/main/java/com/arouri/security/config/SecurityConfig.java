package com.arouri.security.config;

import com.arouri.security.config.filters.JWTAuthenticationFilter;
import com.arouri.security.config.filters.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Nidhal on 13/03/2019.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService) // we need here to create a class that implements from UserDetailService
                .passwordEncoder(bCryptPasswordEncoder);
        // userDetainService will verify well get a crypted pass that will be encoded via bCryptPasswordEncoder
    }
    //------------------------------------------------------------------------------------------------------------------

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // we make csrf disabled to enjoy stateless
        http.csrf().disable()
        // we have no need to create session as we are using a stateless way : JWT
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // here we authorize landing on login page
                .authorizeRequests()
                .antMatchers("/login/**")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/users/**")
                .hasAnyAuthority("ADMIN")
                // any other request needs to be authenticated
                .anyRequest().authenticated()
                .and()
                // authenticationFilter is the one which is going to attemptAuthentication and with its success will generate a token
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                // AuthorizationFilter is the filter which is going to verify whether coming requests are authorized
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
