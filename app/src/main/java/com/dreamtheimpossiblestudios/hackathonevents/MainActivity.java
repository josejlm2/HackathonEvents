package com.dreamtheimpossiblestudios.hackathonevents;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "SlkykJCMuwaB3A9kA1E0iaAGXmxyiWWbHqF2Pxes", "rwJHOyXjGhe6Gr97UfOguHuLtt5ucNxIZhdYFuHq");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

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
             });
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
