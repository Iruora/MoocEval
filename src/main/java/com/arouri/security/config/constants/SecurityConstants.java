package com.arouri.security.config.constants;

import com.auth0.jwt.algorithms.Algorithm;

/**
 * Created by Nidhal on 13/03/2019.
 */
public class SecurityConstants {
    public static final String SECRET = "019a6931d70607c8c4b46194ace35d7956d9e79d227250d564b5be8f36418921";
    public static final long EXPIRATION_TIME = 3600*24*5*1000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final Algorithm ALGORITHM = Algorithm.HMAC512(SECRET);
}
