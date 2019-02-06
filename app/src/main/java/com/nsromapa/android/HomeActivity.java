package com.nsromapa.android;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nsromapa.android.backgroundtasks.DownloadImages;
import com.nsromapa.android.database.SQLiteHelper;
import com.nsromapa.android.sessions.SaveUserBasicInfo;
import com.nsromapa.android.sessions.UserSessionManager;
import com.nsromapa.android.tabs.Pager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static com.nsromapa.android.database.SQLiteHelper.FRIENDS_INFO_TABLE;
import static com.nsromapa.android.database.SQLiteHelper.USER_INFO_TABLE;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    UserSessionManager userSessionManager;
    SaveUserBasicInfo saveUserBasicInfo;

    private ViewPager mViewPager;

    private TabLayout mTabLayout;
    DrawerLayout drawer;
    NavigationView navigationView;

    FloatingActionButton fab_plus, fab_twitter, fab_fb, fab_facebook;
    Animation fabOpen, fabClose, fabRClwse, fabRAnti;
    boolean isOpen = false;

    TextView DrawerUsername, DrawerFullname;
    ImageView DrawerProfile;

    SQLiteHelper mydb;

    String first_name, last_name, f_name, l_name, innerProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mydb = new SQLiteHelper(this);


//        mydb.quaryData("DROP TABLE IF EXISTS "+USER_INFO_TABLE);
//        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        mydb.quaryData("CREATE TABLE IF NOT EXISTS " + USER_INFO_TABLE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, mid TEXT, muname TEXT, mfname TEXT, mlname TEXT,"
                + " mem TEXT, mph TEXT, mpfp TEXT,mcvp TEXT, mgen TEXT, mf_array TEXT)");

        userSessionManager = new UserSessionManager(HomeActivity.this);
        saveUserBasicInfo = new SaveUserBasicInfo(HomeActivity.this);

        HashMap<String, String> userInfos = userSessionManager.getUserDetails();

        String myUserId = userInfos.get(UserSessionManager.KEY_USERID);
        String myUsername = userInfos.get(UserSessionManager.KEY_NAME);

        f_name = saveUserBasicInfo.getUserBasicInfo().get(SaveUserBasicInfo.KEY_F_NAME);
        l_name = saveUserBasicInfo.getUserBasicInfo().get(SaveUserBasicInfo.KEY_L_NAME);
        innerProfilePic = saveUserBasicInfo.getUserBasicInfo().get(SaveUserBasicInfo.KEY_PROFILE_PIC);


        if (f_name != null)
            first_name = f_name;
        else
            first_name = " ";

        if (l_name != null)
            last_name = l_name;
        else
            last_name = " ";


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_open);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        View navHeader = navigationView.getHeaderView(0);

        DrawerUsername = (TextView) navHeader.findViewById(R.id.DrawerUserUsername);
        DrawerFullname = (TextView) navHeader.findViewById(R.id.DrawerUserFullName);
        DrawerProfile = (ImageView) navHeader.findViewById(R.id.DrawerUserProfilePic);

        DrawerUsername.setText("::" + myUsername);
        DrawerFullname.setText(first_name + " " + last_name);



        Cursor cursor = mydb.getData("SELECT * FROM " + USER_INFO_TABLE + " WHERE mid=" + myUserId);
        if (cursor.getCount() == 0) {
            Picasso.get().load(innerProfilePic).into(DrawerProfile);


        } else {
            Picasso.get().load(innerProfilePic).into(DrawerProfile);
//             while (cursor.moveToNext()){
//                 byte[] pfp_img = cursor.getBlob(7);
//                 byte[] cov_img = cursor.getBlob(8);
//
//                 Bitmap bitmap = BitmapFactory.decodeByteArray(pfp_img,0,pfp_img.length);
//                 DrawerProfile.setImageBitmap(bitmap);
//             }

        }


        //tab id
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        //Add the tabs
        mTabLayout.addTab(mTabLayout.newTab().setText(""));
        mTabLayout.addTab(mTabLayout.newTab().setText(""));
        mTabLayout.addTab(mTabLayout.newTab().setText(""));
        mTabLayout.addTab(mTabLayout.newTab().setText(""));
        mTabLayout.addTab(mTabLayout.newTab().setText(""));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Set Ocons on tabs
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_chat);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_group_white);
        mTabLayout.getTabAt(3).setIcon(R.drawable.ic_search);
        mTabLayout.getTabAt(4).setIcon(R.drawable.ic_notifications);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setElevation(0);



        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        Pager adapter = new Pager(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setScrollPosition(position, 0, true);
                mTabLayout.setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        ////////////////////////Dealing with the Floating Buttons /////////////////////////
        fab_plus = (FloatingActionButton) findViewById(R.id.fab_plus);
        fab_twitter = (FloatingActionButton) findViewById(R.id.fab_twitter);
        fab_facebook = (FloatingActionButton) findViewById(R.id.fab_facebook);
        fab_fb = (FloatingActionButton) findViewById(R.id.fab_fb);

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fabRClwse = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        fabRAnti = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);


        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    //Animate the fab buttons to hide them
                    fab_facebook.startAnimation(fabClose);
                    fab_fb.startAnimation(fabClose);
                    fab_twitter.startAnimation(fabClose);
                    fab_plus.startAnimation(fabRAnti);

                    //set buttons to be unclickable
                    fab_fb.setClickable(false);
                    fab_facebook.setClickable(false);
                    fab_twitter.setClickable(false);
                    isOpen = false;

                } else {
                    //Animate the fab buttons to show them
                    fab_facebook.startAnimation(fabOpen);
                    fab_fb.startAnimation(fabOpen);
                    fab_twitter.startAnimation(fabOpen);
                    fab_plus.startAnimation(fabRClwse);

                    //allow buttons to be clickable
                    fab_fb.setClickable(true);
                    fab_facebook.setClickable(true);
                    fab_twitter.setClickable(true);
                    isOpen = true;

                }
            }
        });

        new userExist(myUserId).execute();


    }


    @Nullable
    public byte[] imagetoByte(String image_url) {


        try {
            if (new DownloadImages(image_url).execute().get() != null) {
                Bitmap bitmap = new DownloadImages(image_url).execute().get();

//                try {
//                    Bitmap bitmap1 = Picasso.get().load(image_url).get();


                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                return stream.toByteArray();

//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        Bitmap bitmap = Picasso.get().load(image_url).get();


        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


    //////Clicking on the menus of the on the action bar drawer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_logout) {
            if (saveUserBasicInfo.clearBasicInfo())
                if (userSessionManager.logoutUser())
                    finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //////Clicking on the menus of the navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MyTimeline:
                //Open User Own Timeline intent
                Toast.makeText(this, "My Time Line", Toast.LENGTH_SHORT).show();
                break;

            case R.id.MyFriends:
                //Open User Own Friends intent
                new Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                mTabLayout.getTabAt(2).select();
                            }
                        }, 100);

                break;

            case R.id.MyLikes:
                //Open User Own Likes intent
                break;
            case R.id.MyLiked:
                //Open User Own Liked intent
                break;
            case R.id.MyOwner:
                //Open User Own Pet Owner Info intent
                break;
            case R.id.MyPets:
                //Open User Own Pets intent
                break;
            case R.id.MyAbout:
                //Open About the user Info Intent
                break;
            case R.id.MyPhotos:
                //Open User Photos intent
                break;
            case R.id.MyAudios:
                //Open User Audios intent
                break;
            case R.id.MyVideo:
                //Open User Videos intent
                break;
            case R.id.Schedule:
                //Open Schedule  Intent
                break;


        }

        drawer.closeDrawer(GravityCompat.START);
        return false;
    }


    //////Clicking on the Back Key
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //////Check if user exists
    private class userExist extends AsyncTask<Void, Void, String> {

        String ImageUrl = "https://nsromapa.000webhostapp.com/";
        String Url = "https://nsromapa.000webhostapp.com/android/androidPresentUserInfo.php?action=userExist";


//        String ImageUrl = "http://192.168.43.22/nsromapa2/";
//        String Url = "http://192.168.43.22/nsromapa2/android/androidPresentUserInfo.php?action=userExist";


        String uid;

        userExist(String uid) {
            this.uid = uid;
        }


        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(Url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(5000);
                Log.d("Test", "Test 3 pass");

                return stringBuilder.toString().trim();


            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
                return null;
            }

        }


        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);


            if (json != null) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject JO = jsonObject.getJSONObject("server_response");

                    String ResponseCode = JO.getString("ResponseCode");
                    String message = JO.getString("message");
                    String fname = JO.getString("fname");
                    String lname = JO.getString("lname");
                    String mail = JO.getString("mail");
                    String phone = JO.getString("phone");
                    String gender = JO.getString("gender");
                    String pf_pic = JO.getString("pf_pic");
                    String friend_array = JO.getString("friend_array");
                    String online = JO.getString("online");
                    final String cov_pic = JO.getString("cov_pic");


                    String savedFname = saveUserBasicInfo.getUserBasicInfo()
                            .get(SaveUserBasicInfo.KEY_F_NAME);

                    String savedLname = saveUserBasicInfo.getUserBasicInfo()
                            .get(SaveUserBasicInfo.KEY_L_NAME);

                    String savedMail = saveUserBasicInfo.getUserBasicInfo()
                            .get(SaveUserBasicInfo.KEY_EMAIL);

                    String savedPhone = saveUserBasicInfo.getUserBasicInfo()
                            .get(SaveUserBasicInfo.KEY_PHONE);

                    String savedGender = saveUserBasicInfo.getUserBasicInfo()
                            .get(SaveUserBasicInfo.KEY_GENDER);

                    String savedPf_pic = saveUserBasicInfo.getUserBasicInfo()
                            .get(SaveUserBasicInfo.KEY_PROFILE_PIC);

                    String savedFriend_array = saveUserBasicInfo.getUserBasicInfo()
                            .get(SaveUserBasicInfo.KEY_FRIENDS_ARRAY);

                    String savedOnline = saveUserBasicInfo.getUserBasicInfo()
                            .get(SaveUserBasicInfo.KEY_ONLINE);

                    String savedCov_pic = saveUserBasicInfo.getUserBasicInfo()
                            .get(SaveUserBasicInfo.KEY_COVER_IMAGE);


                    if (!fname.equals(savedFname) || !lname.equals(savedLname)) {
                        saveUserBasicInfo.addInfo_Fname(fname);
                        saveUserBasicInfo.addInfo_Lname(lname);

                        DrawerFullname.setText(fname + " " + lname);
                    }


                    if (!(ImageUrl + pf_pic).equals(savedPf_pic)) {
                        saveUserBasicInfo.addInfo_Profile_Pic(ImageUrl + pf_pic);

                        Picasso.get().load(ImageUrl + pf_pic).into(DrawerProfile);

                    }


                    if (!(ImageUrl + cov_pic).equals(savedCov_pic)) {
                        final View navHeader = navigationView.getHeaderView(0);
                        saveUserBasicInfo.addInfo_Cover_image(ImageUrl + cov_pic);

                        Picasso.get()
                                .load(ImageUrl + cov_pic)
                                .into(new Target() {
                                    @Override
                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                        navHeader.setBackground(new BitmapDrawable(bitmap));
                                    }

                                    @Override
                                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                    }

                                    @Override
                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                    }
                                });
                    }
                    HashMap<String, String> userInfos = userSessionManager.getUserDetails();

                    String myUserId = userInfos.get(UserSessionManager.KEY_USERID);
                    String myUsername = userInfos.get(UserSessionManager.KEY_NAME);


                    Cursor cursor = mydb.getData("SELECT * FROM " + USER_INFO_TABLE + " WHERE mid=" + myUserId);

                    if (cursor.getCount() < 1) {

                        try {
                            mydb.insertUserData(
                                    myUserId,
                                    myUsername,
                                    fname,
                                    lname,
                                    mail,
                                    phone,
                                    imagetoByte(ImageUrl + pf_pic),
                                    imagetoByte(ImageUrl + cov_pic),
                                    gender,
                                    friend_array
                            );

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    } else {
                        try {
                            mydb.updateUserData(
                                    myUserId,
                                    myUsername,
                                    fname,
                                    lname,
                                    mail,
                                    phone,
                                    imagetoByte(ImageUrl + pf_pic),
                                    imagetoByte(ImageUrl + cov_pic),
                                    gender,
                                    friend_array
                            );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }


    }


}
