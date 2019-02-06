package com.nsromapa.android.backgroundtasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by SAY on 22/08/2018 at Nsromapa Goaso.
 */
public class SaveImageToSDCARD {
    //save image
    public SaveImageToSDCARD(Context ctx, String image_url , String filename){
        String state;
        state = Environment.getExternalStorageState();

        Picasso.get()
                .load(image_url)
                .into(getTarget(filename,ctx));

    }

    //target to save
    private static Target getTarget(final String filename, final Context ctx){
        Target target = new Target(){

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
//                new Thread(new Runnable() {
//
//                    @Override
//                    public void run() {
                        saveImage(ctx,bitmap,filename);
//                    }
//                }).start();

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }

    public static void saveImage(Context context, Bitmap b, String imageName) {
        FileOutputStream foStream;
        try {
            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.JPEG, 90, foStream);
            foStream.close();

          } catch (Exception e) {
            Log.d("saveImage", "Exception 2, Something went wrong!");
            e.printStackTrace();
        }
    }
}
