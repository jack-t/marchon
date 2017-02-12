package com.example.jacktownsend.marchon.api;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApiInterface {

    private String api;

    public ApiInterface(String api) {
        this.api = api;
    }

    public Organizer authenticateOrganizer(String username, String password) throws ApiErrorException {
        return new Organizer(1, 1);
    }


    public void signup(String username, String title, String phone, String email, String date, String details) throws ApiErrorException {

    }

    public List<Notification> notificationsForMarch(int marchId) {
        return new ArrayList<>();
    }

    public String getMarchName(int marchId) {
        return "NO MARCH NAME";
    }

    public void createNotification(Notification notification) throws ApiErrorException {

    }

    public List<March> getMarchesList() throws ApiErrorException {
        return new ArrayList<March>() {{
            add(new March(1, "Title", "Desc", new ArrayList<Event>(getEventsList(1)), 38.908292, -77.033489));
        }};
    }

    public List<Event> getEventsList(int marchId) throws ApiErrorException {
        return new ArrayList<Event>() {{
            add(new Event("Title", "Desc", 38.908292, -77.021489));
            add(new Event("Title2", "Description", 38.915292, -77.021489));
        }};
    }

    public March getMarch(int march_id) throws ApiErrorException {
        return getMarchesList().get(0);
    }

    public ArrayList<Route> getRoutes(int march_id) {
        return new ArrayList<Route>() {{
            add(new Route(38.908292, -77.021489, 38.915292, -77.021489));
        }};
    }
}











