package com.example.jwt.service;

import com.example.jwt.pojo.User;

import javax.jws.soap.SOAPBinding;

public interface UserService {
    User findUserById(String id);
    User findByUsername(String username);
}
