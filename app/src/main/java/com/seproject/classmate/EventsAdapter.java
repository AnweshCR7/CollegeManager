package com.seproject.classmate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView
        .Adapter<EventsAdapter
        .DataObjectHolder> {
    private ArrayList<EventObject> mEventset;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
    {
        TextView label;
        ImageView eventImg;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.eventName);
            eventImg = (ImageView) itemView.findViewById(R.id.eventImage);
        }

    }

    public EventsAdapter(ArrayList<EventObject> myEventset) {
        mEventset = myEventset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_card, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder,final int position) {
        holder.label.setText(mEventset.get(position).getEventName());
        holder.eventImg.setImageResource(mEventset.get(position).getEventImage());
    }


    @Override
    public int getItemCount() {
        return mEventset.size();
    }

}