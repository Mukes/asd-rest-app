package com.classified.filter;

import com.asd.framework.authorisation.AbstractHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Crawlers on 6/16/2017.
 */
class TokenHandler extends AbstractHandler<HttpServletRequest>{
    @Override
    public Boolean authorizeRequest(HttpServletRequest obj) {
        String token = obj.getHeader("Authorization");
        System.out.println("Token:"+token);
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
                if (token.equals("1234"))
                    return true;
            }
        }
        return false;
    }
}
