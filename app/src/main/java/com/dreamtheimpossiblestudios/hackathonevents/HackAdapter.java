package com.dreamtheimpossiblestudios.hackathonevents;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jose on 2/27/2015.
 */
public class HackAdapter extends ArrayAdapter<Hackathon>{
    private Context mContext;
    private List<Hackathon> mHacks;
    MemoryCache memoryCache = new MemoryCache();
    FileCache fileCache;
    ExecutorService executorService;

    public HackAdapter(Context context, List<Hackathon> objects) {
        super(context, R.layout.row_layout2, objects);
        this.mContext = context;
        this.mHacks = objects;
        fileCache = new FileCache(context);
        executorService = Executors.newFixedThreadPool(5);
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
        //theImageView.setImageBitmap(getBitmap(task.getLogo().getUrl()));

        //TextView descriptionView = (TextView) convertView.findViewById(R.id.textView1);
        //descriptionView.setText(task.getName());




        return theView;
    }

    public Bitmap getBitmap(String url) {
        File f = fileCache.getFile(url);

        Bitmap b = decodeFile(f);
        if (b != null)
            return b;

// Download Images from the Internet
        try {
            Bitmap bitmap = null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl
                    .openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is = conn.getInputStream();
            OutputStream os = new FileOutputStream(f);
            Utils.CopyStream(is, os);
            os.close();
            conn.disconnect();
            bitmap = decodeFile(f);
            return bitmap;
        } catch (Throwable ex) {
            ex.printStackTrace();
            if (ex instanceof OutOfMemoryError)
                memoryCache.clear();
            return null;
        }
    }












    // Decodes image and scales it to reduce memory consumption
   public Bitmap decodeFile(File f) {
        try {
// Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            FileInputStream stream1 = new FileInputStream(f);
            BitmapFactory.decodeStream(stream1, null, o);
            stream1.close();

// Find the correct scale value. It should be the power of 2.
// Recommended Size 512
            final int REQUIRED_SIZE = 70;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

// Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            FileInputStream stream2 = new FileInputStream(f);
            Bitmap bitmap = BitmapFactory.decodeStream(stream2, null, o2);
            stream2.close();
            return bitmap;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

