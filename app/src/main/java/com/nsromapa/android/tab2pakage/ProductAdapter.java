package com.nsromapa.android.tab2pakage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nsromapa.android.R;

import java.util.List;

/**
 * Created by SAY on 20/08/2018 at Nsromapa Goaso.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<Product> productList;


    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.fragment2_recyclerview_list,null);

        return new ProductViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewTitle.setText(product.getTitle());
        holder.textViewDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));
    }



    @Override
    public int getItemCount() {
       return productList.size();
    }





    class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewTitle,textViewDesc,textViewRating,textViewPrice;


        public ProductViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.friendsPFP);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = (TextView) itemView.findViewById(R.id.textViewRating);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
        }
    }


}
