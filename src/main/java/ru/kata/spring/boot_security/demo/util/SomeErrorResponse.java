package ru.kata.spring.boot_security.demo.util;

public class SomeErrorResponse {
    private String message;

    public SomeErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
