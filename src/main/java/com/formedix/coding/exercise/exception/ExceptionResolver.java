package com.formedix.coding.exercise.exception;

import com.formedix.coding.exercise.model.GenericResponse;
import com.formedix.coding.exercise.model.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionResolver extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ForMedixCodingException.class, Exception.class})
    private ResponseEntity<Object> handleMqSecurityException(ForMedixCodingException ex, WebRequest request) {
        Response responseDetails = new Response();
        responseDetails.setMessage(String.format(ex.getMessage()));

        GenericResponse<Response> errorResponse = new GenericResponse<>(false,
                ex.getHttpStatus().name(), responseDetails);

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), ex.getHttpStatus(), request);
    }
}
