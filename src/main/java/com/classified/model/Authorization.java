package com.classified.model;

import com.asd.framework.dao.Entity;

import java.time.LocalDate;

/**
 * Created by Zamuna on 6/17/2017.
 */
@Entity
public class Authorization {
    private Long id;
    private Long userId;
    private String token;
    private LocalDate expiryDate;

    public Authorization(){

    }
    public Authorization(Long credentials, String token, LocalDate now) {
        this.userId=credentials;
        this.token=token;
        this.expiryDate=now;
    }

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDate getDate() {
        return expiryDate;
    }

    public void setDate(LocalDate date) {
        this.expiryDate = date;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                ", userId=" + userId +
                ", token='" + token + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
