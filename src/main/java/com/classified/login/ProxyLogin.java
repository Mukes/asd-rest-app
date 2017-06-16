package com.classified.login;

public class ProxyLogin implements ILogin{
    @Override
    public Long login(String email, String password)
    {
        ILogin login=new Login();
        Long uid=0l;
        uid=login.login(email,password);
        return uid;
    }

    public static void main(String[] args) {
        ILogin proxyLogin=new ProxyLogin();
        System.out.println(proxyLogin.login("zam@gmail.comm","123"));
    }

}
