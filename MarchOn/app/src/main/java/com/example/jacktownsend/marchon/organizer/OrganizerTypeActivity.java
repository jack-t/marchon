package com.example.jacktownsend.marchon.organizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jacktownsend.marchon.R;

public class OrganizerTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_type);
    }

    public void onClick(View view) {
        Class<?> clazz = null;
        switch (view.getTag().toString()) {
            case "existing":
                clazz = ExistingOrganizerSigninActivity.class;
                break;
            case "new":
                clazz = OrganizerSignupActivity.class;
                break;
        }
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
