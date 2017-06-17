package com.classified.filter;

import com.asd.framework.authorisation.AbstractHandler;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;

public class RoleHandler extends AbstractHandler<HttpServletRequest>{
    @Override
    public Boolean authorizeRequest(HttpServletRequest obj) {
        return true;
    }
}
