package com.classified.service;

import com.asd.framework.service.AbstractService;
import com.classified.model.Post;

/**
 * Created by Zamuna on 6/17/2017.
 */
public class PostService extends AbstractService<Post> {
    public PostService(Class<Post> clazz) {
        super(clazz);
    }
}
