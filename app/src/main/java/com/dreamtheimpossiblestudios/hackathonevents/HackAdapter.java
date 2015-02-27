package com.dreamtheimpossiblestudios.hackathonevents;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jose on 2/27/2015.
 */
public class HackAdapter extends ArrayAdapter<Hackathon>{
    private Context mContext;
    private List<Hackathon> mHacks;

    public HackAdapter(Context context, List<Hackathon> objects) {
        super(context, R.layout.row_layout2, objects);
        this.mContext = context;
        this.mHacks = objects;
    }

    public View getView(int position, View theView, ViewGroup parent){
       /* if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.row_layout2, null);
        }
        */

        LayoutInflater theInflater = LayoutInflater.from(mContext);                                 // The LayoutInflator puts a layout into the right View
        theView = theInflater.inflate(R.layout.row_layout2, parent, false);                    // inflate takes the resource to load, the parent that the resource may be
                                                                                                    // loaded into and true or false if we are loading into a parent view.

        Hackathon task = mHacks.get(position);                                                      // We retrieve the object from the array
        Log.d("HACKTEST", task.getName());
        TextView theTextView = (TextView) theView.findViewById(R.id.textView1);                     // Get the TextView we want to edit
        theTextView.setText(task.getName());                                                        // Put the next hackathon name into the TextView
        ImageView theImageView = (ImageView) theView.findViewById(R.id.imageView1);                 // Get the ImageView in the layout
        theImageView.setImageResource(R.drawable.dot);                                               // We can set a ImageView like this


        //TextView descriptionView = (TextView) convertView.findViewById(R.id.textView1);
        //descriptionView.setText(task.getName());


        return theView;
    }



}
