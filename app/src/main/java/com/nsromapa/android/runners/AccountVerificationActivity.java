package com.nsromapa.android.runners;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nsromapa.android.R;
import com.nsromapa.android.backgroundtasks.BackgroundTask;

public class AccountVerificationActivity extends AppCompatActivity {
        EditText VCode;
        Button Submit,Resend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verification);

        final String uid = getIntent().getStringExtra("uid");

        VCode = (EditText) findViewById(R.id.verificationKey);
        Submit = (Button) findViewById(R.id.submitVerif);
        Resend = (Button) findViewById(R.id.resendVerifBtn);

        Resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundTask backgroundTask = new BackgroundTask(AccountVerificationActivity.this);
                backgroundTask.execute("Resend_Account_Verification_Code",uid);
            }
        });


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String code  =   VCode.getText().toString();

                if (code.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter code to submit",Toast.LENGTH_LONG).show();

                }else if (code.trim().length()!=6){
                    Toast.makeText(getApplicationContext(),"Code must be 6 characters",Toast.LENGTH_LONG).show();

                }else{

                    BackgroundTask backgroundTask = new BackgroundTask(AccountVerificationActivity.this);
                    backgroundTask.execute("Verify_my_new_account",code,uid);

                }
            }
        });
    }
}
