// This Defines an Adapter for the lectures timetable
package com.seproject.classmate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .LectObjectHolder> {

    private ArrayList<LectObject> mDataset;
    LectObjectHolder dataObjectHolder;
    private static final String TAG = "MyActivity";

    public static class LectObjectHolder extends RecyclerView.ViewHolder{
        TextView day;
        TextView time;
        TextView venue;

       // ImageView personPhoto;
       // ImageButton img_Button1;
      //  ImageButton img_Button2;
      //  ImageButton img_Button3;

        private final Context context;
        public LectObjectHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            day = (TextView) itemView.findViewById(R.id.textView);
            time = (TextView) itemView.findViewById(R.id.textView2);
            venue = (TextView) itemView.findViewById(R.id.textView3);
          //  personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
         //  img_Button1 = (ImageButton) itemView.findViewById(R.id.call_button);
           // img_Button2 = (ImageButton) itemView.findViewById(R.id.g_plus_button);
         //   img_Button3 = (ImageButton) itemView.findViewById(R.id.fb_button);
        }
    }

    public MyRecyclerViewAdapter(ArrayList<LectObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public LectObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        dataObjectHolder = new LectObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(LectObjectHolder holder, final int position) {
        holder.day.setText(mDataset.get(position).getlecDay());
        holder.time.setText(mDataset.get(position).getlecTime());
        holder.venue.setText(mDataset.get(position).getLecVenue());
      //  holder.personPhoto.setImageResource(mDataset.get(position).get());
      //  holder.img_Button1.setImageResource(mDataset.get(position).getImgButton1());//Also trial code for the imagebuttons
      //  holder.img_Button2.setImageResource(mDataset.get(position).getImgButton2());//have to remove if imgbuttons don't work

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}