package com.example.jacktownsend.marchon.participant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jacktownsend.marchon.R;

import butterknife.BindView;

public class NotificationsListActivity extends AppCompatActivity {

    @BindView(R.id.notificationsList)
    ListView notifs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_list);



    }
}
