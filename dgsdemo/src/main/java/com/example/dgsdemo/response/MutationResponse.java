package com.example.dgsdemo.response;

public class MutationResponse {
    private final Boolean success;
    private final String message;
    private final int statusCode;

    public MutationResponse(Boolean success, String message, int statusCode) {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
