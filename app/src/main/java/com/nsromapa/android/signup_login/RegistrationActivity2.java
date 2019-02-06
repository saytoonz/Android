package com.nsromapa.android.signup_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.nsromapa.android.R;

public class RegistrationActivity2 extends AppCompatActivity {
    TextView OpenLogin;
    Button btnNext2;
    EditText regFirstname,regLastname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);

        regFirstname=(EditText)findViewById(R.id.regFirstname);
        regLastname=(EditText)findViewById(R.id.regLastname);


        btnNext2 = (Button) findViewById(R.id.btnNext2);
        btnNext2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent register3 = new Intent(getApplicationContext(),RegistrationActivity3.class);

                String newUsername = getIntent().getStringExtra("newUsername");
                String newEmail = getIntent().getStringExtra("newEmail");
                String newFName = regFirstname.getText().toString();
                String newLName = regLastname.getText().toString();

                register3.putExtra("newUsername",newUsername);
                register3.putExtra("newEmail",newEmail);
                register3.putExtra("newFName",newFName);
                register3.putExtra("newLName",newLName);

                startActivity(register3);
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
