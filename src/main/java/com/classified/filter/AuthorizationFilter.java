package com.classified.filter;

import com.asd.framework.authorisation.AbstractHandler;
import com.asd.framework.authorisation.AbstractChainBuilder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = { "/*" })
public class AuthorizationFilter implements Filter{
    private ChainBuilder chainBuilder;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.chainBuilder = new ChainBuilder();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (!chainBuilder.getAbstractHandler().authorizeRequest(httpServletRequest)){
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setStatus(Response.Status.FORBIDDEN.getStatusCode());
            return;
        }else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
        /*if (containPath || user!=null){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login");
        }*/
    }

    @Override
    public void destroy() {

    }

    public class ChainBuilder extends AbstractChainBuilder {

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
