package com.example.jacktownsend.marchon.participant;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jacktownsend.marchon.R;
import com.example.jacktownsend.marchon.api.ApiErrorException;
import com.example.jacktownsend.marchon.api.ApiInterface;
import com.example.jacktownsend.marchon.api.Event;

import java.util.ArrayList;
import java.util.List;

public class EventListFragment extends Fragment {


    public EventListFragment() {
    }

    public static EventListFragment newInstance(int march_id) {
        EventListFragment fragment = new EventListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("march_id", march_id);
        fragment.setArguments(bundle);
        return fragment;
    }


    int march_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        View recycler = view.findViewById(R.id.list);

        march_id = getArguments().getInt("march_id", -1);



        List<Event> initials = new ArrayList<>();
        try {
            ApiInterface api = new ApiInterface(getString(R.string.api_server));
            initials.addAll(api.getEventsList(march_id));
        } catch (ApiErrorException ex) {
            ex.printStackTrace();
        }

        // Set the adapter
        if (recycler instanceof RecyclerView) {
            Context context = recycler.getContext();
            RecyclerView recyclerView = (RecyclerView) recycler;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new EventListRecyclerAdapter(this.getActivity(), initials));
        }
        return view;
    }

}
