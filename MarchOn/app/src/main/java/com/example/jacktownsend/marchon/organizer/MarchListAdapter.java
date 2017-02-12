package com.example.jacktownsend.marchon.organizer;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.jacktownsend.marchon.PreferencesManager;
import com.example.jacktownsend.marchon.R;
import com.example.jacktownsend.marchon.api.March;

import java.util.List;


public class MarchListAdapter extends BaseAdapter implements ListAdapter {

    private List<March> marches;
    private AppCompatActivity context;

    public MarchListAdapter(AppCompatActivity app) {
        this.context = app;
    }



    @Override
    public int getCount() {
        return marches.size();
    }

    @Override
    public Object getItem(int position) {
        return marches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.upcoming_march_row, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.msTitle);
        TextView description = (TextView) convertView.findViewById(R.id.msDescription);

        title.setText(marches.get(position).title);
        description.setText(marches.get(position).description);

        return convertView;
    }
}
