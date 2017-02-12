package com.example.jacktownsend.marchon.organizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jacktownsend.marchon.R;

import butterknife.BindView;

public class CreateNotificationActivity extends AppCompatActivity {

    @BindView(R.id.cnTitleText)
    EditText title;
    @BindView(R.id.cnDescriptionText)
    EditText description;
    @BindView(R.id.createButton)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("notification_title", title.getText().toString());
                intent.putExtra("notification_description", description.getText().toString());
                CreateNotificationActivity.this.setResult(RESULT_OK, intent);
                CreateNotificationActivity.this.finish();
            }
        });

    }
}
