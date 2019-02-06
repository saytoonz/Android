package com.nsromapa.android.viewers;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.nsromapa.android.R;

public class PostVideoViewer extends AppCompatActivity {

    LinearLayout bottomActions;
    VideoView videoView;
    ProgressDialog mDialog;
    ImageButton play_pauseBut;
    SeekBar seekBar;
    TextView currentDuation,totalDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_video_viewer);

       final String video_Loc = getIntent().getStringExtra("video_Loc");
        String UserFrom = getIntent().getStringExtra("UserFrom");
        String UserTo = getIntent().getStringExtra("UserTo");
        String PostId = getIntent().getStringExtra("PostId");
        String PostTime = getIntent().getStringExtra("PostTime");


        Toolbar toolbar = (Toolbar) findViewById(R.id.imageViewToolbar);
        setSupportActionBar(toolbar);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;

        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setLogo(R.drawable.defaultpicfemale);
//        actionBar.setDisplayUseLogoEnabled(true);

        actionBar.setElevation(0);
        actionBar.setTitle(UserFrom);
        actionBar.setSubtitle(PostTime);


        bottomActions = (LinearLayout) findViewById(R.id.PostImageViewerActionButtons);
        videoView = (VideoView) findViewById(R.id.videoView_videoView);
        play_pauseBut = (ImageButton)findViewById(R.id.play_pauseBut);

        seekBar = (SeekBar) findViewById(R.id.videoView_seekbar);
        currentDuation = (TextView)findViewById(R.id.videoView_currentDuratin);
        totalDuration = (TextView)findViewById(R.id.videoView_TotalDuratin);


        play_pauseBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoAction();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int seekTo = (videoView.getDuration() * (progress/1000));
                videoView.seekTo(seekTo);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionBar.isShowing()) {
                    actionBar.hide();
                    bottomActions.setVisibility(View.GONE);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                } else {
                    actionBar.show();
                    bottomActions.setVisibility(View.VISIBLE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
            }
        });


        mDialog = new ProgressDialog(this);
      //  mDialog.setMessage("Please wait...");
     //   mDialog.setCancelable(false);
        mDialog.show();


        Uri uri = Uri.parse(video_Loc);
        videoView.setVideoURI(uri);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play_pauseBut.setImageResource(R.drawable.ic_pause_);
            }


        });

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onPrepared(MediaPlayer mp) {
                mDialog.dismiss();
                mp.setLooping(false);
                videoView.start();

                totalDuration.setText(" "+(videoView.getDuration()/1000));
                currentDuation.setText(videoView.getCurrentPosition()+" /");

                play_pauseBut.setImageResource(R.drawable.ic_pause_);

            }
        });






//        mediaController = new MediaController(this);
////        mediaController.setAnchorView(videoView);
////        videoView.setMediaController(mediaController);







    }


    public void videoAction(){

            if (!videoView.isPlaying()) {
                videoView.start();
                play_pauseBut.setImageResource(R.drawable.ic_pause_);

            }else{
                videoView.pause();
                play_pauseBut.setImageResource(R.drawable.ic_play_arrow);
            }
    }
}
