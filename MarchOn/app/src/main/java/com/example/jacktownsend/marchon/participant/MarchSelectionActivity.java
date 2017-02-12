package com.example.jacktownsend.marchon.participant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jacktownsend.marchon.PreferencesManager;
import com.example.jacktownsend.marchon.R;
import com.example.jacktownsend.marchon.api.March;
import com.example.jacktownsend.marchon.organizer.MarchListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarchSelectionActivity extends AppCompatActivity {

    @BindView(R.id.marches)
    ListView marchList;
    @BindView(android.R.id.empty)
    TextView empty;
    MarchListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_march_selection);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new MarchListAdapter(this);
        adapter.populate();
        marchList.setAdapter(adapter);
        marchList.setEmptyView(empty);

        marchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                March march = (March) adapter.getItem(position);
                PreferencesManager.get().setMarchId(march.id);
//                Intent intent = new Intent(MarchSelectionActivity.class, MarchViewScreen.class);
//                intent.putExtra("march", march);
//                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        adapter.populate();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) super.onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}
