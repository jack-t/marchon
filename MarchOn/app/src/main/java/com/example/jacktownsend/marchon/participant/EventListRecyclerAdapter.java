package com.example.jacktownsend.marchon.participant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jacktownsend.marchon.R;
import com.example.jacktownsend.marchon.api.Event;

import java.util.List;

public class EventListRecyclerAdapter extends RecyclerView.Adapter<EventListRecyclerAdapter.CustomViewHolder> {
    private List<Event> items;
    private Context mContext;

    public EventListRecyclerAdapter(Context context, List<Event> items) {
        this.items = items;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_event, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Event item = items.get(i);

        customViewHolder.titleText.setText(item.title);
        customViewHolder.timeText.setText(item.date);
    }

    @Override
    public int getItemCount() {
        return (null != items ? items.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView titleText;
        protected TextView timeText;

        public CustomViewHolder(View view) {
            super(view);
            this.titleText = (TextView) view.findViewById(R.id.eventTitleText);
            this.timeText = (TextView) view.findViewById(R.id.eventTimeText);
        }
    }
}