package com.nsromapa.android.signup_login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nsromapa.android.R;

public class RegistrationActivity3 extends AppCompatActivity {

    TextView OpenLogin,regDateOfBirth;
    Button btnNext3;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration3);

        regDateOfBirth = (TextView)findViewById(R.id.regDateOfBirth);
        radioGroup= (RadioGroup)findViewById(R.id.radioGroup);

        regDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegistrationActivity3.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date = month+" / "+dayOfMonth+" / "+year;

                regDateOfBirth.setText(date);
            }
        };


        btnNext3 = (Button) findViewById(R.id.btnNext3);
        btnNext3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String newUsername = getIntent().getStringExtra("newUsername");
                String newEmail = getIntent().getStringExtra("newEmail");
                String newFName =  getIntent().getStringExtra("newFName");
                String newLName =  getIntent().getStringExtra("newLName");
                String DOB =  regDateOfBirth.getText().toString();

                //Get Gender From Selected Radio button
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radioId);
                Gender = radioButton.getText().toString();

                Intent register4 = new Intent(getApplicationContext(),RegistrationActivity4.class);

                register4.putExtra("newUsername",newUsername);
                register4.putExtra("newEmail",newEmail);
                register4.putExtra("newFName",newFName);
                register4.putExtra("newLName",newLName);
                register4.putExtra("DOB",DOB);
                register4.putExtra("Gender",Gender);

                startActivity(register4);
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
