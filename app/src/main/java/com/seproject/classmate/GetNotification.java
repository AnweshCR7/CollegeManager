package com.seproject.classmate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Anwesh on 29-06-2015.
 */

public class GetNotification extends ActionBarActivity {

    //String TAG = "Trial";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList NotifList = new ArrayList<NotifObject>();
    private ProgressDialog pDialog;
    //ListAdapter adapter;

    String sub_url;

    JSONArray notifs = null;

    private static final String TAG_DATE = "day_time";
    private static final String TAG_sub = "sub";
    private static final String TAG_descrip = "notification";
    private static final String TAG_tag = "tag";
    Toolbar mToolbarView;
    JSONArray subs = null;
    String[] subjects = new String[50]; //MOST USELESS PIECE OF CODING I HAVE DONE>> WILL CHANGE
    String[] subjects_text = new String[50];
    String get_notif_url;
    String ip;
    Integer position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notif_view);
        mToolbarView = (Toolbar) findViewById(R.id.toolbar_view);
        setSupportActionBar(mToolbarView);
        mToolbarView.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mToolbarView.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();

        ip = getResources().getString(R.string.ip);
        get_notif_url = "http://"+ip+"/classmate/v1/getNotifications";
        new get_notif().execute();
       // position = getIntent().getIntExtra("X", 1);
        //Log.d("printFORUS: ", "> " + position);


        mRecyclerView = (RecyclerView) findViewById(R.id.notif_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
       // mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }
    private void shareIt(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download the Official Android Application for MindSpark 2015 from the Play Store Now!";

        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "MindSpark'15 App is now LIVE");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

   /* class get_subject_list extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         *
        //@Override
        protected String doInBackground(String... args) {
            // Building Parameters
            //List<NameValuePair> params = new ArrayList<NameValuePair>();

            ServiceHandler sh = new ServiceHandler();
            // getting JSON string from URL
            String jsonStr = sh.makeServiceCall(get_sub_url, ServiceHandler.GET);

           // Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    subs = jsonObj.getJSONArray("subjects");

                    // looping through All Contacts
                    for (int i = 0; i < subs.length(); i++) {
                        JSONObject c = subs.getJSONObject(i);

                        String sub_name = c.getString("sub_name");

                        subjects[i] = sub_name;
                        subjects_text[i] = subjects[i];
                        subjects[i] = subjects[i].replace(" ","_");
                       // Log.d("reacheddest: ", "> " + subjects[i]);
                        subjects_text[i] = subjects_text[i].replace("_"," ");
                       // Log.d("reacheddest: ", "> " + subjects_text[i]);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }
        protected void onPostExecute(String file_url) {
            sub_url = "http://"+ip+"/classmate/v1/getTimetable/"+subjects[position];
            Log.d("urlformed: ","> " + sub_url);
            new get_tt().execute();
            getSupportActionBar().setTitle(subjects_text[position]);
        }

    }*/

    class get_notif extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        //@Override
        protected void onPreExecute() {
            super.onPreExecute();
            //This progress Dialog works too fast... :/

            pDialog = new ProgressDialog(GetNotification.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            //List<NameValuePair> params = new ArrayList<NameValuePair>();

            ServiceHandler sh = new ServiceHandler();

            //nameValuePair.add(new BasicNameValuePair("email", email));
           // nameValuePair.add(new BasicNameValuePair("password", password));

            // getting JSON string from URL
            String jsonStr = sh.makeServiceCall(get_notif_url, ServiceHandler.GET);

            //Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    notifs = jsonObj.getJSONArray("notifications");

                    // looping through All Contacts
                    for (int i = 0; i < notifs.length(); i++) {
                        JSONObject c = notifs.getJSONObject(i);

                        String date = c.getString(TAG_DATE);
                        String sub = c.getString(TAG_sub);
                        sub = sub.replace("_"," ");
                        //start = start.substring(0, Math.min(start.length(), 5));
                        //Log.d("sart: ", "> " + start);
                        String descrip = c.getString(TAG_descrip);
                        descrip = descrip.replace("_"," ");//scrip = descrip.replace(">","/");
                        descrip = "\"" + descrip + "\"";
                        //end = end.substring(0, Math.min(end.length(), 5));
                        //Log.d("end: ", "> " + end);
                        //Integer lec_no = Integer.parseInt(lec);
                        String tag = c.getString(TAG_tag);
                        tag = tag.replace("_", " ");
                        //String time = start + " - " + end;
                       /// Log.d("FTIME: ", "> " + time);

                        NotifObject obj1;
                       obj1= new NotifObject(date,sub,tag,descrip);
                      NotifList.add(i, obj1);

                        //Log.d("printFORUS: ", "> " + lectureList + obj1.getlecTime() + obj1.getLecVenue());
                    //    Log.d("printFORUS: ", "> " + ((LectObject)lectureList.get(i)).getlecDay()+((LectObject)lectureList.get(i)).getlecTime()+((LectObject)lectureList.get(i)).getLecVenue());

                        // adding each child node to HashMap key => value
                     //   lecture.put(TAG_DAY, day);
                     //   lecture.put(TAG_LEC, lec);
                       // lecture.put(TAG_VENUE, venue);
                        // adding contact to contact list
                       // lectureList.add(lecture);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            mAdapter = new MyRecyclerViewAdapter2(getDataSet());
            mRecyclerView.setAdapter(mAdapter);
            //setListAdapter(adapter);
        }

    }

    private ArrayList<NotifObject> getDataSet() {
       // ArrayList results = new ArrayList<DataObject>();
                return NotifList;
    }

}
