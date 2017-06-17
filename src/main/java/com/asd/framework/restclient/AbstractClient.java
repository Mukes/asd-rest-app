package com.asd.framework.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class AbstractClient<T> {
    //protected String uri;
    protected Method method;
    protected Map<String, String> headerMap;
    protected T data;

    Integer[] successCodes = {200, 201};

    public abstract Object execute(String uri, Class clazz);


    public AbstractClient data(T t) {
        this.data = t;
        return this;
    }

    public AbstractClient headers(Map<String, String> map) {
        this.headerMap = map;
        return this;
    }
}
