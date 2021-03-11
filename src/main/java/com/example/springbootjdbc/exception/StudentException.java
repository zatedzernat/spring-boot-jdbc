package com.example.springbootjdbc.exception;

public class StudentException extends RuntimeException {

    private String message;
    private int code;

    public StudentException(String message) {
        super(message);
        this.message = message;
    }

    public StudentException(String message, int code) {
        super(message);
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
