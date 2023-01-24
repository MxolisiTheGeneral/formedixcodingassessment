package com.formedix.coding.exercise.exception;

import com.formedix.coding.exercise.config.ExceptionEnum;
import org.springframework.http.HttpStatus;

public class ForMedixCodingException  extends RuntimeException{
    private static final long serialVersionUID = 8624344810222707845L;

    private final String errorDetails;
    private final ExceptionEnum exceptionEnum;

    public ForMedixCodingException(ExceptionEnum error, String message, String details) {
        super(message);
        exceptionEnum = error;
        errorDetails = details;
    }

    public ForMedixCodingException(ExceptionEnum error, String message, Throwable cause, String details) {
        super(message, cause);
        exceptionEnum = error;
        errorDetails = details;
    }

    public ForMedixCodingException(ExceptionEnum exception, String message) {
        super(message);
        exceptionEnum = exception;
        errorDetails = message;
    }

    public ExceptionEnum getMqExceptionConstantsEnum() {
        return exceptionEnum;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public HttpStatus getHttpStatus() {
        return exceptionEnum.getHttpStatus();
    }
}
