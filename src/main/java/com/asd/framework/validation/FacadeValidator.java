package com.asd.framework.validation;

import com.asd.framework.error.ErrorMessage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacadeValidator<T> {
    public Map<Boolean, List<ErrorMessage>> validate(T t) {
        Map map = new HashMap<>();
        List<ErrorMessage> errorMessages = new ArrayList<>();
        boolean result = true;
        Class clazz = t.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                field.setAccessible(true);
                Object obj = null;

                try {
                    obj = field.get(t);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                ConstraintValidator<T> constraintValidator = new AdapterValidator();
                ErrorMessage errorMessage = constraintValidator.validate(field, annotation, obj);
                if (errorMessage != null) {
                    errorMessages.add(errorMessage);
                    result = false;
                }
            }

        }
        map.put(result, errorMessages);
        System.out.println("Validation Result:" + result);
        return map;
    }
}