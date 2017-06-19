package com.classified.service;

import com.asd.framework.service.AbstractService;
import com.classified.model.Category;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by Zamuna on 6/17/2017.
 */
public class CategoryService extends AbstractService<Category> {
    private static final List<String> searchFields = Arrays.asList("name", "description");
    public CategoryService(Class<Category> clazz) {
        super(clazz, searchFields);
    }
}
