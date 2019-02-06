package com.nsromapa.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by SAY on 18/08/2018 at Nsromapa Goaso.
 */

public class SQLiteHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME ="cracker.db";
    public static final String FRIENDS_INFO_TABLE ="fitn";
    public static final String USER_INFO_TABLE = "sty";



    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    public void quaryData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }


    public void insertUserData(String uid, String username, String first_name, String last_name,
                               String email , String phone, byte[] profile_pic, byte[] cover_image,
                               String gender, String friends_array){

        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO "+USER_INFO_TABLE+" VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,uid);
        statement.bindString(2,username);
        statement.bindString(3,first_name);
        statement.bindString(4,last_name);
        statement.bindString(5,email);
        statement.bindString(6,phone);
        statement.bindBlob(7,profile_pic);
        statement.bindBlob(8,cover_image);
        statement.bindString(9,gender);
        statement.bindString(10,friends_array);

        statement.executeInsert();

    }


    public void updateUserData(String uid, String username, String first_name, String last_name,
                               String email , String phone, byte[] profile_pic, byte[] cover_image,
                               String gender, String friends_array){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mid",uid);
        contentValues.put("muname",username);
        contentValues.put("mfname",first_name);
        contentValues.put("mlname",last_name);
        contentValues.put("mem",email);
        contentValues.put("mph",phone);
        contentValues.put("mpfp",profile_pic);
        contentValues.put("mcvp",cover_image);
        contentValues.put("mgen",gender);
        contentValues.put("mf_array",friends_array);

        database.update(USER_INFO_TABLE,contentValues,"mid=?",new String[]{uid});


    }






    public void insertFriendsData(String  uid, String fnd_id, String fnd_username, String fnd_name,
                                  byte[] fnd_img, byte[] fnd_cov_img, String fnd_gend){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO "+FRIENDS_INFO_TABLE+" VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,uid);
        statement.bindString(2,fnd_id);
        statement.bindString(3,fnd_username);
        statement.bindString(4,fnd_name);
        statement.bindBlob(5,fnd_img);
        statement.bindBlob(6,fnd_cov_img);
        statement.bindString(7,fnd_gend);

        statement.executeInsert();

    }







    public Cursor getData(String sql){
        SQLiteDatabase database = getWritableDatabase();

        return database.rawQuery(sql, null);
    }






    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

