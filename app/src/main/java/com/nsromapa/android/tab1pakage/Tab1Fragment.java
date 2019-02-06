package com.nsromapa.android.tab1pakage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nsromapa.android.R;
import com.nsromapa.android.sessions.UserSessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SAY on 10/08/2018.
 */

public class Tab1Fragment extends Fragment {

    UserSessionManager session;

    List<homeFeedList> feedLists;
    RecyclerView recyclerView;


//    private static final String FRIENDS_URL = "https://nsromapa.000webhostapp.com/android/feeds/userhomepost.php";
//    private final static String IMAGE_LINK = "https://nsromapa.000webhostapp.com/";


    private static final String FRIENDS_URL = "http://192.168.43.22/nsromapa2/android/feeds/userhomepost.php";
    private final static String IMAGE_LINK = "http://192.168.43.22/nsromapa2/";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        session = new UserSessionManager(getContext());

        feedLists = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.homePostRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        HashMap<String, String> user = session.getUserDetails();
        String uid = user.get(UserSessionManager.KEY_USERID);



        loadFeeds();

//
//             Snackbar.make(view, "User Login Status "+session.isUserLoggedIn()+" mail: "+uid, Snackbar.LENGTH_LONG)
//                     .setAction("Action", null).show();


        return view;
    }





    private void loadFeeds(){

        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET, FRIENDS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            try {
                                JSONArray friends = new JSONArray(response);

                                for (int i = 0; i < friends.length(); i++) {
                                    JSONObject friendObject = friends.getJSONObject(i);

                                    int postid = friendObject.getInt("postid");
                                    String body = friendObject.getString("body");
                                    String date_added = friendObject.getString("date_added");
                                    String added_by = friendObject.getString("added_by");
                                    String added_by_id = friendObject.getString("added_by_id");
                                    String adder_pfp = friendObject.getString("adder_pfp");
                                    String user_posted_to = friendObject.getString("user_posted_to");
                                    String user_posted_to_id = friendObject.getString("user_posted_to_id");
                                    String file_url = friendObject.getString("file_url");
                                    String file_type = friendObject.getString("file_type");


                                    homeFeedList eachfeed = new homeFeedList(
                                            postid,
                                            body,
                                            date_added,
                                            added_by,
                                            added_by_id,
                                            IMAGE_LINK +adder_pfp,
                                            user_posted_to,
                                            user_posted_to_id,
                                            IMAGE_LINK + file_url,
                                            file_type
                                    );

                                    feedLists.add(eachfeed);


                                }


                                homeFeedListAdapter adapter = new homeFeedListAdapter(getContext(), feedLists);
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


}
