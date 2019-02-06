package com.nsromapa.android.viewers;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.nsromapa.android.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class PostImageViewer extends AppCompatActivity {

    PhotoView photoView;
    LinearLayout bottomActions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_image_viewer);

        String image_Loc = getIntent().getStringExtra("image_Loc");
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
        photoView = (PhotoView) findViewById(R.id.imageView_photo_view);
        Picasso.get()
                .load(image_Loc)
                .placeholder(R.drawable.load)
                .error(R.drawable.errorimg)
                .into(photoView);


        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionBar.isShowing()) {
                    actionBar.hide();
                    bottomActions.setVisibility(View.GONE);
//                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                } else {
                    actionBar.show();
                    bottomActions.setVisibility(View.VISIBLE);
//                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
            }
        });

    }






    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_imageviewer_menu,menu);
        return true;
    }
    
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_save){
           saveImage();
        }
        else if(id==R.id.action_share){
            shareImage();
        }
        else if(id==R.id.action_set_as_wallpaper){
            setwallpaper();

        }

        return super.onOptionsItemSelected(item);
    }






    public static Bitmap viewToBitmap(View view, int width, int height){

        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }


    private void saveImage() {
        FileOutputStream fileOutputStream;
        File file = getExternalDirectory_andFolder();

        if (!file.exists() && !file.mkdirs()){
            Toast.makeText(this, "Can't create Directory to save image", Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmsshhmmss");
        String date = simpleDateFormat.format(new Date());
        String imgName = "Nsro_" +date+".jpg";
        String file_name = file.getAbsolutePath()+"/"+imgName;
        File new_file = new File(file_name);

        try {

            fileOutputStream = new FileOutputStream(new_file);
            Bitmap bitmap = viewToBitmap(photoView, photoView.getWidth(),photoView.getHeight());
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);

            Toast.makeText(this,"Image Saved Successfully",Toast.LENGTH_LONG).show();

            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        refreshingGallery(new_file);

    }

    private void refreshingGallery(File new_file) {
        Intent intent= new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(new_file));
        sendBroadcast(intent);
    }

    private File getExternalDirectory_andFolder(){
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(file,"Nsromapa");
    }








    private void shareImage() {
       Bitmap bitmap = viewToBitmap(photoView,photoView.getWidth(),photoView.getHeight());
       Intent shareIntent= new Intent(Intent.ACTION_SEND);
       shareIntent.setType("image/jpeg");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        File file=new File(Environment.getExternalStorageDirectory()+File.separator+"Nsro_share.jpg");
        try {
            file.createNewFile();
            FileOutputStream fileInputStream = new FileOutputStream(file);
            fileInputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.parse("file:///sdcard/Nsro_share.jpg"));
        startActivity(Intent.createChooser(shareIntent,"Share Image"));


    }






    private void setwallpaper() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setBitmap(viewToBitmap(photoView, photoView.getWidth(),photoView.getHeight()));
            Toast.makeText(this,"Wallpaper applied to home screen.",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"Sorry there was an error.",Toast.LENGTH_SHORT).show();
        }
    }






}
