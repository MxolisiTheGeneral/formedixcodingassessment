package com.formedix.coding.exercise.config;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

    FOR_MEDIX_EXCEPTION("Some technical error occurred - Please contact ForMedix.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorTypeEnum.RETRYABLE),
    ERROR_READING_FROM_DATABASE("An error occurred when trying to read from the database.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorTypeEnum.RETRYABLE),
    ERROR_PERSISTING_TO_DATABASE("An error occurred when trying to write to the database.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorTypeEnum.RETRYABLE),
    NO_DATA_FOUND_EXCEPTION("No data found for provided agreement reference number.", HttpStatus.NOT_FOUND, ErrorTypeEnum.RETRYABLE);

    private String message;
    private HttpStatus httpStatus;
    private ErrorTypeEnum errorType;

    ExceptionEnum(String message, HttpStatus httpStatus, ErrorTypeEnum errorType) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ErrorTypeEnum getErrorType() {
        return errorType;
    }
}
