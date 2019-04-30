package com.arouri.security.config.filters;

import com.arouri.entities.AppUser;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.arouri.security.config.constants.SecurityConstants.*;

/**
 * Created by Nidhal on 13/03/2019.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }
    // -----------------------------------------------------------------------------------------------------------------

    @Override
    // this method will be run when ever a user attempt to authenticate
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // the user that we'll get is in JSON format that's why we need jaxon object mapper
        try {

            AppUser user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
            // give to spring security the user that aims to authenticate in order to verify whether he's able or not
            // on success spring sec will invoke loadUserByUsername() from UserDetailsServiceImpl
            // if it's all good after checking password the successfulAuthentication will be invoked and now we'll generate the token
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("BAD REQUEST :::: " + e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User springUser = (User) authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        springUser.getAuthorities().forEach(
                grantedAuthority -> roles.add(grantedAuthority.getAuthority())
        );


        String jwtToken = JWT.create()
                .withIssuer(request.getRequestURI())
                .withSubject(springUser.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .sign(ALGORITHM);
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwtToken);

    }
}
