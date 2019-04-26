package com.example.jwt.controller;

import com.example.jwt.annotations.CheckToken;
import com.example.jwt.annotations.LoginToken;
import com.example.jwt.pojo.User;
import com.example.jwt.service.UserService;
import com.example.jwt.utils.JwtUtil;
import com.example.jwt.utils.SzpJsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping("/login")
    @LoginToken
    public SzpJsonResult login(@RequestBody @Valid User user) {
        HashMap<String,Object> hashMap=new HashMap<>();
        User userForBase = userService.findByUsername(user.getUsername());
        if (userForBase == null) {
            hashMap.put("message", "登录失败,用户不存在");

            return SzpJsonResult.errorMap(hashMap);
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                hashMap.put("message", "登录失败,密码错误");
                return SzpJsonResult.errorMap(hashMap);
            } else {
                String token = JwtUtil.createJWT(6000000, userForBase);
                hashMap.put("token", token);
                hashMap.put("user",userForBase);
                return SzpJsonResult.errorMap(hashMap);
            }
        }
    }

    //查看个人信息
    @CheckToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }


}