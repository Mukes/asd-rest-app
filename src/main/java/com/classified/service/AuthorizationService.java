package com.classified.service;

import com.asd.framework.service.AbstractService;
import com.classified.model.Authorization;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

public class AuthorizationService extends AbstractService<Authorization> {
    private static final List<String> searchFields = Arrays.asList("name");

    public AuthorizationService(Class<Authorization> clazz) {
        super(clazz, searchFields);
    }

    public String generateToken(Long credentials) {
        String token = "";
        Map<String, String> map = new HashMap<>();
        map.put("userId", String.valueOf(credentials));
        List<Authorization> lst = customGetAll(map);
        if (lst.size() > 0) {
            token = lst.get(0).getToken();
            LocalDate date = lst.get(0).getDate();
            updateCustom(lst.get(0).getid(), "expiryDate='" + LocalDate.now().plusDays(1) + "'", Authorization.class);
            return token;
        } else {
            token = UUID.randomUUID().toString().replaceAll("-", "");
            Long id = (Long) insert(new Authorization(credentials, token, LocalDate.now()));
            map.put("id", String.valueOf(id));
            List<Authorization> authorizations = customGetAll(map);
            return authorizations.get(0).getToken();
        }
    }

    public Authorization getTokenByName(String token) {
        Map<String, String> map = new HashMap<>();
        List<Authorization> authorizations = customGetAll(map);
        if (authorizations.size() > 0) {
            return authorizations.get(0);
        }
        return null;
    }
}
