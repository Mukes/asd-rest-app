package com.classified.filter;

import com.asd.framework.authorisation.AbstractHandler;

import javax.ws.rs.container.ContainerRequestContext;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UrlHandler extends AbstractHandler<ContainerRequestContext>{
    private final String[] EXCLUDED_URL = {"login"};
    @Override
    public Boolean authorizeRequest(ContainerRequestContext obj) {
        URI uri = obj.getUriInfo().getRequestUri();
        System.out.println("uri:"+uri);
        //if (uri.toString().contains())
        if (Arrays.stream(EXCLUDED_URL).parallel().anyMatch(uri.toString()::contains)){
            return true;
        }else {
            return this.nextHandler.authorizeRequest(obj);
        }
    }
}
