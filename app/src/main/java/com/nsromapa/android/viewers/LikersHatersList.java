package com.nsromapa.android.viewers;

/**
 * Created by SAY on 20/08/2018 at Nsromapa Goaso.
 */

public class LikersHatersList {
    private String id;
    private String username;
    private String fullname;
    private String image;
    private byte[] locImage;


    public LikersHatersList(String id, String username, String fullname, String image, byte[] locImage) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.image = image;
        this.locImage = locImage;
    }


    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }


    public String getFullname() {
        return fullname;
    }

    public String getImage() {
        return image;
    }

    public byte[] getLocImage(){
        return locImage;
    }
}
