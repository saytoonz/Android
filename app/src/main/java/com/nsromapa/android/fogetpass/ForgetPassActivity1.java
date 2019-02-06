package com.nsromapa.android.fogetpass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nsromapa.android.R;
import com.nsromapa.android.backgroundtasks.BackgroundTask;

public class ForgetPassActivity1 extends AppCompatActivity {

    EditText fogUsername,fogEmail;
    Button fogSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass1);

        String username = getIntent().getStringExtra("username");

        fogUsername=(EditText) findViewById(R.id.fogUsername);
        fogEmail=(EditText) findViewById(R.id.fogEmail);
        fogSearch=(Button) findViewById(R.id.fogSearch);

        if (username.contains("@")){
            fogEmail.setText(username);
        }else{
            fogUsername.setText(username);
        }


        fogSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = fogUsername.getText().toString();
                String newEmail = fogEmail.getText().toString();

                if (!newUsername.isEmpty()&& !newEmail.isEmpty()){
                    if (newEmail.contains("@")&&newEmail.contains(".")){

                        BackgroundTask backgroundTask = new BackgroundTask(ForgetPassActivity1.this);
                        backgroundTask.execute("FogPassSearchU",newUsername,newEmail);

                    }else {
                        Toast.makeText(ForgetPassActivity1.this,
                                "Sorry, you entered an invalid Email",
                                Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(ForgetPassActivity1.this,
                            "Please enter your username and email...",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
