package com.classified.filter;

import com.asd.framework.authorisation.AbstractHandler;
import com.asd.framework.authorisation.IChainBuilder;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AuthorizationFilter implements ContainerRequestFilter{
    private ChainBuilder chainBuilder;

    public AuthorizationFilter() {
        this.chainBuilder = new ChainBuilder();
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        Response.ResponseBuilder responseBuilder = null;
        Response response = null;
        System.out.println("filter() on ServerAuthenticationRequestFilter");

        if (!chainBuilder.getAbstractHandler().authorizeRequest(containerRequestContext)){
            responseBuilder = Response.serverError();
            response = responseBuilder.status(Response.Status.BAD_REQUEST).build();
            containerRequestContext.abortWith(response);
        }else {
            System.out.println("Success");
        }
    }

    public class ChainBuilder implements IChainBuilder{
        private AbstractHandler abstractHandler;
        @Override
        public void buildChain() {
            AbstractHandler urlHandler = new UrlHandler();
            AbstractHandler tokenHandler = new TokenHandler();
            AbstractHandler roleHandler = new RoleHandler();
            urlHandler.nextHandler = tokenHandler;
            tokenHandler.nextHandler = roleHandler;
            abstractHandler = urlHandler;
        }

        public AbstractHandler getAbstractHandler() {
            return abstractHandler;
        }

    }
}
