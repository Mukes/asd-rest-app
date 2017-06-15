package com.classified.service;

import com.classified.model.User;
import com.asd.framework.service.AbstractService;

/**
 * Created by Crawlers on 6/13/2017.
 */
//public class UserService{
 public class UserService extends AbstractService<User> {
    public UserService(Class<User> clazz) {
        super(clazz);
    }
}
