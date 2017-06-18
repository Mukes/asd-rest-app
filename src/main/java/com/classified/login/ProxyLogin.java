package com.classified.login;

public class ProxyLogin implements ILogin{
    @Override
    public Object login(String email, String password)
    {
        ILogin login=new Login();
        Long uid=0l;
        return login.login(email,password);
    }

    public static void main(String[] args) {
        ILogin proxyLogin=new ProxyLogin();
        proxyLogin.login("zam@gmail.comm","123");
        proxyLogin.login("zam@gmail.com","123");
    }

}
