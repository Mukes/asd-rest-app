package com.classified.model;

import com.asd.framework.dao.Entity;

@Entity
public class User {
    private Integer id;
    private String email;
    private String name;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(Integer id, String email) {
        this.id = id;
        this.email = email;
        this.name=name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", email='" + email + '\'' +
            '}';
    }
}
