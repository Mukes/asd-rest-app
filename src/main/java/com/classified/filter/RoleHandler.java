package com.classified.filter;

import com.asd.framework.authorisation.AbstractHandler;

import javax.ws.rs.container.ContainerRequestContext;

public class RoleHandler extends AbstractHandler<ContainerRequestContext>{
    @Override
    public Boolean authorizeRequest(ContainerRequestContext obj) {
        return true;
    }
}
