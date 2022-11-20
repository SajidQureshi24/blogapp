package com.springboot.backend.advice;

import com.springboot.backend.exception.EmptyInputException;
import com.springboot.backend.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {


        @ExceptionHandler(EmptyInputException.class)
        public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException) {

            return new ResponseEntity<String>("Input Fields are Empty,Please Look into it", HttpStatus.BAD_REQUEST);
        }


        @ExceptionHandler(NoSuchElementException.class)
        public ResponseEntity<String> handleNoSuchElementException(){

            return new ResponseEntity<String>("No value Present in db,please change your db request",HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException){

            return new ResponseEntity<String>(resourceNotFoundException.getFieldName(),HttpStatus.NOT_FOUND);

    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

            return new ResponseEntity<Object>("Please change http Media type",HttpStatus.NOT_FOUND);
    }
}
