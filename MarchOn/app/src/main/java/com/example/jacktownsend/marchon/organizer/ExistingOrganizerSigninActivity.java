package com.example.jacktownsend.marchon.organizer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.jacktownsend.marchon.R;
import com.example.jacktownsend.marchon.api.ApiErrorException;
import com.example.jacktownsend.marchon.api.ApiInterface;

import butterknife.BindView;

import static android.Manifest.permission.READ_CONTACTS;


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
    }

    public void OnSignin(View view) {
        String serverIP = getString(R.string.api_server);

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        int id;
        try {
            ApiInterface api = new ApiInterface(serverIP);
            id = api.authenticateOrganizer(username, password);
        } catch (ApiErrorException ex) {
            Toast.makeText(this, "Log-in Failed", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, OrganizerNotificationList.class);
        intent.putExtra("organizer_id", id);
        startActivity(intent);

    }


}

