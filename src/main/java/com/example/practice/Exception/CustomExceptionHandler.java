package com.example.practice.Exception;

import com.example.practice.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class CustomExceptionHandler {

    // This is designed to handle all the exception to this class within bracker, so the same class is passed as a parameter

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handle(ConstraintViolationException constraintViolationException) {
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        String errorMessage = "";
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            violations.forEach(violation -> builder.append(" " + violation.getMessage()+", "));
            errorMessage = builder.toString();
        } else {
            errorMessage = "ConstraintViolationException occured.";
        }
        return ResponseHandler.generateResponse("Bad Request", HttpStatus.BAD_REQUEST, null, errorMessage);
    }


    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handleException(BindException ex) {

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        // to store errors
        StringBuffer err = new StringBuffer();
        for (FieldError fieldError : errors) {
                err.append(fieldError.getField()+", ");
        }

        return ResponseHandler.generateResponse("Bad Request", HttpStatus.BAD_REQUEST, null, err.toString()+"these fields cannot be blank");
    }

}
