package com.asd.framework.error;

import java.io.Serializable;

/**
 * Created by 985552 on 6/12/2017.
 */
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private com.asd.framework.error.ErrorSource source = com.asd.framework.error.ErrorSource.UNKNOWN_ERROR;
    private String field;
    private String message;
    private String cause;

    public ErrorMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(com.asd.framework.error.ErrorSource source, Throwable throwable) {
        this.source = source;
        this.message = throwable.toString();
        //addCause(throwable);
    }

    public ErrorMessage(Throwable throwable) {
        this.message = throwable.toString();
        //addCause(throwable);
    }

    public ErrorMessage(String message, String cause) {
        this.source = com.asd.framework.error.ErrorSource.UNKNOWN_ERROR;
        this.message = message;
        this.cause = cause;
    }

    public ErrorMessage(com.asd.framework.error.ErrorSource source, String message) {
        this.source = source;
        this.message = message;
    }

    public ErrorMessage(com.asd.framework.error.ErrorSource source, String field, String message) {
        this.source = source;
        this.field = field;
        this.message = message;
    }

    public ErrorMessage(com.asd.framework.error.ErrorSource source, String field, String message, String cause) {
        this.source = source;
        this.field = field;
        this.message = message;
        this.cause = cause;
    }

    public com.asd.framework.error.ErrorSource getSource() {
        return source;
    }

    public void setSource(com.asd.framework.error.ErrorSource source) {
        this.source = source;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    /*private void addCause(Throwable throwable) {
        StringWriter errors = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errors));
        this.cause = errors.toString();
    }*/
}
