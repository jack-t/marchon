package com.example.jacktownsend.marchon.organizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jacktownsend.marchon.api.Notification;
import com.example.jacktownsend.marchon.R;
import com.example.jacktownsend.marchon.api.ApiErrorException;
import com.example.jacktownsend.marchon.api.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class OrganizerNotificationList extends AppCompatActivity {

    private int organizerId;
    private int marchId;
    private String marchName;
    NotificationListAdapter adapter;
    @BindView(R.id.notifListFrame)
    ListView notifFrame;
    @BindView(R.id.marchTitleText)
    TextView titleText;

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

        adapter = new NotificationListAdapter(this);
        notifFrame.setAdapter(adapter);

        organizerId = getIntent().getIntExtra("organizer_id", -1);
        marchId = getIntent().getIntExtra("march_id", -1);
        marchName = getIntent().getStringExtra("march_name");
        this.getSupportActionBar().setTitle(marchName == null ? "No March Name" : marchName);
        titleText.setText(marchName == null ? "No March Name" : marchName);
        if (marchId == -1 || organizerId == -1) {
            Log.d("NOTIFICATIONS", "MUST PASS ORGANIZER AND MARCH IDS");
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, OrganizerTypeActivity.class);
        intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            String title = data.getStringExtra("notification_title");
            String description = data.getStringExtra("notification_description");
            final Notification notification = new Notification(title, description, "date");

            boolean good = true;
            try {
                ApiInterface api = new ApiInterface(getString(R.string.api_server));
                api.createNotification(marchId, notification);
            } catch (ApiErrorException ex) {
                Toast.makeText(this, "Unable to create notification", Toast.LENGTH_LONG).show();
                good = false;
            }
            if (good) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addNotification(notification);

                    }
                });
            }

        } else {
            Toast.makeText(this, "Notification creation cancelled", Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
