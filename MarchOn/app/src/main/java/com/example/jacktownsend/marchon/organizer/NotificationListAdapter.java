package com.example.jacktownsend.marchon.organizer;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.jacktownsend.marchon.R;
import com.example.jacktownsend.marchon.api.Notification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NotificationListAdapter extends BaseAdapter implements ListAdapter {

    private AppCompatActivity context;

    public NotificationListAdapter(AppCompatActivity activity) {
        super();
        this.context = activity;
        notifications = new ArrayList<>();
    }

    private List<Notification> notifications;

    public void setList(Collection<Notification> notifs) {
        notifications.clear();
        notifications.addAll(notifs);
        this.notifyDataSetChanged();
    }

    public void addNotification(Notification notif) {
        notifications.add(notif);
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return notifications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.notification_row, null);
        }

        TextView titleText = (TextView) convertView.findViewById(R.id.rowTitleText);
        TextView descriptionText = (TextView) convertView.findViewById(R.id.rowDescriptionText);
        TextView dateText = (TextView) convertView.findViewById(R.id.rowDateText);

        titleText.setText(notifications.get(position).title);
        descriptionText.setText(notifications.get(position).description);
        dateText.setText("ND (Yet)");


        return convertView;

    }
}
