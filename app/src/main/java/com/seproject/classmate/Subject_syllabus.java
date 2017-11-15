package com.seproject.classmate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Anwesh on 18-06-2015.
 */
/*public class Subject_syllabus extends AppCompatActivity { /* When using Appcombat support library
                                                         you need to extend Main Activity to
                                                         ActionBarActivity.



    private Toolbar toolbar;                              // Declaring the Toolbar Object

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter madp;
    private RecyclerView.LayoutManager mLayoutManager;
    Toolbar mToolbarView;
    String[] subjects={"Operating System","Ingenium","Electricus","Codex","Alkemia","Bazinga"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);

       final Integer pos = getIntent().getIntExtra("Grid_Position", 0);

       final int Grid_position = pos + 1;


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

        getSupportActionBar().setTitle(subjects[pos]);
        switch (Grid_position) {
            case 1:
                mAdapter = new EventsAdapter(getEventSet());
                mRecyclerView.setAdapter(mAdapter);
                //This addOnItemTouchListener is a class created to provide the touch function for card view..
                //it provides the position of each card... the one that was clicked
                mRecyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(Subject_syllabus.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int card_position) {
                                card_position++;


                                editor.putInt("X", Grid_position);
                                editor.putInt("Y", card_position);
                                editor.commit();

                                if(card_position == 0) {
                                    Intent event_intent = new Intent(getBaseContext(), Subject_tt.class);
                                    startActivity(event_intent);
                                    //event_intent.putExtra("X", Grid_position);
                                    //event_intent.putExtra("Y", card_position);
                                }
                                else {
                                    Intent event_intent = new Intent(getBaseContext(), Subject_syllabus.class);
                                    startActivity(event_intent);
                                    //event_intent.putExtra("X", Grid_position);
                                    //event_intent.putExtra("Y", card_position);

                                }
                                Toast.makeText(Subject_syllabus.this, "" + card_position + " of " + Grid_position, Toast.LENGTH_SHORT).show();
                            }
                        })
                );
                break;

            case 2:
                mAdapter = new EventsAdapter(getEventSet());
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(Subject_syllabus.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int card_position) {
                                card_position++;

                                editor.putInt("X", Grid_position);
                                editor.putInt("Y", card_position);
                                editor.commit();
                                Intent event_intent = new Intent(getBaseContext(), EventPageClass.class);
                                startActivity(event_intent);
                                Toast.makeText(Subject_syllabus.this, "" + card_position +"of"+Grid_position , Toast.LENGTH_SHORT).show();
                            }
                        })
                );
                break;
            case 3:
                mAdapter = new EventsAdapter(getEventSet());
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(Subject_syllabus.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int card_position) {
                                card_position++;

                                editor.putInt("X", Grid_position);
                                editor.putInt("Y", card_position);
                                editor.commit();
                                Intent event_intent = new Intent(getBaseContext(), EventPageClass.class);
                                startActivity(event_intent);
                                Toast.makeText(Subject_syllabus.this, "" + card_position +"of"+Grid_position , Toast.LENGTH_SHORT).show();
                            }
                        })
                );
                break;
            case 4:
                mAdapter = new EventsAdapter(getEventSet());
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(Subject_syllabus.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int card_position) {
                                card_position++;

                                editor.putInt("X", Grid_position);
                                editor.putInt("Y", card_position);
                                editor.commit();
                                Intent event_intent = new Intent(getBaseContext(), EventPageClass.class);
                                startActivity(event_intent);
                                //Toast.makeText(EventsMain.this, "" + card_position +"of"+Grid_position , Toast.LENGTH_SHORT).show();
                            }
                        })
                );
                break;
            case 5:
                mAdapter = new EventsAdapter(getEventSet());
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(Subject_syllabus.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int card_position) {
                                card_position++;

                                editor.putInt("X", Grid_position);
                                editor.putInt("Y", card_position);
                                editor.commit();
                                Intent event_intent = new Intent(getBaseContext(), EventPageClass.class);
                                startActivity(event_intent);
                                //Toast.makeText(EventsMain.this, "" + card_position +"of"+Grid_position , Toast.LENGTH_SHORT).show();
                            }
                        })
                );
                break;
            case 6:
                mAdapter = new EventsAdapter(getEventSet());
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(Subject_syllabus.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int card_position) {
                                card_position++;
                                editor.putInt("X", Grid_position);
                                editor.putInt("Y", card_position);
                                editor.commit();
                                Intent event_intent = new Intent(getBaseContext(), EventPageClass.class);
                                startActivity(event_intent);
                                //Toast.makeText(EventsMain.this, "" + card_position +"of"+Grid_position , Toast.LENGTH_SHORT).show();
                            }
                        })
                );
                break;
        }
        //Toast.makeText(EventsMain.this, "" + pos,
           //     Toast.LENGTH_SHORT).show();

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
                return true;

            case R.id.action_settings:
                AlertDialog alertDialog = new AlertDialog.Builder(
                        Subject_syllabus.this).create();

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
        }
        EventObject obj1;

        obj1= new EventObject("TimeTable",R.drawable.tt);
        results.add(0,obj1);
        obj1= new EventObject("Syllabus",R.drawable.byldan);
        results.add(1,obj1);
        return results;
    }
}*/

