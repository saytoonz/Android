package com.nsromapa.android.tab3pakage;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nsromapa.android.R;
import com.nsromapa.android.backgroundtasks.DownloadImages;
import com.nsromapa.android.database.SQLiteHelper;
import com.nsromapa.android.sessions.SaveUserBasicInfo;
import com.nsromapa.android.sessions.UserSessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.nsromapa.android.database.SQLiteHelper.FRIENDS_INFO_TABLE;
import static com.nsromapa.android.sessions.SaveUserBasicInfo.KEY_FRIENDS_ARRAY;

/**
 * Created by SAY on 10/08/2018.
 */

public class Tab3Fragment extends Fragment {

    RecyclerView recyclerView;
    FriendsListAdapter adapter;

    List<FriendsList> friendsLists;
    UserSessionManager session;

    String savedFriend_array;

//    private static final String FRIENDS_URL = "https://nsromapa.000webhostapp.com/android/androidpostsangGets_general.php?action=userFriendsList&&uid=";
//    private final static String IMAGE_LINK = "https://nsromapa.000webhostapp.com/";
//
    private static final String FRIENDS_URL = "http://192.168.43.22/nsromapa2/android/androidpostsangGets_general.php?action=userFriendsList&&uid=";
    private final static String IMAGE_LINK = "http://192.168.43.22/nsromapa2/";


    public static SQLiteHelper sqLiteHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        setRetainInstance(true);
        friendsLists = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.FriendsListRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        session = new UserSessionManager(getContext());


        //Create SQLite Database
        sqLiteHelper = new SQLiteHelper(getContext());

        //Create Table 'fitn' for friends infos into the SQLite Database

//        sqLiteHelper.quaryData("DROP TABLE IF EXISTS "+FRIENDS_INFO_TABLE);
//
        sqLiteHelper.quaryData("CREATE TABLE IF NOT EXISTS " + FRIENDS_INFO_TABLE
                       + " (id INTEGER PRIMARY KEY AUTOINCREMENT, uid TEXT, fid TEXT, " +
                       "funame TEXT, fullname TEXT, fimg TEXT, fcov_img TEXT, fgend TEXT)");

        HashMap<String, String> userInfo = session.getUserDetails();
        final String uid = userInfo.get(UserSessionManager.KEY_USERID);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshFriends);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh, R.color.refresh1, R.color.refresh2);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFriendsFromOnline(uid);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

//        loadFriendsFromSQLite(uid);
        loadFriendsFromOnline(uid);


        return view;
    }


    private void loadFriendsFromSQLite(final String Session_uid) {
        savedFriend_array = new SaveUserBasicInfo(getActivity()).getUserBasicInfo().get(KEY_FRIENDS_ARRAY);

        //Get all data from SQLite Database
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM " + FRIENDS_INFO_TABLE + " WHERE uid=" + Session_uid);
        friendsLists.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String uid = cursor.getString(1);
            String fnd_id = cursor.getString(2);
            String fnd_username = cursor.getString(3);
            String fnd_name = cursor.getString(4);
            byte[] fnd_img = cursor.getBlob(5);
            byte[] fnd_CoverImg = cursor.getBlob(6);
            String fnd_gend = cursor.getString(7);

            FriendsList friendsList;
            friendsList = new FriendsList(fnd_id, fnd_username, fnd_name, null, fnd_img);

            friendsLists.add(friendsList);
        }

        adapter = new FriendsListAdapter(getContext(), friendsLists);
        recyclerView.setAdapter(adapter);

    }


    private void loadFriendsFromOnline(final String uid) {


        savedFriend_array = new SaveUserBasicInfo(getActivity()).getUserBasicInfo().get(KEY_FRIENDS_ARRAY);


        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET, FRIENDS_URL + uid + "&&Friends_array=" + savedFriend_array,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            try {
                                JSONArray friends = new JSONArray(response);

                                for (int i = 0; i < friends.length(); i++) {
                                    JSONObject friendObject = friends.getJSONObject(i);

                                    String fid = friendObject.getString("id");
                                    String fusername = friendObject.getString("username");
                                    String fulname = friendObject.getString("fulname");
                                    String fprofile_pic = friendObject.getString("profile_pic");
                                    String fcover_pic = friendObject.getString("cover_pic");
                                    String fgrn = friendObject.getString("gender");

                                    String f_array = friendObject.getString("f_array");
                                    new SaveUserBasicInfo(getActivity()).addInfo_Friends_arry(f_array);

                                    if (!fusername.equals("noFriend") || !fusername.equals("usernotFound")) {

                                        Cursor cursor = sqLiteHelper.getData("SELECT * FROM " + FRIENDS_INFO_TABLE +
                                                " WHERE uid=" + uid + " AND fid =" + fid);
                                        if (cursor.getCount() < 1) {

                                            FriendsList friendsList;
                                            friendsList = new FriendsList(fid, fusername, fulname, IMAGE_LINK + fprofile_pic, null);

                                            friendsLists.add(friendsList);

                                            try {
                                                sqLiteHelper.insertFriendsData(
                                                        uid,
                                                        fid,
                                                        fusername,
                                                        fulname,
                                                        imagetoByte(IMAGE_LINK + fprofile_pic),
                                                        imagetoByte(IMAGE_LINK + fcover_pic),
                                                        fgrn
                                                );

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }


                                    }
                                }


                                adapter = new FriendsListAdapter(getContext(), friendsLists);
                                recyclerView.setAdapter(adapter);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

    @Nullable
    private byte[] imagetoByte(String image_url) {



        try {
            if (new DownloadImages(image_url).execute().get() != null) {
                Bitmap bitmap = new DownloadImages(image_url).execute().get();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                return stream.toByteArray();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return null;

//        try {
//
//            Bitmap bitmap = Picasso.get().load(image_url).get();
//
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//
//            return stream.toByteArray();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        return null;
    }
}
