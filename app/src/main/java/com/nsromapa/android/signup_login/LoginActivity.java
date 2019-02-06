package com.nsromapa.android.signup_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nsromapa.android.R;
import com.nsromapa.android.backgroundtasks.BackgroundTask;
import com.nsromapa.android.fogetpass.ForgetPassActivity1;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;

    EditText txtUsername, txtPassword;
    TextView Opensignup,forgetPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //When sign up button is clicked
        Opensignup=(TextView)findViewById(R.id.Opensignup);
        forgetPass=(TextView)findViewById(R.id.forgetPass);


        Opensignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(getApplicationContext(),RegistrationActivity1.class);
                register.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                register.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(register);
                finish();
            }
        });





        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);


        /////When log in button is clicked
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = txtUsername.getText().toString();
                final String password = txtPassword.getText().toString();

                if (username.trim().length()>0 && password.trim().length()>0){

                    BackgroundTask backgroundTask = new BackgroundTask(LoginActivity.this);
                    backgroundTask.execute("try_entry",username,password);

                    txtPassword.setText("");

                }else{
                    Toast.makeText(getApplicationContext(),
                            "Please enter Username and Password",
                            Toast.LENGTH_LONG).show();
                }
            }
        });



        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = txtUsername.getText().toString();

                //Navigate to forget Password Intent
                Intent forgetPass = new Intent(getApplicationContext(),ForgetPassActivity1.class);
                forgetPass.putExtra("username",username);
                startActivity(forgetPass);
            }
        });
    }
}
