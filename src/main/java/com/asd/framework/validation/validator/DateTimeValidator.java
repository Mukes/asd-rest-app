package com.asd.framework.validation.validator;

import com.asd.framework.validation.constraints.DateTime;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeValidator<T> implements com.asd.framework.validation.validator.Validator {

    @Override
    public boolean validate(Annotation annotation, Object obj) {
        if (obj != null) {
            if (!obj.toString().isEmpty()) {
                //Below code is
                DateTime dateTime = (DateTime) annotation;
                SimpleDateFormat sdf = new SimpleDateFormat(dateTime.format());
                sdf.setLenient(false);
                try {
                    sdf.parse(obj.toString());
                    return true;
                } catch (ParseException e) {
                    return false;
                }
            }
        }
        return false;
    }
}
