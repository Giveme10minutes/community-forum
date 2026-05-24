package com.community.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JwtUtilTeatSpringBoot {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void testJwtUtil() {
        Long userId = 1L;
        String userName = "cy";
        String token=jwtUtil.generateToken(userId,userName);

        Claims claims= jwtUtil.parseToken(token);
        assertEquals(userId,Long.valueOf(claims.getSubject()));
        assertEquals(userName,claims.get("userName", String.class));
    }
}
