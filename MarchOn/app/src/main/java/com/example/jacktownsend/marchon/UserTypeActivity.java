package com.example.jacktownsend.marchon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jacktownsend.marchon.api.ApiInterface;
import com.example.jacktownsend.marchon.organizer.OrganizerTypeActivity;
import com.example.jacktownsend.marchon.participant.MarchSelectionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserTypeActivity extends AppCompatActivity {

    @BindView(R.id.participantButton)
    Button participantButton;
    @BindView(R.id.organizerButton)
    Button organizerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
        ButterKnife.bind(this);

        PreferencesManager.set(getPreferences(MODE_PRIVATE));

        if (PreferencesManager.get().isOrganizer()) {
            proceed(organizerButton);
        }

        ApiInterface.init(this);

    }

    public void proceed(View view) {
        Class<?> clazz = null;
        switch (view.getTag().toString()) {
            case "organizer":
                clazz = OrganizerTypeActivity.class;
                // organizer type choice
                break;
            case "participant":
                // participant march selection
                clazz = MarchSelectionActivity.class;
                break;
        }
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
