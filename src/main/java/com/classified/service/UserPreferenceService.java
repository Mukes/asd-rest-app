package com.classified.service;

import com.asd.framework.service.AbstractService;
import com.classified.model.UserPreference;

import java.util.Arrays;
import java.util.List;

public class UserPreferenceService extends AbstractService<UserPreference> {
    private static final List<String> searchFields = Arrays.asList("name");
    public UserPreferenceService(Class<UserPreference> clazz) {
        super(clazz, searchFields);
    }
}
