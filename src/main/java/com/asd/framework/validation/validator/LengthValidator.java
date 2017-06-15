package com.asd.framework.validation.validator;

import com.asd.framework.validation.constraints.Length;

import java.lang.annotation.Annotation;

public class LengthValidator implements com.asd.framework.validation.validator.Validator {

    @Override
    public boolean validate(Annotation annotation, Object obj) {
        if (obj != null) {
            int len = obj.toString().length();
            Length length = (Length) annotation;
            if (len < length.min() || len > length.max()) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
