package com.classified.model;

/**
 * Created by Crawlers on 6/18/2017.
 */
public class UserWithToken extends User{
    private String token;

    public void setUser(User user){
        setId(user.getId());
        setName(user.getName());
        setEmail(user.getEmail());
        setPhone(user.getPhone());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserWithToken{" +
            "token='" + token + '\'' +
            '}';
    }
}
