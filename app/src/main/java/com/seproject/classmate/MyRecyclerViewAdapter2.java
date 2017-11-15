// This Defines an Adapter for the NOTIFICATIONS
package com.seproject.classmate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter2 extends RecyclerView
        .Adapter<MyRecyclerViewAdapter2
        .NotifObjectHolder> {

    private ArrayList<NotifObject> mDataset;
    NotifObjectHolder dataObjectHolder;
    private static final String TAG = "MyActivity";

    public static class NotifObjectHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView subject;
        TextView tag;
        TextView description;

       // ImageView personPhoto;
       // ImageButton img_Button1;
      //  ImageButton img_Button2;
      //  ImageButton img_Button3;

        private final Context context;
        public NotifObjectHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            tag = (TextView) itemView.findViewById(R.id.textView);
            date = (TextView) itemView.findViewById(R.id.textView2);
            subject = (TextView) itemView.findViewById(R.id.textView3);
            description = (TextView) itemView.findViewById(R.id.textView4);
        }
    }

    public MyRecyclerViewAdapter2(ArrayList<NotifObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public NotifObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row_two, parent, false);
        //one is for lectures, 2 is for notifications

        dataObjectHolder = new NotifObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(NotifObjectHolder holder, final int position) {
        holder.date.setText(mDataset.get(position).getDate());
        holder.tag.setText(mDataset.get(position).getTag());
        holder.subject.setText(mDataset.get(position).getSubject());
        holder.description.setText(mDataset.get(position).getDescription());
      //  holder.personPhoto.setImageResource(mDataset.get(position).get());
      //  holder.img_Button1.setImageResource(mDataset.get(position).getImgButton1());//Also trial code for the imagebuttons
      //  holder.img_Button2.setImageResource(mDataset.get(position).getImgButton2());//have to remove if imgbuttons don't work

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}