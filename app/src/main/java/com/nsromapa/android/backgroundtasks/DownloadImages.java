package com.nsromapa.android.backgroundtasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by SAY on 17/08/2018.
 */

public class DownloadImages extends AsyncTask<Void, Void, Bitmap> {
    String url;

    public DownloadImages(String url) {
        this.url = url;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setConnectTimeout(1000 * 30);
            connection.setReadTimeout(1000 * 30);

            return BitmapFactory.decodeStream((InputStream) connection.getContent(), null, null);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
//            if (bitmap!= null){
//                userImage.setImageBitmap(bitmap);
//            }
    }
}

