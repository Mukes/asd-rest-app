package com.asd.framework.validation.validator;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements com.asd.framework.validation.validator.Validator {

    @Override
    public boolean validate(Annotation annotation, Object obj) {
        Pattern pattern;
        Matcher matcher;
            if (obj != null) {
                if (!obj.toString().isEmpty()) {
                    String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[a-z]{2,3}+)*(\\.[a-z]{2,3})$";
                    pattern = Pattern.compile(EMAIL_REGEX);
                    matcher = pattern.matcher(obj.toString());
                    if (matcher.matches()) {

                        return true;
                    }
                }
            }
       return false;
    }
}
