package com.classified.service;

import com.classified.model.User;
import com.asd.framework.service.AbstractService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService extends AbstractService<User> {
    public UserService(Class<User> clazz) {
        super(clazz);
    }

    public long login(String uname, String password) {
        Long id = 0l;
        Map<String, String> map = new HashMap<>();
        map.put("email", uname);
        map.put("password", password);
        List<User> users = customGetAll(map);
        if (users.size() > 0) {
            System.out.println("Login Successfull");
            id = users.get(0).getId();
            return id;
        } else {
            System.out.println("User name or password incorrect");
        }
        return id;
    }
}
