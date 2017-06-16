package com.classified.login;

import com.classified.model.User;
import com.classified.service.UserService;

import java.util.List;

public class Login implements ILogin{
    public UserService userService;
    @Override
    public Long login(String email, String password) {
        userService=new UserService(User.class);
        return userService.login(email,password);
    }

}
