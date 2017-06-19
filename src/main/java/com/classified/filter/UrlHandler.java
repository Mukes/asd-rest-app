package com.classified.filter;

import com.asd.framework.authorisation.AbstractHandler;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UrlHandler extends AbstractHandler<HttpServletRequest>{
    private final String[] EXCLUDED_URL = {"login"};
    @Override
    public Boolean authorizeRequest(HttpServletRequest obj) {
        String uri = obj.getRequestURI();
        //if (uri.toString().contains())
        if (Arrays.stream(EXCLUDED_URL).parallel().anyMatch(uri::contains)){
            return true;
        }else {
            return this.nextHandler.authorizeRequest(obj);
        }
    }
}
