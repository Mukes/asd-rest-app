package com.asd.framework.model;

import com.asd.framework.dao.Entity;

/**
 * Created by Crawlers on 6/12/2017.
 */
@Entity
public class User {
    private Integer id;
    private String email;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", email='" + email + '\'' +
            '}';
    }
}
