package com.seproject.classmate;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    int login_ok = 0;


    //Global Variables for the user
    String user_name;
    String email;
    String password;
    String type;

    String error_message;
    int error_cue = 0;

    private ProgressDialog progressDialog;

    List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);

   // WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
   // String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

    // url to get all products list
    String login_url;
    SharedPreferences pref;

    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);


        String ip = getResources().getString(R.string.ip);
        login_url = "http://"+ip+"/classmate/v1/login";

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        //Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

         email = _emailText.getText().toString();
         password = _passwordText.getText().toString();
            SharedPreferences.Editor editor = pref.edit();
                editor.putString("email", email);
            //editor.putInt("idName", 12);
            editor.commit();


        // TODO: Implement your own authentication logic here.
        login_ok = 0; // initialize to zero to prevent accidental entry...
        //new authorize().execute();

        //login_ok = 1; //TO BE REMOVED ONCE TESTING IS DONE --> dont know why this doesn't work...
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        //my login code b/w the two commented sections
                      /**/
                        if(login_ok == 1)
                            onLoginSuccess();
                        else
                            onLoginFailed(); /**/
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    // Background Async Task to authenticate
    class authorize extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        //@Override
        protected void onPreExecute() {
            super.onPreExecute();
            //This progress Dialog works too fast... :/

            /*progressDialog = new ProgressDialog(LoginActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();*/
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            //List<NameValuePair> params = new ArrayList<NameValuePair>();

            ServiceHandler sh = new ServiceHandler();

            nameValuePair.add(new BasicNameValuePair("email", email));
            nameValuePair.add(new BasicNameValuePair("password", password));

            // getting JSON string from URL
            String jsonStr = sh.makeServiceCall(login_url, ServiceHandler.POST, nameValuePair);

                Log.d("Response: ", "> " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        boolean success = jsonObj.getBoolean("error");
                            if(!success){
                                login_ok = 1;
                                user_name = jsonObj.getString("name");
                                type = jsonObj.getString("type");
                                Log.d("Response: ", "> " + type);
                                SharedPreferences.Editor editor = getSharedPreferences("user_det", MODE_PRIVATE).edit();
                                editor.putString("name", user_name);
                                //editor.putInt("idName", 12);
                                editor.commit();
                                //boolean success = jsonObj.getBoolean("error");
                            }
                            else{
                                login_ok = 0;
                                error_message = jsonObj.getString("message");
                                error_cue = 1;
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
           //progressDialog.dismiss();
            // updating UI from Background Thread
            /*runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    /*ListAdapter adapter = new SimpleAdapter(
                            AllProductsActivity.this, productsList,
                            R.layout.list_item, new String[] { TAG_PID,
                            TAG_NAME},
                            new int[] { R.id.pid, R.id.name });
                    // updating listview
                    setListAdapter(adapter);

                }
            });*/

        }

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();

        if(type.equals("student")) {
            Intent k = new Intent(getBaseContext(), StudentDash.class);
            k.putExtra("user_name", user_name);
            startActivity(k);
        }
        else{
            Intent k = new Intent(getBaseContext(), FacDash.class);
            k.putExtra("user_name", user_name);
            startActivity(k);
        }
    }

    public void onLoginFailed() {

        if(error_cue == 1)
            Toast.makeText(getBaseContext(), error_message, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getBaseContext(), "Server cannot be reached!", Toast.LENGTH_LONG).show();


        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}