package com.nsromapa.android.friendsprofile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nsromapa.android.R;

/**
 * Created by SAY on 25/08/2018 at Nsromapa Goaso.
 */
public class FriendProfileTab2 extends Fragment {
    View view;
    String fid;

    public FriendProfileTab2() {
      //  this.fid = fid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_friend_profile2,container,false);


        return view;
    }

}
