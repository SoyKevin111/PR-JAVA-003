package com.example.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandlerGlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        Map<String, Object> response = new HashMap<>();

        if (ex instanceof MethodArgumentNotValidException inputEx) {
            List<Object> fields = inputEx.getBindingResult().getFieldErrors().stream()
                    .map(e -> {
                        Map<String, String> field = new HashMap<>();
                        field.put("campo", e.getField());
                        field.put("mensaje", e.getDefaultMessage());
                        return field;
                    })
                    .collect(Collectors.toList());
            response.put("errors", fields);
            response.put("status", HttpStatus.BAD_REQUEST);
        } else {
            response.put("mensaje", ex.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("timestamp", LocalDate.now());
        return ResponseEntity.badRequest().body(response);
    }
}
