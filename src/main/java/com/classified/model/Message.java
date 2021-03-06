package com.classified.model;

import com.asd.framework.dao.Entity;
import com.asd.framework.validation.constraints.Mandatory;
import com.asd.framework.validation.constraints.Number;

/**
 * Created by 985552 on 6/15/2017.
 */
@Entity
public class Message {
    private Long id;
    @Number
    @Mandatory
    private Long senderId;
    @Number
    @Mandatory
    private Long receiverId;
    @Mandatory
    private String text;
    private Boolean read;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
}
