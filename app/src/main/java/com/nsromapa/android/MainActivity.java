package com.nsromapa.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nsromapa.android.runners.CheckNetwork;
import com.nsromapa.android.sessions.UserSessionManager;
import com.nsromapa.android.signup_login.LoginActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    UserSessionManager session;
    CheckNetwork checkNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int SPLASH_TIME_OUT = 2500;
        session = new UserSessionManager(getApplicationContext());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Check user login
                //If user is not logged in, This will redirect user to LoginActivity
                if (session.checkLogin()) {

                    Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(homeIntent);
                    finish();

                } else {

                    checkNetwork = new CheckNetwork();
                    //Check network status before redirecting user to login page
                    if (checkNetwork.isNetworkAvailable(MainActivity.this)) {
                        Intent LoginIntent = new Intent(MainActivity.this, LoginActivity.class);
                        LoginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        LoginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(LoginIntent);
                        finish();

                    } else if (!checkNetwork.isNetworkAvailable(MainActivity.this)) {
                        AlertDialog.Builder builder = checkNetwork.noNetworkAlert(MainActivity.this);

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }


                        });

                        builder.setNegativeButton("REFRESH", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                recreate();
                            }
                        });
                        builder.show();

                    }

                }

            }
        }, SPLASH_TIME_OUT);
    }
}
