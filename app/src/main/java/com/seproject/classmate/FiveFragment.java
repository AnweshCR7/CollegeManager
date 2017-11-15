package com.seproject.classmate;

/**
 * Created by Anwesh on 4/18/2016.
 */
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FiveFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList lectureList = new ArrayList<LectObject>();
    private ProgressDialog pDialog;
    //ListAdapter adapter;

    String sub_url;

    JSONArray lecs = null;

    private static final String TAG_sub = "sub";
    private static final String TAG_start = "start";
    private static final String TAG_end = "end";
    private static final String TAG_VENUE = "venue";
    //Toolbar mToolbarView;
    //JSONArray subs = null;
    //String[] subjects = new String[50]; //MOST USELESS PIECE OF CODING I HAVE DONE>> WILL CHANGE
    //String[] subjects_text = new String[50];
    String get_sub_url;
    String ip;
    Integer position;

    public FiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ip = getResources().getString(R.string.ip);
        get_sub_url = "http://"+ip+"/classmate/v1/getTimetableOfDay/Friday";

        //mRecyclerView = (RecyclerView) view.findViewById(R.id.lecture_view);
        //mRecyclerView.setHasFixedSize(true);
       // mLayoutManager = new LinearLayoutManager(getActivity());
        // mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        new get_tt().execute();

        View view = inflater.inflate(R.layout.tab_fragment_one, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.lectures_view);
        mRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //mLayoutManager = new LinearLayoutManager(getActivity());
        // mRecyclerView.setLayoutManager(mLayoutManager);
        return view;
    }


    class get_tt extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        //@Override
        protected void onPreExecute() {
            super.onPreExecute();
            //This progress Dialog works too fast... :/

            pDialog = new ProgressDialog(getActivity());
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
            String jsonStr = sh.makeServiceCall(get_sub_url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);


            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    lecs = jsonObj.getJSONArray("res");

                    lectureList.clear();

                    // looping through All Contacts

                    for (int i = 0; i < lecs.length(); i++) {
                        JSONObject c = lecs.getJSONObject(i);

                        String day = c.getString(TAG_sub);
                        day = day.replace("_"," ");
                        String start = c.getString(TAG_start);
                        start = start.substring(0, Math.min(start.length(), 5));
                        //Log.d("sart: ", "> " + start);
                        String end = c.getString(TAG_end);
                        end = end.substring(0, Math.min(end.length(), 5));
                       // Log.d("end: ", "> " + end);
                        //Integer lec_no = Integer.parseInt(lec);
                        String venue = c.getString(TAG_VENUE);
                        venue = venue.replace("_", " ");
                        String time = start + " - " + end;
                        //Log.d("FTIME: ", "> " + time);

                        LectObject obj1;
                        obj1= new LectObject(day,
                                time,venue);
                        lectureList.add(i, obj1);

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
            mAdapter = new MyRecyclerViewAdapter(getDataSet());
            mRecyclerView.setAdapter(mAdapter);

                    /* adapter = new SimpleAdapter(
                    Subject_tt.this, lectureList,
                    R.layout.lect_details, new String[] { TAG_DAY, TAG_LEC,
                    TAG_VENUE }, new int[] { R.id.day,
                    R.id.time, R.id.venue });*/

            //setListAdapter(adapter);
        }

    }

    private ArrayList<LectObject> getDataSet() {
        // ArrayList results = new ArrayList<DataObject>();
        /*LectObject obj1;
        obj1= new LectObject("Prafull Gupta",
                "Technical Secretary ","");
        lectureList.add(0,obj1);*/
        return lectureList;
    }

}