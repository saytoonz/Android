package com.nsromapa.android.sessions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.nsromapa.android.runners.CheckNetwork;
import com.nsromapa.android.signup_login.LoginActivity;

import java.util.HashMap;



/**
 * Created by SAY on 10/08/2018.
 */

public class UserSessionManager {
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String  PREFERED_NAME = "UserLoginSession";

    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public static final String KEY_NAME = "name";

    public static final String KEY_USERID = "email";


    //Constructor
    public UserSessionManager(Context context){
        this._context = context;
        pref=_context.getSharedPreferences(PREFERED_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }



    //Create login session
    public void createUserLoginSession(String name, String email){
        //Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        //Storing name in pref
        editor.putString(KEY_NAME, name);

        //Storing name in email
        editor.putString(KEY_USERID, email);

        //Commit Changes
        editor.commit();
    }



    public boolean checkLogin(){
        //Check login status
        if (this.isUserLoggedIn()){

            return true;

        }else{

            return false;

        }
    }



    public HashMap<String,String>getUserDetails(){

        //User hashmap to store user credentials
        HashMap<String, String >user = new HashMap<>();

        //username
        user.put(KEY_NAME, pref.getString(KEY_NAME,null));

        //user email id
        user.put(KEY_USERID, pref.getString(KEY_USERID,null));

        return user;
    }





    //Clear session when there is logout
    public boolean logoutUser(){

        //Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        //Redirect user to login page
        Intent intent = new Intent(_context,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(intent);

        return true;

    }

    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN,false);
    }
}
