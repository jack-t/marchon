package com.example.jacktownsend.marchon.participant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jacktownsend.marchon.R;
import com.example.jacktownsend.marchon.api.Notification;

import java.util.List;


public class NotificationsListAdapter extends BaseAdapter {

    private List<Notification> notifications;
    private Context context;

    public NotificationsListAdapter(Context context, List<Notification> notif) {
        this.notifications = notif;
        this.context = context;
    }

    public void setList(List<Notification> n) {
        notifications.clear();
        notifications.addAll(n);
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
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.notifications_list_row, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.notificationTitle);
        TextView desc = (TextView) convertView.findViewById(R.id.notificationDescription);

        Notification n = (Notification) getItem(position);
        title.setText(n.title);
        desc.setText(n.description);

        return convertView;
    }
}
