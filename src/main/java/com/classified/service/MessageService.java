package com.classified.service;

import com.asd.framework.service.AbstractService;
import com.classified.model.Message;

/**
 * Created by Zamuna on 6/17/2017.
 */
public class MessageService extends AbstractService<Message> {
    public MessageService(Class<Message> clazz) {
        super(clazz);
    }
}
