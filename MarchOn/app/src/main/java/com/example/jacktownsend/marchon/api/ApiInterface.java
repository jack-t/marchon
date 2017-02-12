package com.example.jacktownsend.marchon.api;

import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApiInterface {

    private String api;

    public ApiInterface(String api) {
        this.api = api;
    }

    public Organizer authenticateOrganizer(String username, String password) throws ApiErrorException {
        final Organizer or = new Organizer();
        RequestFuture<JSONObject> future = RequestFuture.newFuture();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, api + "/login/" + username, null, future, future);
        queue.add(jsObjRequest);

        try {
            JSONObject response = future.get();
            or.organizer = Integer.parseInt(response.get("organizer_id").toString());
            or.march = Integer.parseInt(response.get("march_id").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return or;
    }


    public void signup(String username, String title, String phone, String email, String date, String details) throws ApiErrorException {

    }

    public List<Notification> notificationsForMarch(int marchId) {

        final ArrayList<Notification> ret = new ArrayList<>();
        RequestFuture<JSONArray> future = RequestFuture.newFuture();

        JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.POST, api + "/notifications/" + marchId, null, future, future);
        queue.add(jsObjRequest);

        try {
            JSONArray response = future.get();
            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                Notification notif = new Notification(obj.getString("title"), obj.getString("description"));
                ret.add(notif);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
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
    }

    public List<March> getMarchesList() throws ApiErrorException {
        final ArrayList<March> ret = new ArrayList<>();
        RequestFuture<JSONArray> future = RequestFuture.newFuture();

        JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET, api + "/marches/", null, future, future);
        //queue.add(jsObjRequest);
        future.setRequest(jsObjRequest);
        try {
            JSONArray response = future.get();

            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                March march = new March(obj.getString("title"), obj.getString("description"), obj.getString("date"), obj.getString("location"), obj.getInt("id"), obj.getDouble("llat"), obj.getDouble("llong"));
                ret.add(march);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ArrayList<Event> getEventsList(int marchId) throws ApiErrorException {
        final ArrayList<Event> ret = new ArrayList<>();
        RequestFuture<JSONArray> future = RequestFuture.newFuture();

        JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET, api + "/events/" + marchId, null, future, future);
        queue.add(jsObjRequest);
        try {
            JSONArray response = future.get();
            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                Event event = new Event(obj.getString("title"), obj.getString("description"), obj.getDouble("llat"), obj.getDouble("llong"));
                ret.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public March getMarch(int march_id) throws ApiErrorException {
        return getMarchesList().get(0);
    }

    public ArrayList<Route> getRoutes(int march_id) {
        final ArrayList<Route> ret = new ArrayList<>();

        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET, api + "/routes/" + march_id, null, future, future);
        queue.add(jsObjRequest);
        try {
            JSONArray response = future.get();
            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                Route route = new Route(obj.getDouble("llat_begin"), obj.getDouble("llong_begin"), obj.getDouble("llat_end"), obj.getDouble("llong_end"));
                ret.add(route);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<Notification> getNotifications(int march_id) throws ApiErrorException {
        final ArrayList<Notification> ret = new ArrayList<>();

        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET, api + "/routes/" + march_id, null, future, future);
        //future.setRequest(jsObjRequest);
        queue.add(jsObjRequest);
        try {
            JSONArray response = future.get();
            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                Notification notif = new Notification(obj.getString("title"), obj.getString("description"));
                ret.add(notif);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    private static RequestQueue queue;

    public static void init(Context context) {
        queue = Volley.newRequestQueue(context);
    }

}











