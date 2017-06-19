package com.classified.service;

import com.asd.framework.service.AbstractService;
import com.classified.model.Post;

import java.util.Arrays;
import java.util.List;

public class PostService extends AbstractService<Post> {
    private static final List<String> searchFields = Arrays.asList("title", "price", "description", "negotiable");
    public PostService(Class<Post> clazz) {
        super(clazz, searchFields);
    }
}
