package com.example.jacktownsend.marchon.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiInterface {

    private String api;

    public ApiInterface(String api) {
        this.api = api;
    }

    public static March march = new March("Women's March", "The Women's March on Washington, Jan 21 2017.", "Jan. 21, 2017", "Washington, D.C.", 1, 38.887635, -77.015280);
    public static Event ev1 = new Event("Rally", "A program featuring nationally recognized advocates, artists, entertainers, entrepreneurs, thought leaders, and others will be announced in the coming days.", "9:30 AM", 38.887635, -77.015280);
    public static Event ev2 = new Event("March Ends", "The Ellipse is where the march ends.", "3:30 PM", 38.893738, -77.036406);

    public static Route route1 = new Route(38.887635, -77.015280, 38.887626, -77.031858);
    public static Route route2 = new Route(38.887626, -77.031858, 38.892093, -77.031934);
    public static Route route3 = new Route(38.892093, -77.031934, 38.892089, -77.036546);

    public static Notification notification = new Notification("Plans Changed!", "Use Constitution, not Independence.", "2:30 PM");

    public Organizer authenticateOrganizer(String username, String password) throws ApiErrorException {
        return new Organizer(1, 1);
    }


    public void signup(String username, String title, String phone, String email, String date, String details) throws ApiErrorException {

    }

    public List<Notification> notificationsForMarch(int marchId) {

        return new ArrayList<Notification>() {{
            add(notification);
        }};
    }

    public String getMarchName(int marchId) throws ApiErrorException {
        return getMarch(marchId).title;
    }

    public void createNotification(int march_id, Notification notification) throws ApiErrorException {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, api + "/login/" + march_id + "/" + notification.title + "/" + notification.description, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        });
        queue.add(request);
        //TODO
    }

    public List<March> getMarchesList() throws ApiErrorException {
        return new ArrayList<March>() {{
            add(march);
        }};
    }

    public ArrayList<Event> getEventsList(int marchId) throws ApiErrorException {
        return new ArrayList<Event>() {{
            add(ev1);
            add(ev2);
        }};
    }

    public March getMarch(int march_id) throws ApiErrorException {
        return getMarchesList().get(0);
    }

    public ArrayList<Route> getRoutes(int march_id) {
        return new ArrayList<Route>() {{
            add(route1);
            add(route2);
            add(route3);

        }};
    }

    public List<Notification> getNotifications(int march_id) throws ApiErrorException {
        return new ArrayList<Notification>() {{
            add(notification);
        }};
    }

    private static RequestQueue queue;

    public static void init(Context context) {
        queue = Volley.newRequestQueue(context);
    }
}











