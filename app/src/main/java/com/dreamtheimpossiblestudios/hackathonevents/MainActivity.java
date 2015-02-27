package com.dreamtheimpossiblestudios.hackathonevents;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private EditText mHackInput;
    private ListView mListView;
    private HackAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize parse
        Parse.enableLocalDatastore(this);                                                            // Enable Local Datastore
        Parse.initialize(this, "SlkykJCMuwaB3A9kA1E0iaAGXmxyiWWbHqF2Pxes",
                "rwJHOyXjGhe6Gr97UfOguHuLtt5ucNxIZhdYFuHq");
        ParseObject.registerSubclass(Hackathon.class);                                              //register class with activity


        mAdapter = new HackAdapter(this, new ArrayList<Hackathon>());
        mHackInput = (EditText) findViewById(R.id.hackathon_input);
        mListView = (ListView) findViewById(R.id.theListView);


        mListView.setAdapter(mAdapter);
//        mListView.setOnItemClickListener(this);
      //  updateData();
        updateData2();



/*

        // Simple array with a list of my favorite TV shows
           String[] favoriteTVShows = {"Pushing Daisies", "Better Off Ted",
       	                "Twin Peaks", "Freaks and Geeks", "Orphan Black", "Walking Dead",
      	                "Breaking Bad", "The 400", "Alphas", "Life on Mars"};

           // A View is the generic term and class for every widget you put on your screen.
           // Views occupy a rectangular area and are responsible for handling events
           // and drawing the widget.

           // The ListAdapter acts as a bridge between the data and each ListItem
           // You fill the ListView with a ListAdapter. You pass it a context represented by
           // this.

           // A Context provides access to resources you need. It provides the current Context, or
           // facts about the app and the events that have occurred with in it.
           // android.R.layout.simple_list_item_1 is one of the resources needed.
           // It is a predefined layout provided by Android that stands in as a default

            ListAdapter theAdapter = new MyAdapter(this,favoriteTVShows);

           // We point the ListAdapter to our custom adapter
           // ListAdapter theAdapter = new MyAdapter(this, favoriteTVShows);

           // Get the ListView so we can work with it
           ListView theListView = (ListView) findViewById(R.id.theListView);

           // Connect the ListView with the Adapter that acts as a bridge between it and the array
           theListView.setAdapter(theAdapter);




        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

       	                String tvShowPicked = "You selected " +
                                   String.valueOf(adapterView.getItemAtPosition(i));

       	                Toast.makeText(MainActivity.this, tvShowPicked, Toast.LENGTH_SHORT).show();

       	        }
        });*/
    }


    public void createHackathon(View v) {
        if (mHackInput.getText().length() > 0){
            Hackathon t = new Hackathon();
            t.setName(mHackInput.getText().toString());
            Log.d("TEST", mHackInput.getText().toString());
            t.setLocation("");
            t.saveEventually();
            mAdapter.insert(t, 0);                                                                  //inserts new info into views
            mHackInput.setText("");
        }
        HackAdapter mAdapter = new HackAdapter(this, new ArrayList<Hackathon>());
        mListView.setAdapter(mAdapter);
    }


    public void updateData2(){
        ParseQuery<Hackathon> query = ParseQuery.getQuery(Hackathon.class);
        query.findInBackground(new FindCallback<Hackathon>() {

            @Override
            public void done(List<Hackathon> tasks, ParseException error) {
                if(tasks != null){
                    mAdapter.clear();
                    mAdapter.addAll(tasks);
                }
            }
        });
    }




















    public void updateData(){

        ParseQuery<Hackathon> query = ParseQuery.getQuery(Hackathon.class);
        //query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.findInBackground(new FindCallback<Hackathon>() {
            @Override
            public void done(List<Hackathon> tasks, ParseException error) {
                if(tasks != null){
                    //mAdapter.clear();
                    for (int i = 0; i < tasks.size(); i++) {

                        Log.d("DATA?", tasks.get(i).getName());
                        mAdapter.add(tasks.get(i));
                    }
                }
            }
        });





        /*
        ParseQuery<Hackathon> query = ParseQuery.getQuery(Hackathon.class);
        query.findInBackground(new FindCallback<Hackathon>() {

            @Override
            public void done(List<Hackathon> tasks, ParseException error) {
                if(tasks != null){
                    //mAdapter.clear();
                    //mAdapter.addAll(tasks);
                }
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);                                          // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {                                           // Handle action bar item clicks here. The action bar will
                                                                                                    // automatically handle clicks on the Home/Up button, so long
                                                                                                    // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {                                                           //noinspection SimplifiableIfStatement
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
