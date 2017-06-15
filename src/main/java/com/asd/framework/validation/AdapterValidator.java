package com.asd.framework.validation;

import com.asd.framework.error.ErrorMessage;
import com.asd.framework.error.ErrorSource;
import com.asd.framework.validation.constraints.*;
import com.asd.framework.validation.validator.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AdapterValidator<T> implements ConstraintValidator<T> {
    @Override
    public ErrorMessage validate(Field field, Annotation annotation, Object obj) {
        Validator validator = getValidator(annotation);
        Boolean isValid = validator.validate(annotation, obj);
        if (!isValid) {
            return getErrorMessage(field, annotation);
        }
        return null;
    }

    private Validator getValidator(Annotation annotation) {
        Validator validator = null;
        if (annotation instanceof Email)
            validator = new EmailValidator();
        else if (annotation instanceof DateTime)
            validator = new DateTimeValidator();
        else if (annotation instanceof Length)
            validator = new LengthValidator();
        else if (annotation instanceof Mandatory)
            validator = new MandatoryValidator();
        else if (annotation instanceof com.asd.framework.validation.constraints.Number)
            validator = new NumberValidator();
        else if (annotation instanceof Password)
            validator = new PasswordValidator();
        else if (annotation instanceof Url)
            validator = new UrlValidator();
        return validator;
    }

    private ErrorMessage getErrorMessage(Field field, Annotation annotation) {
        ErrorMessage errorMessage = null;
        if (annotation instanceof Email)
            errorMessage = new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                String.format("%s is invalid", field.getName()),
                CAUSED_MESSAGE);
        else if (annotation instanceof DateTime) {
            DateTime dateTime = (DateTime) annotation;
            errorMessage = new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                String.format("%s should be in %s format", field.getName(), dateTime.format()),
                CAUSED_MESSAGE);
        } else if (annotation instanceof Length) {
            Length length = (Length) annotation;
            errorMessage = new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                String.format("%s should be %d to %d characters", field.getName(), length.min(), length.max()),
                CAUSED_MESSAGE);
        } else if (annotation instanceof Mandatory)
            errorMessage = new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                String.format("%s should not be null", field.getName()), CAUSED_MESSAGE);
        else if (annotation instanceof com.asd.framework.validation.constraints.Number)
            errorMessage = new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                String.format("%s is not a number", field.getName()),
                CAUSED_MESSAGE);
        else if (annotation instanceof Password)
            errorMessage = new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                String.format("%s is not a valid password", field.getName()),
                CAUSED_MESSAGE);
        else if (annotation instanceof Url)
            errorMessage = new ErrorMessage(ErrorSource.FIELD_ERROR, field.getName(),
                String.format("%s url is invalid", field.getName()),
                CAUSED_MESSAGE);
        return errorMessage;
    }
}
