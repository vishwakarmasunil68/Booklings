package com.emobi.bjain.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emobi.bjain.R;
import com.emobi.bjain.activity.HomeActivity;
import com.emobi.bjain.activity.LoginActivity;
import com.emobi.bjain.database.DatabaseHelper;
import com.emobi.bjain.pojo.cart.CartResultPOJO;
import com.emobi.bjain.pojo.recentview.RecentViewResultPOJO;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.StringUtils;
import com.emobi.bjain.webservice.WebServicesUrls;

import java.util.List;

/**
 * Created by sunil on 22-05-2017.
 */

public class RecentListAdapter extends RecyclerView.Adapter<RecentListAdapter.MyViewHolder> {

    private List<RecentViewResultPOJO> horizontalList;
    private Activity activity;
    private final String TAG=getClass().getSimpleName();
    private DatabaseHelper databaseHelper;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_product;
        public TextView tv_name;
        public TextView tv_price;
        public ImageView iv_add_cart;
        public ImageView iv_favorite;
        public CardView cv_product;
        public LinearLayout ll_product;

        public MyViewHolder(View view) {
            super(view);
            iv_product = (ImageView) view.findViewById(R.id.iv_product);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            iv_add_cart= (ImageView) view.findViewById(R.id.iv_add_cart);
            iv_favorite= (ImageView) view.findViewById(R.id.iv_favorite);
            cv_product= (CardView) view.findViewById(R.id.cv_product);
            ll_product= (LinearLayout) view.findViewById(R.id.ll_product);
        }
    }


    public RecentListAdapter(Activity activity, List<RecentViewResultPOJO> horizontalList) {
        this.horizontalList = horizontalList;
        this.activity=activity;
        databaseHelper=new DatabaseHelper(activity);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_feature_products, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Glide.with(activity).load(WebServicesUrls.IMAGE_BASE_URL+horizontalList.get(position).getSmall_image()).into(holder.iv_product);
        holder.tv_name.setText(horizontalList.get(position).getName());
        holder.tv_price.setText("$ "+horizontalList.get(position).getPrice());
        holder.ll_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"product_id:-"+horizontalList.get(position).getProduct_id());
                HomeActivity homeActivity= (HomeActivity) activity;
                homeActivity.ViewProduct(horizontalList.get(position).getProduct_id());
            }
        });
        Log.d(TAG,"product id:-"+horizontalList.get(position).getProduct_id());
        if(databaseHelper.getWishListData(horizontalList.get(position).getProduct_id())!=null){
            Log.d(TAG,"list products ids:-"+horizontalList.get(position).getProduct_id());
            Log.d(TAG,"product:-"+databaseHelper.getWishListData(horizontalList.get(position).getProduct_id()));
            holder.iv_favorite.setImageResource(R.drawable.ic_fav_selected);
        }else{
            holder.iv_favorite.setImageResource(R.drawable.ic_fav);
        }
        holder.iv_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Pref.GetBooleanPref(activity.getApplicationContext(), StringUtils.IS_LOGIN,false)) {
                    HomeActivity homeActivity= (HomeActivity) activity;
                    if (databaseHelper.getWishListData(horizontalList.get(position).getProduct_id()) != null) {
                        //remove from db
                        homeActivity.DeleteFromWishList(horizontalList.get(position).getProduct_id(),holder.iv_favorite);
//                        holder.iv_favorite.setImageResource(R.drawable.ic_fav);
                    } else {
                        //add to db
                        homeActivity.AddToWishList(horizontalList.get(position).getProduct_id(),"1",holder.iv_favorite);
//                        holder.iv_favorite.setImageResource(R.drawable.ic_fav_selected);
                    }
                }else{
                    activity.startActivity(new Intent(activity, LoginActivity.class));
                }
                Log.d(TAG,"wishlist datas:-"+databaseHelper.getAllWishListData().toString());
            }
        });

        final CartResultPOJO cartResultPOJO=databaseHelper.getCartData(horizontalList.get(position).getProduct_id());
        if(cartResultPOJO!=null){
            holder.iv_add_cart.setImageResource(R.drawable.ic_loaded_cart);
        }else{
            holder.iv_add_cart.setImageResource(R.drawable.ic_empty_cart);
        }
        holder.iv_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartResultPOJO!=null){
                    //remove from cart
                    HomeActivity homeActivity= (HomeActivity) activity;
                    homeActivity.callCartSingleItemDelete(
                            cartResultPOJO.getCart_id(),
                            cartResultPOJO.getProduct_id(),
                            holder.iv_add_cart
                    );
                }else{
                    //add to cart
//                    String price="";
//                    if(horizontalList.get(position).getDiscount_price()!=null){
//                        price=horizontalList.get(position).getDiscount_price();
//                    }else{
//                        price=horizontalList.get(position).getPrice();
//                    }
                    HomeActivity homeActivity= (HomeActivity) activity;
                    homeActivity.addToCart(horizontalList.get(position).getProduct_id(),
                            horizontalList.get(position).getSku(),
                            horizontalList.get(position).getName(),
                            "",
                            "1",
                            horizontalList.get(position).getPrice(),
                            horizontalList.get(position).getPrice(),
                            holder.iv_add_cart,
                            false
                    );
                }

            }
        });
    }



    @Override
    public int getItemCount() {
        if(horizontalList!=null){
            return horizontalList.size();
        }else{
            return 0;
        }
    }
}