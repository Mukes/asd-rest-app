package com.asd.framework.validation.validator;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements com.asd.framework.validation.validator.Validator {

    @Override
    public boolean validate(Annotation annotation, Object obj) {
        Pattern pattern;
        Matcher matcher;
        if (obj != null) {
            if (!obj.toString().isEmpty()) {
                String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$";
                pattern = Pattern.compile(PASSWORD_REGEX);
                matcher = pattern.matcher(obj.toString());
                if (matcher.matches()) {
                    return true;
                }
            }
        }
        return false;
    }
}
