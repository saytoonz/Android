package com.nsromapa.android.signup_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nsromapa.android.R;
import com.nsromapa.android.backgroundtasks.BackgroundTask;

public class RegistrationActivity4 extends AppCompatActivity {
    TextView OpenLogin;
    Button btnNext4;
    EditText regPassword,regPassword2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration4);

        regPassword =(EditText)findViewById(R.id.regPassword);
        regPassword2 =(EditText)findViewById(R.id.regPassword2);
        OpenLogin =(TextView) findViewById(R.id.OpenLogin);



        String newUsername = getIntent().getStringExtra("newUsername");
        String newEmail = getIntent().getStringExtra("newEmail");
        String newFName =  getIntent().getStringExtra("newFName");
        String newLName =  getIntent().getStringExtra("newLName");
        String DOB =  getIntent().getStringExtra("DOB");
        String Gender =  getIntent().getStringExtra("Gender");

        Toast.makeText(this,
                "uname:"+newUsername+", email:"+newEmail+" fname:"+
                        " "+newFName+" lname:"+newLName+" DOB:"+DOB+" Gender:"+Gender,
                Toast.LENGTH_SHORT).show();



        btnNext4 = (Button) findViewById(R.id.btnNext4);
        btnNext4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String newUsername = getIntent().getStringExtra("newUsername");
                String newEmail = getIntent().getStringExtra("newEmail");
                String newFName =  getIntent().getStringExtra("newFName");
                String newLName =  getIntent().getStringExtra("newLName");
                String DOB =  getIntent().getStringExtra("DOB");
                String Gender =  getIntent().getStringExtra("Gender");

                Editable pass = regPassword.getText();
                Editable pass2 = regPassword2.getText();


                if (pass.toString().length()<5 || pass.toString().length()>32){
                    Toast.makeText(RegistrationActivity4.this,
                            "Password must be within 8 and 32 characters",
                            Toast.LENGTH_LONG).show();
                }else{
                    if (!pass.toString().equals(pass2.toString())){
                        Toast.makeText(RegistrationActivity4.this,
                                "Password do not match...",
                                Toast.LENGTH_LONG).show();
                    }else{

                        String npass = pass.toString();
                        String npass2 = pass2.toString();

                        BackgroundTask backgroundTask = new BackgroundTask(RegistrationActivity4.this);
                        backgroundTask.execute("SendSignUp_registration",newUsername,newEmail,newFName,newLName,DOB,Gender,npass,npass2);
                    }
                }
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
