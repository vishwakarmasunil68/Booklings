package com.emobi.bjain.adapter;

import android.app.Activity;
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
import com.emobi.bjain.database.DatabaseHelper;
import com.emobi.bjain.pojo.cart.CartResultPOJO;
import com.emobi.bjain.pojo.newsearch.NewSearchResultPOJO;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.TagUtils;
import com.emobi.bjain.webservice.WebServicesUrls;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by sunil on 03-05-2017.
 */

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.MyViewHolder> {
    DatabaseHelper databaseHelper;
    private List<NewSearchResultPOJO> horizontalList;
    private Activity activity;
    private final String TAG=getClass().getSimpleName();

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


    public SearchProductAdapter(Activity activity, List<NewSearchResultPOJO> horizontalList) {
        this.horizontalList = horizontalList;
        this.activity=activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_feature_products, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        databaseHelper=new DatabaseHelper(activity);
        try {
            Gson gson=new Gson();
//            final SearchProductPOJO searchProductPOJO=gson.fromJson(horizontalList.get(position), SearchProductPOJO.class);
            Glide.with(activity).load(WebServicesUrls.IMAGE_BASE_URL+horizontalList.get(position).getProduct_image()).into(holder.iv_product);
            holder.tv_name.setText(horizontalList.get(position).getProduct_name());
            String main_price_currency= Pref.GetCurrency(activity.getApplicationContext());
            holder.tv_price.setText(main_price_currency+" " + getConvertedPrice(horizontalList.get(position).getMain_price()));
            holder.ll_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "product_id:-" + horizontalList.get(position).getProduct_id());
                    HomeActivity homeActivity = (HomeActivity) activity;
                    homeActivity.ViewProduct(horizontalList.get(position).getProduct_id());
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
                        Log.d(TagUtils.getTag(),"discount price:-"+getConvertedPrice(horizontalList.get(position).getDiscount_price()));
                        HomeActivity homeActivity= (HomeActivity) activity;
                        homeActivity.addToCart(horizontalList.get(position).getProduct_id(),
                                horizontalList.get(position).getSku(),
                                horizontalList.get(position).getProduct_name(),
                                "",
                                "1",
                                getConvertedPrice(horizontalList.get(position).getDiscount_price()),
                                getConvertedPrice(horizontalList.get(position).getDiscount_price()),
                                holder.iv_add_cart,
                                false
                        );
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getConvertedPrice(String price){
        try{
            double val=Double.parseDouble(price);
            DecimalFormat f = new DecimalFormat("##.00");
            return String.valueOf(f.format(val));
        }catch (Exception e){
            e.printStackTrace();
            return price;
        }
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}