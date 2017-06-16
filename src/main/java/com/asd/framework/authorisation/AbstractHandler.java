package com.asd.framework.authorisation;

public abstract class AbstractHandler<T> {
    public AbstractHandler nextHandler;
    public abstract Boolean authorizeRequest(T t);
    //public abstract Boolean canHandleRequest(Object obj);
}

