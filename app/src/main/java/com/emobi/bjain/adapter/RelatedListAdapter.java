package com.emobi.bjain.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emobi.bjain.R;
import com.emobi.bjain.pojo.related.RelatedPOJO;
import com.emobi.bjain.webservice.WebServicesUrls;

import java.util.List;

/**
 * Created by sunil on 03-05-2017.
 */

public class RelatedListAdapter extends RecyclerView.Adapter<RelatedListAdapter.MyViewHolder> {

    private List<RelatedPOJO> horizontalList;
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


    public RelatedListAdapter(Activity activity, List<RelatedPOJO> horizontalList) {
        this.horizontalList = horizontalList;
        this.activity=activity;
    }

    @Override
    public RelatedListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_feature_products, parent, false);

        return new RelatedListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RelatedListAdapter.MyViewHolder holder, final int position) {
        Glide.with(activity).load(WebServicesUrls.IMAGE_BASE_URL+horizontalList.get(position).getSmall_image()).into(holder.iv_product);
        holder.tv_name.setText(horizontalList.get(position).getName());
        holder.tv_price.setText("$ "+horizontalList.get(position).getPrice());
        holder.ll_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d(TAG,"product_id:-"+horizontalList.get(position).getProduct_id());
//                HomeActivity homeActivity= (HomeActivity) activity;
//                homeActivity.ViewProduct(horizontalList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}
