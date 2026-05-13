package com.community.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.community.common.Exception.BusinessException;
import com.community.entity.User;
import com.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    @Autowired
    private UserMapper userMapper;
    public void userExists(String userName) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUserName, userName);
        User user = userMapper.selectOne(wrapper);

        if (!(user == null)) {
            throw new BusinessException("用户已经存在");
        }
    }

    public User register(String userName, String rawPassword) {
        User user = new User();
        user.setUserName(userName);
        String encode = passwordEncoder.encode(rawPassword);
        user.setPassword(encode);
        userMapper.insert(user);
        return user;
    }

    public User login(String userName, String rawPassword) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        User user = userMapper.selectOne(wrapper);

        if (user == null) {
            throw new BusinessException("用户名字或密码错误");
        }

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BusinessException("用户名字或密码错误");
        }

        return user;
    }
}
