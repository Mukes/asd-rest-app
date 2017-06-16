package com.classified.filter;

import com.asd.framework.authorisation.AbstractHandler;

import javax.ws.rs.container.ContainerRequestContext;

/**
 * Created by Crawlers on 6/16/2017.
 */
class TokenHandler extends AbstractHandler<ContainerRequestContext>{
    @Override
    public Boolean authorizeRequest(ContainerRequestContext obj) {
        String token = obj.getHeaderString("Authorization");
        System.out.println("Token:"+token);
        if(!isTokenValid(token)){
            return false;
        }else {
            return this.nextHandler.authorizeRequest(obj);
        }
    }

    private boolean isTokenValid(String token){
        return true;
    }
}
