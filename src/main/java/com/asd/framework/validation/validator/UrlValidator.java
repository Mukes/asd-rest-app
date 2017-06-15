package com.asd.framework.validation.validator;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidator implements com.asd.framework.validation.validator.Validator {

    @Override
    public boolean validate(Annotation annotation, Object obj) {
        Pattern pattern;
        Matcher matcher;
        if (obj != null) {
            if (!obj.toString().isEmpty()) {
                String WEBSITE_REGEX = "^(http(s{0,1}):\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
                pattern = Pattern.compile(WEBSITE_REGEX);
                matcher = pattern.matcher(obj.toString());
                if (matcher.matches()) {
                    return true;
                }
            }
        }
        return false;
    }
}

