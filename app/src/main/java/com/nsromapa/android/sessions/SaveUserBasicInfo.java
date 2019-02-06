package com.nsromapa.android.sessions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.nsromapa.android.signup_login.LoginActivity;

import java.util.HashMap;

/**
 * Created by SAY on 18/08/2018 at Nsromapa Goaso.
 */

public class SaveUserBasicInfo {
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String  PREFERED_NAME = "SaveUserBasicInfo";

    public static final String KEY_F_NAME = "first_name";
    public static final String KEY_L_NAME = "last_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_PROFILE_PIC = "pf_pic";
    public static final String KEY_FRIENDS_ARRAY = "friends_array";
    public static final String KEY_ONLINE = "online";
    public static final String KEY_COVER_IMAGE = "cov_pic";


    //Constructor
    @SuppressLint("CommitPrefEdits")
    public SaveUserBasicInfo(Context context){
        this._context = context;
        pref=_context.getSharedPreferences(PREFERED_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }


    public void addInfo_Fname(String value){
        editor.putString(KEY_F_NAME,value);
        editor.commit();
    }

    public void addInfo_Lname(String value){
        editor.putString(KEY_L_NAME,value);
        editor.commit();
    }

    public void addInfo_Email(String value){
        editor.putString(KEY_EMAIL,value);
        editor.commit();
    }

    public void addInfo_Phone(String value){
        editor.putString(KEY_PHONE,value);
        editor.commit();
    }

    public void addInfo_Gender(String value){
        editor.putString(KEY_GENDER,value);
        editor.commit();
    }

    public void addInfo_Profile_Pic(String value){
        editor.putString(KEY_PROFILE_PIC,value);
        editor.commit();
    }

    public void addInfo_Friends_arry(String value){
        editor.putString(KEY_FRIENDS_ARRAY,value);
        editor.commit();
    }

    public void addInfo_Online(String value){
        editor.putString(KEY_ONLINE,value);
        editor.commit();
    }

    public void addInfo_Cover_image(String value){
        editor.putString(KEY_COVER_IMAGE,value);
        editor.commit();
    }



    public HashMap<String,String >getUserBasicInfo(){
        HashMap<String,String> userBasicInfo = new HashMap<>();

        userBasicInfo.put(KEY_F_NAME, pref.getString(KEY_F_NAME,null));
        userBasicInfo.put(KEY_L_NAME, pref.getString(KEY_L_NAME,null));
        userBasicInfo.put(KEY_EMAIL, pref.getString(KEY_EMAIL,null));
        userBasicInfo.put(KEY_PHONE, pref.getString(KEY_PHONE,null));
        userBasicInfo.put(KEY_GENDER, pref.getString(KEY_GENDER,null));
        userBasicInfo.put(KEY_PROFILE_PIC, pref.getString(KEY_PROFILE_PIC,null));
        userBasicInfo.put(KEY_FRIENDS_ARRAY, pref.getString(KEY_FRIENDS_ARRAY,null));
        userBasicInfo.put(KEY_ONLINE, pref.getString(KEY_ONLINE,null));
        userBasicInfo.put(KEY_COVER_IMAGE, pref.getString(KEY_COVER_IMAGE,null));


        return userBasicInfo;
    }


    public boolean clearBasicInfo(){

        //Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        return true;

    }
}
