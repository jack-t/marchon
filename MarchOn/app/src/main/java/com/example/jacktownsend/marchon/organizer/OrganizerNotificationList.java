package com.example.jacktownsend.marchon.organizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.jacktownsend.marchon.Notification;
import com.example.jacktownsend.marchon.NotificationListFragment;
import com.example.jacktownsend.marchon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrganizerNotificationList extends AppCompatActivity {

    private int organizerId;
    private int marchId;
    @BindView(R.id.notifListFrame)
    FrameLayout notifFrame;
    NotificationListFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_notification_list);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrganizerNotificationList.this, CreateNotificationActivity.class);
                OrganizerNotificationList.this.startActivityForResult(intent, 100); // 100 is arbitrary
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        organizerId = getIntent().getIntExtra("organizer_id", -1);
        marchId = getIntent().getIntExtra("march_id", -1);

        if (marchId == -1 || organizerId == -1) {
            Log.d("NOTIFICATIONS", "MUST PASS ORGANIZER AND MARCH IDS");
        }

        frag = new NotificationListFragment();
        frag.getArguments().putInt("march_id", marchId);
        getSupportFragmentManager().beginTransaction().add(notifFrame.getId(), frag).commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            String title = data.getStringExtra("notification_title");
            String description = data.getStringExtra("notification_description");

            frag.addNotification(new Notification(title, description));
        } else {
            Toast.makeText(this, "Notification creation cancelled", Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
