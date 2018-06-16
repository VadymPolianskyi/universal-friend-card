package com.two.ufcard.protocol.api;


import org.springframework.http.HttpStatus;

public abstract class Response {
    private int code = HttpStatus.OK.value();
    private String message = HttpStatus.OK.getReasonPhrase();
}
