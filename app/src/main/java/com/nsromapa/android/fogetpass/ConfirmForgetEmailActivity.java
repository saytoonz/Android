package com.nsromapa.android.fogetpass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.nsromapa.android.R;

public class ConfirmForgetEmailActivity extends AppCompatActivity {

    EditText ForgCodeEntered;
    Button ForgVerifyCodeButt,forgResendVerif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_forget_email);

        ForgCodeEntered = (EditText)findViewById(R.id.ForgCodeEntered);
        ForgVerifyCodeButt = (Button)findViewById(R.id.ForgVerifyCodeButt);
        forgResendVerif = (Button)findViewById(R.id.forgResendVerifButt);


        String mail = getIntent().getStringExtra("email");
        ForgCodeEntered.setText(mail);





    }
}
