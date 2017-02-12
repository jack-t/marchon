package com.example.jacktownsend.marchon.api;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class March implements Serializable {

    public final String title, description, date, location;
    public final int id;
    public final double llat, llong;
    public final ArrayList<Event> events;

    public March(String title, String description, String date, String location, int id, double llat, double llong, ArrayList<Event> events) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.id = id;
        this.llat = llat;
        this.llong = llong;
        this.events = events;
    }
}
