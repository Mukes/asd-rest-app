package com.classified.login;

public class ProxyLogin implements ILogin{
    @Override
    public Object login(String email, String password)
    {
        ILogin login=new Login();
        return login.login(email,password);
    }

}
