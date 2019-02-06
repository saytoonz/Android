package com.nsromapa.android.friendsprofile;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.nsromapa.android.R;
import com.nsromapa.android.database.SQLiteHelper;
import com.nsromapa.android.sessions.SaveUserBasicInfo;
import com.nsromapa.android.sessions.UserSessionManager;

import java.util.HashMap;

import static com.nsromapa.android.database.SQLiteHelper.FRIENDS_INFO_TABLE;

public class FriendProfile extends AppCompatActivity {

    UserSessionManager sessionManager;
    SaveUserBasicInfo userBasicInfo;
    SQLiteHelper sqLiteHelper;

    TextView Friend_full_name;
    ImageView profilepic,coverpic;

    String user_id, username;
    String fid;

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProfileAppbar);
        setSupportActionBar(toolbar);

        appBarLayout = (AppBarLayout)findViewById(R.id.FriendProfileAppbar);
        tabLayout = (TabLayout)findViewById(R.id.tabsInFriendsProfile);
        viewPager = (ViewPager) findViewById(R.id.viewPagerInFriendsProfile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FriendProfileViewPager adapter = new FriendProfileViewPager(getSupportFragmentManager());
            //Adding Fragments
        adapter.AddFragment(new FriendProfileTab1(),"Posts");
        adapter.AddFragment(new FriendProfileTab2(),"Media");
        adapter.AddFragment(new FriendProfileTab3(),"Relations");
        adapter.AddFragment(new FriendProfileTab4(),"About");
        //Settin up Adapter
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);






       fid = getIntent().getStringExtra("fid");

        sessionManager = new UserSessionManager(this);
        userBasicInfo = new SaveUserBasicInfo(this);
        sqLiteHelper = new SQLiteHelper(this);

        HashMap<String,String > userSession = sessionManager.getUserDetails();
        user_id = userSession.get(UserSessionManager.KEY_USERID);
        username = userSession.get(UserSessionManager.KEY_NAME);

        Friend_full_name = (TextView)findViewById(R.id.friendFullNameInFriendsProfile);
        profilepic = (ImageView)findViewById(R.id.friendProfilePicInFriendsProfile);
        coverpic = (ImageView)findViewById(R.id.friendCoverPicInFriendsProfile);

        Cursor cursor = sqLiteHelper.getData("SELECT * FROM "+FRIENDS_INFO_TABLE+ " WHERE uid=" + user_id+" AND fid="+fid);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String uid = cursor.getString(1);
                String fnd_id = cursor.getString(2);
                String fnd_username = cursor.getString(3);
                String fnd_name = cursor.getString(4);
                byte[] fnd_img = cursor.getBlob(5);
                byte[] fnd_CoverImg = cursor.getBlob(6);
                String fnd_gend = cursor.getString(7);

                Bitmap profileImg = BitmapFactory.decodeByteArray(fnd_img,0,fnd_img.length);
                Bitmap coverImg = BitmapFactory.decodeByteArray(fnd_CoverImg,0,fnd_CoverImg.length);

                Friend_full_name.setText(fnd_name);
                profilepic.setImageBitmap(profileImg);
                coverpic.setImageBitmap(coverImg);

                getSupportActionBar().setTitle("::"+fnd_username);
            }
        }else{

        }




    }
}
