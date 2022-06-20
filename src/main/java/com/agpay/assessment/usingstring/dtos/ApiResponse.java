package com.agpay.assessment.usingstring.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


public record ApiResponse(
       Object data,
       HttpStatus status,
       String message,
       LocalDateTime timestamps
) {
    public ApiResponse(Object data, HttpStatus status, String message){
        this(data, status,message, LocalDateTime.now(ZoneOffset.of("+1")));
        }

}
