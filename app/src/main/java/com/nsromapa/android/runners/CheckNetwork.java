package com.nsromapa.android.runners;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by SAY on 15/08/2018.
 */

public class CheckNetwork{

    public boolean isNetworkAvailable(Context ctx){

        boolean have_WIFI = false;
        boolean have_Mobile = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos  =connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info:networkInfos){
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                    have_WIFI = true;

            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected())
                    have_WIFI = true;
        }



        return have_WIFI ||have_Mobile;
    }




    public AlertDialog.Builder noNetworkAlert(Context c){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);

        builder.setTitle("No Internet Connection");
        builder.setMessage("You need have Mobile Data or WiFi to continue. Press OK to exit");

        return builder;
    }


}
