package com.example.jacktownsend.marchon;

import android.content.SharedPreferences;
import android.util.Log;

public class PreferencesManager {

    private SharedPreferences prefs;

    private PreferencesManager(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    private static PreferencesManager man;

    public static PreferencesManager get() {
        return man;
    }

    public static void set(SharedPreferences p) {
        man = new PreferencesManager(p);
    }


    public boolean isOrganizer() {
        return prefs.contains("organizer");
    }

    public int getOrganizerId() {
        return prefs.getInt("organizer", -1);
    }

    public void setMarchId(int nmarch) {
        prefs.edit().putInt("march", nmarch).commit();
    }

    public void unsetMarchId() {
        prefs.edit().remove("march");
    }

    public void unsetOrganizerId() {
        prefs.edit().remove("organizer");

    }

    public void setOrganizerId(int norganizer) {
        prefs.edit().putInt("march", norganizer).commit();
    }

    public int getMarchId() {
        return prefs.getInt("march", -1);
    }

    public void toggleSNM() {
        this.prefs.edit().putBoolean("snm", prefs.contains("snm") ? !prefs.getBoolean("snm", true) : true).commit();
        Log.d("SNM", "Toggled SNM; now: " + prefs.getBoolean("snm", true));
    }

    public boolean isSNM() {
        return prefs.getBoolean("snm", false);
    }
}
