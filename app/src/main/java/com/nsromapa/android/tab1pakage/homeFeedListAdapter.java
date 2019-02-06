package com.nsromapa.android.tab1pakage;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.nsromapa.android.R;
import com.nsromapa.android.friendsprofile.FriendProfile;
import com.nsromapa.android.sessions.UserSessionManager;
import com.nsromapa.android.viewers.LikersHaters;
import com.nsromapa.android.viewers.PostImageViewer;
import com.nsromapa.android.viewers.PostVideoViewer;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by SAY on 26/08/2018 at Nsromapa Goaso.
 */
public class homeFeedListAdapter extends RecyclerView.Adapter<homeFeedListAdapter.postViewHOlder> {

    UserSessionManager userSessionManager;
    Context ctx;
    List<homeFeedList> postsList;

    public homeFeedListAdapter(Context ctx, List<homeFeedList> postsList) {
        this.ctx = ctx;
        this.postsList = postsList;
    }


    @Override
    public postViewHOlder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.fragment1_recyclerview_list,null);

        return new postViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(final postViewHOlder holder, int position) {
        final homeFeedList feedList = postsList.get(position);


        holder.UserFrom.setText(feedList.getAdded_by());
        holder.UserTo.setText(feedList.getUser_posted_to());

        holder.UserTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, FriendProfile.class);
                intent.putExtra(feedList.getUser_posted_to_id(),"fid");
                ctx.startActivity(intent);
            }
        });






        ////////////Toggling the Like and the unlike buttons when it is clicked
        holder.likeButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              final MediaPlayer likeSound = MediaPlayer.create(ctx, R.raw.like_aud);
//                likeSound.start();

//                //Toggler like buttons
                holder.likeButt.setVisibility(View.GONE);
                holder.unlikeButt.setVisibility(View.VISIBLE);

                //Update likers
                int likes = Integer.parseInt(holder.likers.getText().toString());
                int nlikes = likes++;
                holder.likers.setText(nlikes);
                Toast.makeText(ctx,"Like "+feedList.getPostid(),Toast.LENGTH_SHORT).show();
            }
        });


        holder.unlikeButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toggler like buttons
                holder.unlikeButt.setVisibility(View.GONE);
                holder.likeButt.setVisibility(View.VISIBLE);

                //Update likers
                int likes = Integer.parseInt(holder.likers.getText().toString());
                holder.likers.setText(likes-1);
                Toast.makeText(ctx,"Unlike "+feedList.getPostid(),Toast.LENGTH_SHORT).show();
            }
        });







        holder.likers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent intent = new Intent(ctx, LikersHaters.class);

                        intent.putExtra("open","openlikers.php?");

                        ctx.startActivity(intent);


                Toast.makeText(ctx, holder.likers.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });




        holder.haters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ctx, LikersHaters.class);

                intent.putExtra("open","openhaters.php?");

                ctx.startActivity(intent);
                Toast.makeText(ctx, holder.haters.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });







        holder.commenters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, holder.commenters.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });










        holder.commentButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"Comment On "+feedList.getPostid(),Toast.LENGTH_SHORT).show();
            }
        });

        holder.hateButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"Hate this post with Post id "+feedList.getPostid(),Toast.LENGTH_SHORT).show();
            }
        });





        //Where to Display Delete Post Button
        if (feedList.getUser_posted_to_id().equals(holder.loginUser)
                || feedList.getAdded_by_id().equals(holder.loginUser))

            holder.delButt.setVisibility(View.VISIBLE);
        else
            holder.delButt.setVisibility(View.GONE);


        holder.delButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"Delete the post with this id "+feedList.getPostid(),Toast.LENGTH_SHORT).show();
            }
        });







        /////////// Download posted user dp ////////////////
            Picasso.get()
                    .load(feedList.getAdder_pfp())
                    .placeholder(R.drawable.defaultpicfemale)
                    .error(R.drawable.defaultpicmale)
                    .resize(60,60)
                    .centerCrop()
                    .into(holder.senderPfp);







        switch (feedList.getFile_type()) {
            case "imageFile":

                holder.postWebview.setVisibility(View.GONE); //Hide Web View


                //Load image from URL
                Picasso.get()
                        .load(feedList.getFile_url())
                        .placeholder(R.drawable.load)
                        .error(R.drawable.errorimg)
                        .into(holder.postImage);


                holder.postImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent imgViewer = new Intent(ctx, PostImageViewer.class);
                        imgViewer.putExtra("image_Loc", feedList.getFile_url());
                        imgViewer.putExtra("UserFrom", feedList.getAdded_by());
                        imgViewer.putExtra("UserTo", feedList.getUser_posted_to());
                        imgViewer.putExtra("PostId", feedList.getPostid());
                        imgViewer.putExtra("PostTime", feedList.getDate_added());
                        ctx.startActivity(imgViewer);
                    }
                });

                break;
            case "videoFile":

                holder.postWebview.setVisibility(View.GONE); // Hide Web View


                holder.postImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent vidViewer = new Intent(ctx, PostVideoViewer.class);
                        vidViewer.putExtra("video_Loc", feedList.getFile_url());
                        vidViewer.putExtra("UserFrom", feedList.getAdded_by());
                        vidViewer.putExtra("UserTo", feedList.getUser_posted_to());
                        vidViewer.putExtra("PostId", feedList.getPostid());
                        vidViewer.putExtra("PostTime", feedList.getDate_added());
                        ctx.startActivity(vidViewer);
                    }
                });

                break;
            default:

                //   holder.postVideo.setVisibility(View.GONE); //Hide Video View
                holder.postImage.setVisibility(View.GONE); // Hide Image View

                break;
        }


    }












    @Override
    public int getItemCount() {
        return postsList.size();
    }












    class postViewHOlder extends RecyclerView.ViewHolder{

        String loginUser;

        ImageView senderPfp,postImage;
        WebView postWebview;
        TextView UserFrom,UserTo,likers,haters,commenters;
       // VideoView postVideo;

        ImageButton likeButt, unlikeButt,commentButt,hateButt,delButt;

        private postViewHOlder(View itemView) {
            super(itemView);

            userSessionManager= new UserSessionManager(ctx);

            loginUser = userSessionManager.getUserDetails().get(UserSessionManager.KEY_USERID);


            senderPfp = (ImageView)itemView.findViewById(R.id.homePostUserFromPFP);
            UserFrom = (TextView)itemView.findViewById(R.id.homePostUserFrom);
            UserTo = (TextView)itemView.findViewById(R.id.homePostUserPostedTo_Postedon_username);

            postImage = (ImageView)itemView.findViewById(R.id.homePostPostedImage);
            postWebview = (WebView) itemView.findViewById(R.id.homePostWebview);
            //postVideo = (VideoView) itemView.findViewById(R.id.homePostPostedVideo);

            likeButt = (ImageButton) itemView.findViewById(R.id.homePostActionButtons_like);
            unlikeButt = (ImageButton)itemView.findViewById(R.id.homePostActionButtons_unlike);
            hateButt = (ImageButton) itemView.findViewById(R.id.homePostActionButtons_hate);
            commentButt = (ImageButton) itemView.findViewById(R.id.homePostActionButtons_comment);
            delButt = (ImageButton) itemView.findViewById(R.id.homePostActionButtons_delete);

            likers = (TextView) itemView.findViewById(R.id.home_likers);
            haters = (TextView) itemView.findViewById(R.id.home_haters);
            commenters = (TextView) itemView.findViewById(R.id.home_commenters);


        }


    }




}
