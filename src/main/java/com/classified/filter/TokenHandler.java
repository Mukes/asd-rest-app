package com.classified.filter;

import com.asd.framework.authorisation.AbstractHandler;
import com.classified.model.Authorization;
import com.classified.service.AuthorizationService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Crawlers on 6/16/2017.
 */
class TokenHandler extends AbstractHandler<HttpServletRequest>{
    private AuthorizationService authorizationService;
    @Override
    public Boolean authorizeRequest(HttpServletRequest obj) {
        String token = obj.getHeader("Authorization");
        if(!isTokenValid(token)){
            return false;
        }else {
            return this.nextHandler.authorizeRequest(obj);
        }
    }

    private boolean isTokenValid(String token){
        if (token!=null && !token.isEmpty()){
            String[] actualToken = token.split(" ");
            if (actualToken.length==2){
                token = actualToken[1];
                //query based on token.
                //check and return value.
                if (authorizationService==null){
                    authorizationService = new AuthorizationService(Authorization.class);
                }
                Authorization authorization = authorizationService.getTokenByName(token);
                if (authorization!=null){
                    return true;
                }
            }
        }
        return false;
    }
}
