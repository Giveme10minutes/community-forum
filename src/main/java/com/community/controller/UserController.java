package com.community.controller;

import com.community.common.Result;
import com.community.dto.RegisterRequest;
import com.community.entity.User;
import com.community.service.UserService;
import com.community.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Result<Void> register(@Valid @RequestBody RegisterRequest request) {
        userService.userExists(request.getUserName());
        User user = userService.register(request.getUserName(), request.getPassword());
        return Result.success(null);
    }

    @PostMapping("/login")
    public Result<String> login(@Valid @RequestBody RegisterRequest request) {
        User user;
        user = userService.login(request.getUserName(), request.getPassword());
        return Result.success(jwtUtil.generateToken(user.getId(), user.getUserName()));
    }

}
