package com.classified.model;

import com.asd.framework.dao.Entity;

/**
 * Created by 985552 on 6/15/2017.
 */
@Entity
public class UserPreference {
    private Long id;
    private Long userId;
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
