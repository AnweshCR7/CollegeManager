package com.seproject.classmate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class HelloGridView extends AppCompatActivity {
    Integer pos;
    private Toolbar toolbar;
    Toolbar mToolbarView;
    int MainActivity_Call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_grid_view);

       // MainActivity_Call = getIntent().getIntExtra("Main_Activity_call", 0);
        mToolbarView = (Toolbar) findViewById(R.id.toolbar_view);
        setSupportActionBar(mToolbarView);
        mToolbarView.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mToolbarView.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        //setSupportActionBar(toolbar);                  // Setting toolbar as the ActionBar with setSupportActionBar() call

        //getActionBar().setDisplayHomeAsUpEnabled(true);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // pos = position;
                Intent L_intent = new Intent(getBaseContext(), EventsMain.class);
                L_intent.putExtra("Grid_Position", position);
                startActivity(L_intent);
                //Toast.makeText(HelloGridView.this, "" + position,
                //   Toast.LENGTH_SHORT).show();

            }
        });

        final View actionA = findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(HelloGridView.this);
                    /*Toast.makeText(MainActivity.this, "Action A was clicked",
                            Toast.LENGTH_SHORT).show();*/

            }
        });


        final View actionB = findViewById(R.id.action_c);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Action B was clicked",
                //  Toast.LENGTH_SHORT).show();
               Intent j = new Intent(getBaseContext(), Complete_TT.class);
                j.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(j);
            }
        });
        final View actionC = findViewById(R.id.action_d);
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent k = new Intent(getBaseContext(), GetNotification.class);
                startActivity(k);

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private void shareIt(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download the Official Android Application for MindSpark 2015 from the Play Store Now!";

        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "MindSpark'15 App is now LIVE");
        sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody );

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
    //this has been added to minimize the Floating button i.e restart the mainactivity on backpress
    public void onBackPressed(){
        if(MainActivity_Call == 1)
            NavUtils.navigateUpFromSameTask(this);
        else
            super.onBackPressed();
        //startActivity(new Intent(this,MainActivity.class));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
           // case R.id.navigate_up:
               // NavUtils.navigateUpFromSameTask(this);
              //  return true;

            case R.id.action_settings:
                AlertDialog alertDialog = new AlertDialog.Builder(
                        HelloGridView.this).create();

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
                alertDialog.show();
                return true;

            case R.id.share:
                shareIt();
                return true;
                // Showing Alert Message

        }

        return super.onOptionsItemSelected(item);
    }

    public Integer getMyData() {
        return pos;
    }
    public void uphandler(View v){
        NavUtils.navigateUpFromSameTask(this);    // This will kill current activity, and if previous activity is still opened in background, it will come in front.
    }

}
