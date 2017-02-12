package com.example.jacktownsend.marchon.organizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jacktownsend.marchon.api.Organizer;
import com.example.jacktownsend.marchon.PreferencesManager;
import com.example.jacktownsend.marchon.R;
import com.example.jacktownsend.marchon.api.ApiErrorException;
import com.example.jacktownsend.marchon.api.ApiInterface;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ExistingOrganizerSigninActivity extends AppCompatActivity {

    @BindView(R.id.signinButton)
    Button signin;
    @BindView(R.id.emailText)
    EditText usernameText;
    @BindView(R.id.passwordText)
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_organizer_signin);
        ButterKnife.bind(this);
    }

    @BindString(R.string.api_server)
    String serverIP;

    public void onSignin(View view) {

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        int organizer_id;
        int march_id;
        String name = null;
        try {
            ApiInterface api = new ApiInterface(serverIP);
            Organizer organizer = api.authenticateOrganizer(username, password);
            organizer_id = organizer.organizer;
            march_id = organizer.march;
            name = api.getMarchName(march_id);
        } catch (ApiErrorException ex) {
            Toast.makeText(this, "Log-in Failed", Toast.LENGTH_LONG).show();
            return;
        }

        PreferencesManager.get().setOrganizerId(organizer_id);
        PreferencesManager.get().setMarchId(march_id);

        Intent intent = new Intent(this, OrganizerNotificationList.class);
        intent.putExtra("organizer_id", organizer_id);
        intent.putExtra("march_id", march_id);
        intent.putExtra("march_name", name);
        startActivity(intent);
    }


}

