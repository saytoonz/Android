package com.nsromapa.android.tab1pakage;

/**
 * Created by SAY on 26/08/2018 at Nsromapa Goaso.
 */
public class homeFeedList {
   private int postid;
   private String body;
   private String date_added;
   private String added_by;
   private  String added_by_id;
   private String adder_pfp;
   private String user_posted_to;
   private String user_posted_to_id;
   private String file_url;
   private String file_type;

    public homeFeedList(int postid, String body, String date_added, String added_by, String added_by_id,
                        String adder_pfp, String user_posted_to, String user_posted_to_id,
                        String file_url, String file_type) {
        this.postid = postid;
        this.body = body;
        this.date_added = date_added;
        this.added_by = added_by;
        this.added_by_id = added_by_id;
        this.adder_pfp = adder_pfp;
        this.user_posted_to = user_posted_to;
        this.user_posted_to_id = user_posted_to_id;
        this.file_url = file_url;
        this.file_type = file_type;
    }


    public int getPostid() {
        return postid;
    }

    public String getBody() {
        return body;
    }

    public String getDate_added() {
        return date_added;
    }

    public String getAdded_by() {
        return added_by;
    }

    public String getAdded_by_id() {
        return added_by_id;
    }

    public String getAdder_pfp() {
        return adder_pfp;
    }

    public String getUser_posted_to() {
        return user_posted_to;
    }

    public String getUser_posted_to_id() {
        return user_posted_to_id;
    }

    public String getFile_url() {
        return file_url;
    }

    public String getFile_type() {
        return file_type;
    }
}
