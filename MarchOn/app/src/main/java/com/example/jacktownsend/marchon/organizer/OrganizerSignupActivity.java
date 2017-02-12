package com.example.jacktownsend.marchon.organizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jacktownsend.marchon.R;
import com.example.jacktownsend.marchon.api.ApiErrorException;
import com.example.jacktownsend.marchon.api.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrganizerSignupActivity extends AppCompatActivity {

    @BindView(R.id.marchTitleText)
    EditText titleText;
    @BindView(R.id.nameText)
    EditText nameText;
    @BindView(R.id.dateText)
    EditText dateText;
    @BindView(R.id.detailsText)
    EditText detailsText;
    @BindView(R.id.phoneNumberText)
    EditText phoneText;
    @BindView(R.id.emailText)
    EditText emailText;
    @BindView(R.id.signupButton)
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_signup);
        ButterKnife.bind(this);

    }

    public void onSignup(View view) {
        ApiInterface api = new ApiInterface(getString(R.string.api_server));
        String title = titleText.getText().toString();
        String name = nameText.getText().toString();
        String date = dateText.getText().toString();
        String details = detailsText.getText().toString();
        String phone = phoneText.getText().toString();
        String email = emailText.getText().toString();
        try {
            api.signup(name, title, phone, email, date, details);
            Toast.makeText(this, "Sign-up sent. We'll be in touch.", Toast.LENGTH_LONG).show();
            finish();
        } catch (ApiErrorException ex) {
            Toast.makeText(this, "Error signing up", Toast.LENGTH_LONG).show();
            return;
        }
    }
}
