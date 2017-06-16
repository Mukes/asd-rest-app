package com.classified.service;

import com.classified.model.User;
import com.asd.framework.service.AbstractService;

import java.util.List;

/**
 * Created by Crawlers on 6/13/2017.
 */
//public class UserService{
 public class UserService extends AbstractService<User> {
    public UserService(Class<User> clazz) {
        super(clazz);
    }

    public long login(String uname,String password){
         Long id=0l;
        List<User> users =  customGetAll("email = '"+uname+"' and  password = '"+password+"'", User.class);
        if(users.size()>0){
           id=users.get(0).getId();
           return id;
        }
        else{
            System.out.println("User name or password incorrect");
        }
        return id;
    }
}
