package com.example.jacktownsend.marchon;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.jacktownsend.marchon.api.ApiInterface;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationListFragment extends Fragment {

    public NotificationListFragment() {
    }

    private NotificationListAdapter adapter;

    @BindView(R.id.notificationsList)
    ListView view;

    @BindString(R.string.api_server)
    String serverIP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_list, container, false);
        ButterKnife.bind(this, view); // bind w/r/t view

        this.adapter = new NotificationListAdapter(this);

        updateNotificationList();

        return view;
    }

    public void updateNotificationList() {
        ApiInterface api = new ApiInterface(serverIP);
        int marchId = getArguments().getInt("march_id");
        final List<Notification> list = api.notificationsForMarch(marchId);
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setList(list);
            }
        });
    }

    public void addNotification(Notification notification) {
        adapter.addNotification(notification);
    }
}
