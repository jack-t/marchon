package com.example.jacktownsend.marchon.api;

import com.example.jacktownsend.marchon.Notification;

import java.util.ArrayList;
import java.util.List;

public class ApiInterface {

    private String api;

    public ApiInterface(String api) {
        this.api = api;
    }

    public int authenticateOrganizer(String username, String password) throws ApiErrorException {

        return 1;

    }

    public void signup(String username, String title, String phone, String email, String date, String details) throws ApiErrorException {

    }

    public List<Notification> notificationsForMarch(int marchId) {
        return new ArrayList<>();
    }

}
