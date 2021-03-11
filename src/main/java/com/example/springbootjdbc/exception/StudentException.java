package com.example.springbootjdbc.exception;

public class StudentException extends RuntimeException {

    private String customMessage;
    private int code;

    public StudentException(String customMessage) {
        super(customMessage);
        this.customMessage = customMessage;
    }

    public StudentException(String customMessage, int code) {
        super(customMessage);
        this.customMessage = customMessage;
        this.code = code;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public int getCode() {
        return code;
    }
}
