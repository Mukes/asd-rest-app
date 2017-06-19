package com.classified.service;

import com.asd.framework.service.AbstractService;
import com.classified.model.Message;

import java.util.Arrays;
import java.util.List;

public class MessageService extends AbstractService<Message> {
    private static final List<String> searchFields = Arrays.asList("text");
    public MessageService(Class<Message> clazz) {
        super(clazz, searchFields);
    }
}
