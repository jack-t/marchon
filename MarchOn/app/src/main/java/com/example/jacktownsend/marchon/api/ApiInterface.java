package com.example.jacktownsend.marchon.api;

import java.util.ArrayList;
import java.util.List;

public class ApiInterface {

    private String api;

    public ApiInterface(String api) {
        this.api = api;
    }

    public Organizer authenticateOrganizer(String username, String password) throws ApiErrorException {

        return new Organizer(1,1);

    }

    public void signup(String username, String title, String phone, String email, String date, String details) throws ApiErrorException {

    }

    public List<Notification> notificationsForMarch(int marchId) {
        return new ArrayList<>();
    }

    public String getMarchName(int marchId) {
        return "NO MARCH NAME";
    }

    public void createNotification(Notification notification)throws ApiErrorException {



    }
}