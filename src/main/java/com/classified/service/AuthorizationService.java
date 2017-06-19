package com.classified.service;

import com.asd.framework.service.AbstractService;
import com.classified.model.Authorization;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Zamuna on 6/17/2017.
 */
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
//        System.out.println(lst.get(0).getDate().toString());
//        System.out.println(lst.get(0).getDate().isBefore(LocalDate.now()));
        if (lst.size() > 0) {
            token = lst.get(0).getToken();
            LocalDate date = lst.get(0).getDate();
            updateCustom(lst.get(0).getid(), "expiryDate='" + LocalDate.now().plusDays(1) + "'", Authorization.class);
            System.out.println("Token already exist");
            return token;
        } else {
            token = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(LocalDate.now());
            Long id = (Long) insert(new Authorization(credentials, token, LocalDate.now()));
            map.put("id", String.valueOf(id));
            List<Authorization> authorizations = customGetAll(map);
            System.out.println(authorizations.get(0).getToken());
            return authorizations.get(0).getToken();
        }
    }

    public Authorization getTokenByName(String token) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        List<Authorization> authorizations = customGetAll(map);
        if (authorizations.size() > 0) {
            return authorizations.get(0);
        }
        return null;
    }
}
