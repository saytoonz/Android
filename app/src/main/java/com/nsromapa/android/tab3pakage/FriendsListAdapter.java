package com.nsromapa.android.tab3pakage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nsromapa.android.friendsprofile.FriendProfile;
import com.nsromapa.android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by SAY on 20/08/2018 at Nsromapa Goaso.
 */

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<FriendsList> friendsLists;


    public FriendsListAdapter(Context mCtx, List<FriendsList> productList) {
        this.mCtx = mCtx;
        this.friendsLists = productList;
    }


       @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.fragment3_recyclerview_list,null);

        return new ProductViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final FriendsList friend = friendsLists.get(position);

        holder.textViewUsername.setText(friend.getUsername());
        holder.textViewFulName.setText(friend.getFullname());

        if (friend.getLocImage()!=null){

            Bitmap bitmap = BitmapFactory.decodeByteArray(friend.getLocImage(),0,friend.getLocImage().length);
            holder.imageView.setImageBitmap(bitmap);

        }else{
            Picasso.get()
                    .load(friend.getImage())
                    .into(holder.imageView);
        }


//        new DownloadImage().execute(friend.getImage());
//        new SaveImageToSDCARD(mCtx,friend.getImage(),"thefile");

        holder.LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, FriendProfile.class);

                intent.putExtra("fid",friend.getId());

                mCtx.startActivity(intent);
            }
        });





    }



    @Override
    public int getItemCount() {
       return friendsLists.size();
    }





    class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewUsername,textViewFulName;
        LinearLayout LinearLayout;


        public ProductViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.friendsPFP);
            textViewUsername = (TextView) itemView.findViewById(R.id.FriendUsername);
            textViewFulName = (TextView) itemView.findViewById(R.id.FriendFullname);
            LinearLayout = (LinearLayout) itemView.findViewById(R.id.LinearLayout_FriendsList);

        }
    }




//
//
//
//    public Bitmap loadImageBitmap(Context context, String imageName) {
//        Bitmap bitmap = null;
//        FileInputStream fiStream;
//        try {
//            fiStream    = context.openFileInput(imageName);
//            bitmap      = BitmapFactory.decodeStream(fiStream);
//            fiStream.close();
//        } catch (Exception e) {
//            Log.d("saveImage", "Exception 3, Something went wrong!");
//            e.printStackTrace();
//        }
//        return bitmap;
//    }
}
