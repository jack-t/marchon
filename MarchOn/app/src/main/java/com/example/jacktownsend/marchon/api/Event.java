package com.example.jacktownsend.marchon.api;


import java.io.Serializable;

public class Event implements Serializable {

    public final String title, description, date;
    public final double llat, llong;

    public Event(String title, String description, String date, double llat, double llong) {
        this.title = title;
        this.description = description;
        this.llat = llat;
        this.llong = llong;
        this.date = date;
    }
}
