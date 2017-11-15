package com.seproject.classmate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;

/**
 * Created by Anwesh on 4/19/2016.
 */
public class FacDash extends AppCompatActivity implements OnProgressBarListener{
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private Timer timer;
    Toolbar mToolbarView;
    private NumberProgressBar bnp;
    Dialog dialog;

    ArrayList<Integer> selected_list = new ArrayList();
    ArrayList<String> list = new ArrayList();

    CharSequence box_list[] = {"Tea", "Coffee", "fuck"};
    //List<CharSequence> box_list = new ArrayList<>();
    JSONArray subs1 = null;
    SharedPreferences pref;
    String Email;
    private Toolbar toolbar;
    LinearLayout lm;
    JSONArray subs = null;
    String[] subjects = new String[50]; //MOST USELESS PIECE OF CODING I HAVE DONE>> WILL CHANGE
    //Intent intent = new Intent();
    int[] percentage = new int[50];
    String[] subject_names = new String[50]; //MOST USELESS PIECE OF CODING I HAVE DONE>> WILL CHANGE
    String[] subjects_text = new String[50];
    String get_sub_url;
    // String user = intent.getExtras().getString("user_name", "INVALID");
    int size;
    String get_attendance_url;
    int number;
    String msg ="";
    String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fac_dash);
        pref = getSharedPreferences("MyPref",
                Context.MODE_PRIVATE);
        Email = pref.getString("email", "");

        ip = getResources().getString(R.string.ip);
       // get_attendance_url = "http://"+ip+"/classmate/v1/getLessAttendance/"+Email;
        get_sub_url = "http://"+ip+"/classmate/v1/getSub";

        //Log.d("urlformed: ", "> " + get_attendence_url);

          new get_subject_list().execute();
       // new get_less_attendance().execute();

        SharedPreferences preferences = this.getSharedPreferences("user_det", Context.MODE_PRIVATE);
        String user_name = preferences.getString("name", "No name defined");

        mToolbarView = (Toolbar) findViewById(R.id.toolbar_view);
        setSupportActionBar(mToolbarView);

        TextView info_text = (TextView)findViewById(R.id.text);
        String concat = "Welcome " + user_name;
        info_text.setText(concat);
        // percentage = 50.00;
        lm = (LinearLayout)findViewById(R.id.llayout);

        final View actionA = findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), CreateCourse.class);
                i.putExtra("Main_Activity_call",1);
                startActivity(i);
                    /*Toast.makeText(MainActivity.this, "Action A was clicked",
                            Toast.LENGTH_SHORT).show();*/

            }
        });

        final View actionB = findViewById(R.id.action_b);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Action B was clicked",
                //  Toast.LENGTH_SHORT).show();
                Intent j = new Intent(getBaseContext(), Complete_TT.class);
                j.putExtra("Main_Activity_call",1);
                startActivity(j);
            }
        });


        final View actionC = findViewById(R.id.action_c);
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(getBaseContext(), AddLec.class);
                //k.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(k);

            }
        });

        final View actionE = findViewById(R.id.action_e);
        actionE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(getBaseContext(), AddNotif.class);
                //k.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);

            }
        });

        final View actionD = findViewById(R.id.action_d);
        actionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(getBaseContext(), LoginActivity.class);

                l.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(l);

            }
        });

    }

    class get_subject_list extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
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
                    subs1 = jsonObj.getJSONArray("subjects");

                    size = subs1.length();
                    // looping through All Contacts
                    for (int i = 0; i < subs1.length(); i++) {
                        JSONObject c = subs1.getJSONObject(i);

                        String sub_name = c.getString("sub_name");

                        subject_names[i] = sub_name;
                        subjects_text[i] = subject_names[i];
                        subject_names[i] = subject_names[i].replace(" ", "_");
                        // Log.d("reacheddest: ", "> " + subjects[i]);
                        subjects_text[i] = subjects_text[i].replace("_", " ");

                        // box_list[i] = subjects_text[i];
                        //Log.d("reacheddest: ", "> " + subjects_text[i]);

                        list.add(subjects_text[i]);
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
            //Log.d("rdest: ", "> " + subjects_text[0]+subjects_text[1]+subjects_text[2]);
            Button btn = (Button) findViewById(R.id.Attend_Button);

            box_list = list.toArray(new CharSequence[list.size()]);

            final boolean bl[] = new boolean[size];
            final AlertDialog.Builder builder = new AlertDialog.Builder(FacDash.this);
            builder.setTitle("What Lectures have been taken today?");
            builder.setMultiChoiceItems(box_list, bl,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedItemId,
                                            boolean isSelected) {
                            //isSelected = new boolean[size];
                            if (isSelected) {
                                selected_list.add(selectedItemId);
                            } else if (selected_list.contains(selectedItemId)) {
                                selected_list.remove(Integer.valueOf(selectedItemId));
                            }
                        }
                    });

            builder.setPositiveButton("Done!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    msg = "";
                    for (int i = 0; i < selected_list.size(); i++) {

                        msg = msg + "\n" + (i + 1) + " : " + subjects_text[selected_list.get(i)];
                    }
                    Toast.makeText(getApplicationContext(),
                            "Total " + selected_list.size() + " Items Selected.\n" + msg, Toast.LENGTH_LONG)
                            .show();
                    new update_attendance().execute();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Arrays.fill(bl, false);
                    selected_list.clear();
                    dialog = builder.create();
                    dialog.show();

                    // Toast.makeText(FacDash.this, "Wohoo! See You There!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    class update_attendance extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        //@Override
        protected String doInBackground(String... args) {
            // Building Parameters
            //List<NameValuePair> params = new ArrayList<NameValuePair>();
            String jsonStr;
            String url;
            ServiceHandler sh = new ServiceHandler();
            // getting JSON string from URL

            //THE TOTAL NEEDS TO BE CHECKED
            for (int i = 0; i < selected_list.size(); i++) {

                url = "http://"+ip+"/classmate/v1/updateTeacherAttendance/"+subjects_text[selected_list.get(i)];
                Log.d("formed_url: ", "> " + url);
                jsonStr = sh.makeServiceCall(url, ServiceHandler.PUT);
            }
            return null;
        }
        protected void onPostExecute(String file_url) {
            //sub_url = "http://"+ip+"/classmate/v1/getTimetable/"+subjects[position];
            // Log.d("urlformed: ", "> " + sub_url);
            // new get_tt().execute();
            // getSupportActionBar().setTitle(subjects_text[position]);
        }

    }



    private void shareIt(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download the Official Android Application for MindSpark 2015 from the Play Store Now!";

        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "MindSpark'15 App is now LIVE");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,shareBody );

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onProgressChange(int current, int max) {
        if(current == max) {
            //Toast.makeText(getApplicationContext(), getString(R.string.finish), Toast.LENGTH_SHORT).show();
            //bnp.setProgress(0);
        }
    }


    public void uphandler(View v){
        NavUtils.navigateUpFromSameTask(this);    // This will kill current activity, and if previous activity is still opened in background, it will come in front.
    }


}