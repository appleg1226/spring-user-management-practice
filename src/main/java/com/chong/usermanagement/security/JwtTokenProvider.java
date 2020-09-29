package com.chong.usermanagement.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${security.secretKey}")
    private String secretKey;
    private final long tokenValidTime = 30 * 60 * 1000L;

    UserDetailsService userDetailsService;
}
