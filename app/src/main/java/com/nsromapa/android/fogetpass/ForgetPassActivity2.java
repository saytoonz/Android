package com.nsromapa.android.fogetpass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nsromapa.android.R;
import com.nsromapa.android.backgroundtasks.BackgroundTask;
import com.nsromapa.android.backgroundtasks.DownloadImages;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

public class ForgetPassActivity2 extends AppCompatActivity {

    ImageView userImage;
    TextView fullNmae, lilNote;
    Button ForgNotMyAC;
    LinearLayout sendForgVerif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass2);

        userImage = (ImageView) findViewById(R.id.userImage);
        fullNmae = (TextView) findViewById(R.id.fullNmae);
        lilNote = (TextView) findViewById(R.id.lilNote);
        ForgNotMyAC = (Button) findViewById(R.id.ForgNotMyAC);
        sendForgVerif = (LinearLayout) findViewById(R.id.sendForgVerif);

        final String imagelink = getIntent().getStringExtra("imageLink");
        String fulname = getIntent().getStringExtra("full_name");

        fullNmae.setText(fulname);
        try {
            if (new DownloadImages(imagelink).execute().get()!=null)
            userImage.setImageBitmap(new DownloadImages(imagelink).execute().get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        sendForgVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getIntent().getStringExtra("email");

                BackgroundTask backgroundTask = new BackgroundTask(getApplicationContext());
                backgroundTask.execute("Send_Verification_code_for_Forget_Password", email);
            }
        });

    }


}
