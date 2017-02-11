package com.example.jacktownsend.marchon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jacktownsend.marchon.organizer.OrganizerTypeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserTypeActivity extends AppCompatActivity {

    @BindView(R.id.participantButton)
    Button participantButton;
    @BindView(R.id.participantButton)
    Button organizerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
        ButterKnife.bind(this);
    }

    public void onClick(View view) {
        Class<?> clazz = null;
        switch (view.getTag().toString()) {
            case "organizer":
                clazz = OrganizerTypeActivity.class;
                // organizer type choice
                break;
            case "participant":
                // participant march selection
                break;
        }
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
