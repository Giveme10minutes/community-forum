package com.community.controller;

import com.community.common.Result;
import com.community.entity.User;
import com.community.service.UserService;
import com.community.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Result<Void> register(@RequestParam String userName,@RequestParam String password) {
        User user;
        try {
            userService.userExists(userName);
            user = userService.register(userName, password);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success(null);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestParam String userName,@RequestParam String password) {
        User user;
        try {
            user = userService.login(userName, password);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success(jwtUtil.generateToken(user.getId(), user.getUserName()));
    }

}
