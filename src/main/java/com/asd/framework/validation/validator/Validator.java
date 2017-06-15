package com.asd.framework.validation.validator;

import java.lang.annotation.Annotation;

public interface Validator<T> {
    boolean validate(Annotation annotation, T t);
}
