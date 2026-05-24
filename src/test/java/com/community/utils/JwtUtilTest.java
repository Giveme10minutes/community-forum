package com.community.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JwtUtilTest {

    private JwtUtil jwtUtil = new JwtUtil();

    @BeforeEach
    void loadApplication() {
        ReflectionTestUtils.setField(jwtUtil, "jwtExpire", 3600000L);
        ReflectionTestUtils.setField(jwtUtil, "SECRET", "ieuysgfiuzdvcg213y0edoiudfgsdoiuyfe");
    }

    @Test
    void testJwtUtil() {
        Long userId = 1L;
        String userName = "cy";
        String token = jwtUtil.generateToken(userId, userName);

        Claims claims = jwtUtil.parseToken(token);
        assertEquals(userId, Long.valueOf(claims.getSubject()));
        assertEquals(userName, claims.get("userName", String.class));
    }
}
