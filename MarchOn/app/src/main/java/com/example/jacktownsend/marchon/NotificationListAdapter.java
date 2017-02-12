package com.example.jacktownsend.marchon;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collection;
import java.util.List;

public class NotificationListAdapter extends BaseAdapter implements ListAdapter {

    private Fragment context;

    public NotificationListAdapter(Fragment activity) {
        super();
        this.context = activity;
    }

    private List<Notification> notifications;

    public void setList(Collection<Notification> notifs) {
        notifications.clear();
        notifications.addAll(notifs);
    }

    public void addNotification(Notification notif) {
        notifications.add(notif);
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
            convertView = context.getActivity().getLayoutInflater().inflate(R.layout.notification_row, parent);
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
