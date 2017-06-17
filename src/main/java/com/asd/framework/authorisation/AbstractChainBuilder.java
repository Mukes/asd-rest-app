package com.asd.framework.authorisation;

public abstract class AbstractChainBuilder {
    protected AbstractHandler abstractHandler;

    protected AbstractChainBuilder(){
        buildChain();
    }

    public abstract void buildChain();
}
