package com.example.jacktownsend.marchon.participant;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jacktownsend.marchon.PreferencesManager;
import com.example.jacktownsend.marchon.api.ApiErrorException;
import com.example.jacktownsend.marchon.api.ApiInterface;
import com.example.jacktownsend.marchon.api.Event;
import com.example.jacktownsend.marchon.api.March;
import com.example.jacktownsend.marchon.api.Route;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.jacktownsend.marchon.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    SupportMapFragment map;

    March march;
    List<Route> routes;
    List<Event> events;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_maps_dummy, container, false);
        ButterKnife.bind(this, layout);

        map = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map));


        march = (March) getArguments().getSerializable("march");
        routes = (List<Route>) getArguments().getSerializable("routes");
        events = (List<Event>) getArguments().getSerializable("events");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if (!PreferencesManager.get().isSNM()) {
            map.getMapAsync(this);
            map.onResume();
        } else {
            this.getChildFragmentManager().beginTransaction().remove(map).commit();
            Toast.makeText(inflater.getContext(), "Map view has been disabled for light data mode", Toast.LENGTH_LONG).show();
        }
        return layout;
    }

    public static MapsFragment newInstance(Context context, int march_id) {
        MapsFragment frag = new MapsFragment();
        try {
            ApiInterface api = new ApiInterface(context.getString(R.string.api_server));
            March march = api.getMarch(march_id);
            ArrayList<Route> routes = api.getRoutes(march_id);
            ArrayList<Event> events = api.getEventsList(march_id);
            Bundle bundle = new Bundle();
            bundle.putSerializable("march", march);
            bundle.putSerializable("routes", routes);
            bundle.putSerializable("events", events);
            frag.setArguments(bundle);
        } catch (ApiErrorException ex) {
            ex.toast(context);
            ex.printStackTrace();
        }
        return frag;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        for (Event e : events) {
            LatLng marchLoc = new LatLng(e.llat, e.llong);
            mMap.addMarker(new MarkerOptions().position(marchLoc).title(e.title));
        }

        LatLng center = new LatLng(march.llat, march.llong);
        mMap.addMarker(new MarkerOptions().position(center).title(march.title));

        for (Route route : routes) {
            mMap.addPolyline(route.makePoly(this.getContext()));
        }


        mMap.moveCamera(CameraUpdateFactory.newLatLng(center));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15.5f));
    }
}
