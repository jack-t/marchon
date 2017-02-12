package com.example.jacktownsend.marchon.api;

public class ApiErrorException extends Exception {

    private final int response;

    public ApiErrorException(int response, String message) {
        super(message);
        this.response = response;
    }

    public int getResponse() {
        return response;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
