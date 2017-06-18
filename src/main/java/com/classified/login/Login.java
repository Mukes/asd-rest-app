package com.classified.login;

import com.classified.model.User;
import com.classified.service.UserService;

import java.util.List;

public class Login implements ILogin{
    private UserService userService;
    @Override
    public Object login(String email, String password) {
        userService=new UserService(User.class);
        return userService.login(email,password);
    }

}
