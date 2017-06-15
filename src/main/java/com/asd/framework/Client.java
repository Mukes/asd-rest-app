package com.asd.framework;

import com.asd.framework.error.ErrorMessage;
import com.asd.framework.model.User;
import com.asd.framework.validation.FacadeValidator;

import java.util.List;
import java.util.Map;

/**
 * Created by Crawlers on 6/12/2017.
 */
public class Client {
    public static void main(String[] args) {
        User user = new User("");
        FacadeValidator<User> facadeValidator = new FacadeValidator();
        Map map=facadeValidator.validate(user);
        map.forEach((k, v)-> {
            System.out.println("Key:"+k);
            ((List)v).forEach(msg-> System.out.println("Message:"+((ErrorMessage)msg).getMessage()));
        });
    }
}
