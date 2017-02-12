package com.example.jacktownsend.marchon.api;

import android.content.Context;

import com.example.jacktownsend.marchon.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.Serializable;

public class Route implements Serializable {

    public final double llat_begin, llong_begin;
    public final double llat_end, llong_end;

    public Route(double llat_begin, double llong_begin, double llat_end, double llong_end) {
        this.llat_begin = llat_begin;
        this.llong_begin = llong_begin;
        this.llat_end = llat_end;
        this.llong_end = llong_end;
    }

    public PolylineOptions makePoly(Context context) {
        return new PolylineOptions().add(new LatLng(llat_begin, llong_begin), new LatLng(llat_end, llong_end)).color(context.getResources().getColor(R.color.colorAccent)).width(20f).zIndex(100);
    }

}
