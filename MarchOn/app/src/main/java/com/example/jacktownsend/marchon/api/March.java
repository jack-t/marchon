package com.example.jacktownsend.marchon.api;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class March implements Serializable {

    public final String title, description;
    public final int id;
    public final double llat, llong;
    public final ArrayList<Event> events;

    public March(int id, String title, String description, ArrayList<Event> list, double llat, double llong) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.llat = llat;
        this.llong = llong;
        this.events = list;
    }
}
