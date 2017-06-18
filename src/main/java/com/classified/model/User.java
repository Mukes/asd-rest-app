package com.classified.model;

import com.asd.framework.dao.Entity;
import com.asd.framework.validation.constraints.*;
import com.asd.framework.validation.constraints.Number;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by 985552 on 6/15/2017.
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User implements Serializable{
    private Long id;
    private String name;
    @Email
    @Password
    @Number
    @Url
    @DateTime
    @Length(min = 10, max = 500)
    private String email;
    private String phone;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
