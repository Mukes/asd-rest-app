package com.classified.service;

import com.asd.framework.service.AbstractService;
import com.classified.model.UserPreference;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Zamuna on 6/17/2017.
 */
public class UserPreferenceService extends AbstractService<UserPreference> {
    private static final List<String> searchFields = Arrays.asList("name");
    public UserPreferenceService(Class<UserPreference> clazz) {
        super(clazz, searchFields);
    }
}
