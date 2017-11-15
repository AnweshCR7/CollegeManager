package com.seproject.classmate;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//import com.daimajia.numberprogressbar.NumberProgressBar;

import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Anwesh on 18-06-2015.
 */
public class EventsMain extends AppCompatActivity implements OnProgressBarListener{ /* When using Appcombat support library
                                                         you need to extend Main Activity to
                                                         ActionBarActivity.
                                                      */
    private Toolbar toolbar;                              // Declaring the Toolbar Object


   // SharedPreferences prefs = PreferenceManager
     //       .getDefaultSharedPreferences(this);


    SharedPreferences pref;
    //SharedPreferences.Editor editor = pref.edit();
    String Email;
    String get_attend_url;

    private Timer timer;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter madp;
    private RecyclerView.LayoutManager mLayoutManager;
    Toolbar mToolbarView;
    private ProgressDialog pDialog;
    JSONArray subs = null;
    JSONObject attend = null;
    ///String[] subject={"Operating System","Ingenium","Electricus","Codex","Alkemia","Bazinga"};
    String[] subjects = new String[50]; String[] subjects_url = new String[50]; //MOST USELESS PIECE OF CODING I HAVE DONE>> WILL CHANGE
    String get_sub_url;
    Integer position;
    private NumberProgressBar bnp;
    Double percentage;
    String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);
        ip = getResources().getString(R.string.ip);
        get_sub_url = "http://"+ip+"/classmate/v1/getSub";
        final Integer pos = getIntent().getIntExtra("Grid_Position", 0);
        position = pos;
        pref = getSharedPreferences("MyPref",
                Context.MODE_PRIVATE);
        Email = pref.getString("email", "");


        //Email = pref.getString("email", null);

        final int Grid_position = pos + 1;
        new get_subject_list().execute();

        mToolbarView = (Toolbar) findViewById(R.id.toolbar_view);
        setSupportActionBar(mToolbarView);
        mToolbarView.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mToolbarView.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });           // Setting toolbar as the ActionBar with setSupportActionBar() call



        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        SharedPreferences mSettings = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = mSettings.edit();

        mAdapter = new EventsAdapter(getEventSet());
        mRecyclerView.setAdapter(mAdapter);
        //This addOnItemTouchListener is a class created to provide the touch function for card view..
        //it provides the position of each card... the one that was clicked
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(EventsMain.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int card_position) {
                        card_position++;


                        editor.putInt("X", Grid_position);
                        editor.putInt("Y", card_position);
                        editor.commit();

                        if(card_position == 1) {
                            Intent event_intent = new Intent(getBaseContext(), Subject_tt.class);
                            event_intent.putExtra("X", position);
                            startActivity(event_intent);

                            //event_intent.putExtra("Y", card_position);
                        }
                        else {
                            //Intent event_intent = new Intent(getBaseContext(), Subject_syllabus.class);
                           // startActivity(event_intent);
                            //event_intent.putExtra("X", Grid_position);
                            //event_intent.putExtra("Y", card_position);

                        }
                        //Toast.makeText(EventsMain.this, "" + card_position + " of " + Grid_position, Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }

    class get_subject_list extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        //@Override
        protected void onPreExecute() {
            super.onPreExecute();
            //This progress Dialog works too fast... :/

            pDialog = new ProgressDialog(EventsMain.this);
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

            //Log.d("Response: ", "> " + jsonStr);

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

                        subjects[i] = subjects[i].replace("_"," ");
                        subjects_url[i] = subjects[i].replace(" ","_");
                     //   Log.d("Response: ", "> " + subjects[i]);
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

            getSupportActionBar().setTitle(subjects[position]);

            get_attend_url = "http://"+ip+"/classmate/v1/getAttend/"+Email+"/"+subjects_url[position];
            Log.d("URLREQ: ", "> " + get_attend_url);
            new get_attendence().execute();

            //setListAdapter(adapter);
        }

    }
    class get_attendence extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        //@Override
        protected void onPreExecute() {
            super.onPreExecute();
            //This progress Dialog works too fast... :/

            pDialog = new ProgressDialog(EventsMain.this);
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
            String jsonStr = sh.makeServiceCall(get_attend_url, ServiceHandler.GET);

            //Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    attend = jsonObj.getJSONObject("attendence");

                    // looping through All Contact
                        int present = attend.getInt("present");
                        int total = attend.getInt("total_lec");
                        if(total == 0)
                            percentage = 1.0;
                        else
                            percentage = (double)(present*100/total);

                       Log.d("CHECK: ", "> " + "--"+ present +"--"+ total +"--"+ percentage);

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

            bnp = (NumberProgressBar) findViewById(R.id.number_progress_bar);
            bnp.setOnProgressBarListener(EventsMain.this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bnp.getProgress() < percentage)
                            bnp.incrementProgressBy(1);
                    }
                });
            }
        }, 100, 100);
    }

    }
    @Override
    public void onProgressChange(int current, int max) {
        if(current == max) {
            //Toast.makeText(getApplicationContext(), getString(R.string.finish), Toast.LENGTH_SHORT).show();
            //bnp.setProgress(0);
        }
    }

    private void shareIt(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download the Official Android Application for MindSpark 2015 from the Play Store Now!";

        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "MindSpark'15 App is now LIVE");
        sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody );

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
           /* case R.id.navigate_up:
                NavUtils.navigateUpFromSameTask(this);
                return true;*/

            case R.id.action_settings:
                AlertDialog alertDialog = new AlertDialog.Builder(
                        EventsMain.this).create();

                // Setting Dialog Title
                alertDialog.setTitle("About");

                // Setting Dialog Message
                alertDialog.setMessage("Official Android Application for\nMindSpark 2015.\n\nDeveloped by Incline Studios (Not really)\n");

                // Setting Icon to Dialog
                //alertDialog.setIcon(R.drawable.tick);

                // Setting OK Button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            case R.id.share:
                shareIt();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private ArrayList<EventObject> getEventSet() {
        ArrayList results = new ArrayList<EventObject>();
        /*for (int index = 0; index < 20; index++) {
            DataObject obj = new DataObject("Some Primary Text " + index,
                    "Secondary " + index);
            results.add(index, obj);
        }*/
        EventObject obj1;

        obj1= new EventObject("TimeTable",R.drawable.tt);
        results.add(0,obj1);
        obj1= new EventObject("Syllabus",R.drawable.byldan);
        results.add(1,obj1);
        return results;
    }
}

