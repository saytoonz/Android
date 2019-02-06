package com.nsromapa.android.runners;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nsromapa.android.HomeActivity;
import com.nsromapa.android.R;
import com.nsromapa.android.backgroundtasks.BackgroundTask;
import com.nsromapa.android.sessions.UserSessionManager;

public class CompleteVerificationActivity extends AppCompatActivity {

    Button LikeNsromapa;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_verification);

        LikeNsromapa = (Button)findViewById(R.id.Liked_Nsromapa_to_continue);
        LikeNsromapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = getIntent().getStringExtra("uid");
                BackgroundTask backgroundTask = new BackgroundTask(CompleteVerificationActivity.this);
                backgroundTask.execute("Like_user_as_you_want_to_follow",uid);

                //Navigate to the Home page
                Intent homeIntent = new Intent(getApplicationContext(),HomeActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(homeIntent);
            }
        });

    }
}
