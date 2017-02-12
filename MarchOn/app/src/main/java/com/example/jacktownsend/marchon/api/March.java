package com.example.jacktownsend.marchon.api;


import java.io.Serializable;

public class March implements Serializable{

    public final String title, description;
    public final int id;


    public March(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
