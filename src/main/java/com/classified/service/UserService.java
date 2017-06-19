package com.classified.service;

import com.classified.model.Authorization;
import com.classified.model.User;
import com.asd.framework.service.AbstractService;
import com.classified.model.UserWithToken;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService extends AbstractService<User> {
    private static final List<String> searchFields = Arrays.asList("name");
    public UserService(Class<User> clazz) {
        super(clazz, searchFields);
    }

    public UserWithToken login(String uname, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("email", uname);
        map.put("password", password);
        List<User> users = customGetAll(map);
        if (users.size() > 0) {
            User user = users.get(0);
            Long id = user.getId();
            AuthorizationService authorizationService = new AuthorizationService(Authorization.class);
            String token=authorizationService.generateToken(id);
            UserWithToken userWithToken = new UserWithToken();
            userWithToken.setUser(user);
            userWithToken.setToken(token);
            return userWithToken;
        }
        return null;
    }
}
