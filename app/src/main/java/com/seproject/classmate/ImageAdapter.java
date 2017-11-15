package com.seproject.classmate;
//THIS IS A CUSTOM ADAPTER USED BY THE GRIDVIEW CLASS
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anwesh on 12-06-2015.
 */


public class ImageAdapter extends BaseAdapter{
    private final LayoutInflater mInflater;
    private final List<Item> mItems = new ArrayList<Item>();
    private ProgressDialog pDialog;
    JSONArray subs = null;
    ///String[] subject={"Operating System","Ingenium","Electricus","Codex","Alkemia","Bazinga"};
    String[] subjects = new String[50]; //MOST USELESS PIECE OF CODING I HAVE DONE>> WILL CHANGE
    String get_sub_url;


    // private Context mContext;

    public ImageAdapter(Context c) {
        mInflater = LayoutInflater.from(c);
        //mContext = c;
        String ip = c.getResources().getString(R.string.ip);
        get_sub_url = "http://"+ip+"/classmate/v1/getSub";
        new get_subject_list().execute();
       // int i = 0;

      // for(i = 0; i < 3; i++)
        //   mItems.add(new Item(subjects[i],       R.drawable.code_junkie));

       // mItems.add(new Item("Software Engg.",   R.drawable.code_junkie2));
      //  mItems.add(new Item("Signal Processing", R.drawable.codeville));
       // mItems.add(new Item("Data Mining",      R.drawable.debugging));
      //  mItems.add(new Item("Cloud & Big Data", R.drawable.code_junkie2));
      //  mItems.add(new Item("Machine Learning",  R.drawable.code_junkie));

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int i, View view, ViewGroup parent) {
       // ImageView imageView;


        View v = view;
        ImageView picture;
        TextView name;
        if (v == null) {
            // if it's not recycled, initialize some attributes
            v = mInflater.inflate(R.layout.grid_item, parent, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        } /*else {
            imageView = (ImageView) convertView;
        }*/
        Item item = getItem(i);
        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        picture.setImageResource(item.drawableId);

        name.setText(item.name);

        //imageView.setImageResource(mThumbIds[position]);
        return v;
    }

    class get_subject_list extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        protected void onPreExecute() {
            super.onPreExecute();
            //This progress Dialog works too fast... :/

            /*pDialog = new ProgressDialog(GetNotification.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();*/


        }
        //@Override
        protected String doInBackground(String... args) {
            // Building Parameters
            //List<NameValuePair> params = new ArrayList<NameValuePair>();

            ServiceHandler sh = new ServiceHandler();
            // getting JSON string from URL
            String jsonStr = sh.makeServiceCall(get_sub_url, ServiceHandler.GET);

          //  Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    subs = jsonObj.getJSONArray("subjects");

                    // looping through All Contacts
                    for (int i = 0; i < subs.length(); i++) {
                        JSONObject c = subs.getJSONObject(i);
                        //Integer x = i;
                        String sub_name = c.getString("sub_name");


                        subjects[i] = sub_name;
                        subjects[i] = subjects[i].replace("_", " ");
                        mItems.add(new Item(subjects[i], mThumbIds[i%6]));
                        //Log.d("gridview: ", "> " + subjects[i]);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.grreen,
            R.drawable.orange2,
            R.drawable.cyan2,R.drawable.cyan,
            R.drawable.red,R.drawable.pastel
           // R.drawable.pastel,R.drawable.light_orange,
           // R.drawable.green,R.drawable.orange2,R.drawable.light_orange,
           // R.drawable.purp,
           // R.drawable.cyan,
           // R.drawable.pastel,R.drawable.grreen,
           // R.drawable.light_orange

    };

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}

