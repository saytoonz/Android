package com.nsromapa.android.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.nsromapa.android.R;
import com.nsromapa.android.sessions.UserSessionManager;

import java.util.HashMap;

/**
 * Created by SAY on 10/08/2018.
 */

public class Tab1Fragment extends Fragment {

    UserSessionManager session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        session = new UserSessionManager(getContext());



//            HashMap<String,String > user=session.getUserDetails();
//            //get email
//            String email = user.get(UserSessionManager.KEY_USERID);
//
//
//             Snackbar.make(view, "User Login Status "+session.isUserLoggedIn()+" mail: "+email, Snackbar.LENGTH_LONG)
//                     .setAction("Action", null).show();


        return view;
    }
}
