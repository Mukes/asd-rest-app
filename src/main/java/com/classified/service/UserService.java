package com.classified.service;

import com.asd.framework.service.AbstractService;
import com.classified.model.User;

/**
 * Created by Crawlers on 6/13/2017.
 */
public class UserService extends AbstractService<User> {
    public UserService(Class<User> clazz) {
        super(clazz);
    }
}
