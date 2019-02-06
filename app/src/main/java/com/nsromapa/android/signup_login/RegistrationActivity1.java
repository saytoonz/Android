package com.nsromapa.android.signup_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nsromapa.android.R;
import com.nsromapa.android.backgroundtasks.BackgroundTask;

public class RegistrationActivity1 extends AppCompatActivity {

    TextView OpenLogin;
    TextView regUsername,regemail;
    Button btnNext1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);

        regUsername=(TextView)findViewById(R.id.regUsername);
        regemail=(TextView)findViewById(R.id.regemail);


        btnNext1 = (Button) findViewById(R.id.btnNext1);
        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = regUsername.getText().toString();
                String email = regemail.getText().toString();

                BackgroundTask backgroundTask = new BackgroundTask(RegistrationActivity1.this);
                backgroundTask.execute("check_username_and_email_for_signup",username,email);

            }
        });











        //Open log in intent
        OpenLogin=(TextView) findViewById(R.id.OpenLogin);
        OpenLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(),LoginActivity.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(login);
                finish();
            }
        });
    }
}
