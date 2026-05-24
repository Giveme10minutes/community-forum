package com.community.config;

import com.community.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        if (request.getRequestURI().startsWith("/post")) {
            return true;
        }
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(401);
            response.setContentType("appllication/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"未登陆\"}");
            return false;
        }

        token = token.substring(7);

        try {
            Claims claims = jwtUtil.parseToken(token);
            Long userId = Long.valueOf(claims.getSubject());
            String userName=String.valueOf(claims.get("userName"));
            request.setAttribute("userId", userId);
            request.setAttribute("userName",userName);
            return true;
        }catch(Exception e){
            response.setStatus(401);
            response.setContentType("applicaiton/json");
            response.getWriter().write("{\"code\":401,\"message\":\"登录已过期\"}");
            return false;
        }
    }
}
