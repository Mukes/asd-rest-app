package com.classified.model;

import com.asd.framework.dao.Entity;
import com.asd.framework.validation.constraints.Mandatory;

/**
 * Created by 985552 on 6/15/2017.
 */
@Entity
public class Category {
    private Long id;
    @Mandatory
    private String name;
    @Mandatory
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
