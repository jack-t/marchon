package com.example.jacktownsend.marchon.participant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jacktownsend.marchon.NotificationPollerService;
import com.example.jacktownsend.marchon.R;
import com.example.jacktownsend.marchon.api.ApiErrorException;
import com.example.jacktownsend.marchon.api.ApiInterface;
import com.example.jacktownsend.marchon.api.Notification;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsListActivity extends AppCompatActivity {

    @BindView(R.id.notificationsList)
    ListView notifs;

    NotificationsListAdapter adapter;

    int march_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_list);
        ButterKnife.bind(this);

        march_id = getIntent().getIntExtra("march_id", -1);

        adapter = new NotificationsListAdapter(this, new ArrayList<Notification>());
        notifs.setAdapter(adapter);
        populate();
    }




    @Override
    public void onResume() {
        populate();
        super.onResume();
    }

    public void populate() {
        adapter.setList(NotificationPollerService.currentList);
    }
}
