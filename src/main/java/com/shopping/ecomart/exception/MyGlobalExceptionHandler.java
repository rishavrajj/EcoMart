package com.shopping.ecomart.exception;

import com.shopping.ecomart.dtos.ResultResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResultResponseDTO myResourceNotFoundException(ResourceNotFoundException e) {
        return ResultResponseDTO.builder().message(HttpStatus.NOT_FOUND).description(e.getMessage()).build();
    }

    @ExceptionHandler(APIException.class)
    public ResultResponseDTO myAPIException(APIException e) {
        return ResultResponseDTO.builder().message(HttpStatus.BAD_REQUEST).description(e.getMessage()).build();
    }


    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResultResponseDTO myResourceAlreadyExistException(ResourceAlreadyExistException e) {
        return ResultResponseDTO.builder().message(HttpStatus.BAD_REQUEST).description(e.getMessage()).build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> res = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError) err).getField();
            String message = err.getDefaultMessage();

            res.put(fieldName, message);
        });

        return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> myAuthenticationException(AuthenticationException e) {

        String res = e.getMessage();

        return new ResponseEntity<String>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResultResponseDTO myMissingPathVariableException(MissingPathVariableException e) {
        return ResultResponseDTO.builder().message(HttpStatus.BAD_REQUEST).description(e.getMessage()).build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResultResponseDTO myDataIntegrityException(DataIntegrityViolationException e) {
        return ResultResponseDTO.builder().message(HttpStatus.BAD_REQUEST).description(e.getMessage()).build();
    }
}
