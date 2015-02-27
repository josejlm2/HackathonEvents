package com.dreamtheimpossiblestudios.hackathonevents;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Simple array with a list of my favorite TV shows
         String[] favoriteTVShows = {"Pushing Daisies", "Better Off Ted",
        	        "Twin Peaks", "Freaks and Geeks", "Orphan Black", "Walking Dead",
        	        "Breaking Bad", "The 400", "Alphas", "Life on Mars"};

         // The ListAdapter acts as a bridge between the data and each ListItem
         // You fill the ListView with a ListAdapter. You pass it a context represented by
         // this. A Context provides access to resources you need.
         // android.R.layout.simple_list_item_1 is one of the resources needed.
         // It is a predefined layout provided by Android that stands in as a default

        ListAdapter theAdapter = new ArrayAdapter<String>(this, R.layout.row_layout,
                	            R.id.textView1, favoriteTVShows);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
