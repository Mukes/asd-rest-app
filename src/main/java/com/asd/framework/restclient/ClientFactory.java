package com.asd.framework.restclient;

import java.util.ArrayList;

public class ClientFactory<T> {
    private static ClientFactory clientFactory = new ClientFactory();

    private ClientFactory(){

    }

    public static ClientFactory getClientFactory(){
        return clientFactory;
    }

    public AbstractClient<T> getClient(Method method) {
        AbstractClient client = null;
       switch (method){
           case GET:
               client = new GetClient<T>(Method.GET);
               break;
           case GET_ALL:
               client = new GetClient<T>(Method.GET_ALL);
               break;
           case PUT:
               client = new PutClient<T>(Method.PUT);
               break;
           case POST:
               client = new PostClient<T>(Method.POST);
               break;
           case DELETE:
               client = new DeleteClient<T>(Method.DELETE);
               break;
       }
        return client;
    }
}
