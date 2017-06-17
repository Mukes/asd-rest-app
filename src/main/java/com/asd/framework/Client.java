package com.asd.framework;

import com.asd.framework.error.ErrorMessage;
import com.asd.framework.restclient.ClientFactory;
import com.asd.framework.restclient.Method;
import com.asd.framework.validation.FacadeValidator;
import com.classified.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Crawlers on 6/12/2017.
 */
public class Client {
    public static void main(String[] args) {
        User user = new User();
        user.setEmail("email");
        user.setPhone("pass");
        user.setName("pass");
        user.setPassword("pass");
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer 1234");
        List<User> user1 =  (List<User>)ClientFactory.getClientFactory()
        //User user1 =  (User) ClientFactory.getClientFactory()
            .getClient(Method.GET_ALL).headers(map).data(user)
            .execute("http://localhost:8080/api/user", User.class);
    }
}
