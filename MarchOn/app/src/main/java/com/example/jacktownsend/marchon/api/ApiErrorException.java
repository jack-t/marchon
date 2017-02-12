package com.example.jacktownsend.marchon.api;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

    public void toast(Context act) {
        Toast.makeText(act, "ApiError: " + response + " " + getMessage(), Toast.LENGTH_SHORT).show();
    }
}
