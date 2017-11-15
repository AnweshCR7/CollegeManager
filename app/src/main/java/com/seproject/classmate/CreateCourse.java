package com.seproject.classmate;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CreateCourse extends AppCompatActivity {
    private static final String TAG = "CreateCourse";

    @InjectView(R.id.course_name) EditText _coursename;
   // @InjectView(R.id.course_type) EditText _coursetype;
    @InjectView(R.id.btn_create) Button _createButton;
    //@InjectView(R.id.link_login) TextView _loginLink;

    int create_ok;
    String [] opts ={"theory","practical"};
    String name;
    String create_course_url;
    //String email;
    //String password;
    String type;
    ProgressDialog progressDialog;
    List<NameValuePair> nameValuePair1 = new ArrayList<NameValuePair>(3);

    String register_url;
    Spinner spnr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createcourse);
        ButterKnife.inject(this);

        String ip = getResources().getString(R.string.ip);
        create_course_url = "http://"+ip+"/classmate/v1/putSub/";

        spnr = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, opts);

        spnr.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = spnr.getSelectedItemPosition();
                        // Toast.makeText(getApplicationContext(),"You have selected "+opts[position],Toast.LENGTH_SHORT).show();
                        type = opts[position];
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                }
        );

        _createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = _coursename.getText().toString();
                name = name.replace(" ","_");
               // type = _coursetype.getText().toString();
                create_course_url = create_course_url +name + "/" +type + "/0";
                new create().execute();
                //AsyncTask
            }
        });
    }

    /*public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(CreateCourse.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

         name = _nameText.getText().toString();
         email = _emailText.getText().toString();
         password = _passwordText.getText().toString();
         type = _type.getText().toString();

        // TODO: Implement your own signup logic here.

        signup_ok = 0; // initialize to zero to prevent accidental entry...
        new register().execute();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        if(signup_ok == 1)
                            onSignupSuccess();
                        else
                            onSignupFailed(); /*
                        progressDialog.dismiss();
                    }
                }, 3000);
    }*/


    public void onCreateSuccess() {
        _createButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
        NavUtils.navigateUpFromSameTask(CreateCourse.this);
        //Intent j = new Intent(getBaseContext(), Complete_TT.class);
        //j.putExtra("Main_Activity_call", 1);
        //startActivity(j);
    }

    public void onCreateFailed() {
        Toast.makeText(getBaseContext(), "course could not be created :(", Toast.LENGTH_LONG).show();

        _createButton.setEnabled(true);
    }


    class create extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        //@Override
        protected void onPreExecute() {
            super.onPreExecute();
            //This progress Dialog works too fast... :/

            progressDialog = new ProgressDialog(CreateCourse.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            //List<NameValuePair> params = new ArrayList<NameValuePair>();

            ServiceHandler sh = new ServiceHandler();
            //nameValuePair1.add(new BasicNameValuePair("name", name));

            //nameValuePair1.add(new BasicNameValuePair("type", type));

            // getting JSON string from URL
            String jsonStr = sh.makeServiceCall(create_course_url, ServiceHandler.PUT);

           // Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    boolean success = jsonObj.getBoolean("error");
                    if(!success){
                       create_ok = 1;
                        //user_name = jsonObj.getString("name");
                       // Log.d("Response: ", "> " + user_name);
                        //boolean success = jsonObj.getBoolean("error");
                    }
                    else{
                       create_ok = 0;
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
            progressDialog.dismiss();
            if(create_ok == 1){
                onCreateSuccess();
            }
            else
            {onCreateFailed();}


        }

    }
}