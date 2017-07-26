package com.emobi.bjain.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emobi.bjain.R;

import java.util.List;

/**
 * Created by sunil on 28-04-2017.
 */

public class FeatureProductAdapter extends RecyclerView.Adapter<FeatureProductAdapter.MyViewHolder> {

    private List<String> horizontalList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_product;
        public TextView tv_name;
        public TextView tv_price;
        public ImageView iv_add_cart;
        public ImageView iv_favorite;
        public CardView cv_product;

        public MyViewHolder(View view) {
            super(view);
            iv_product = (ImageView) view.findViewById(R.id.iv_product);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            iv_add_cart= (ImageView) view.findViewById(R.id.iv_add_cart);
            iv_favorite= (ImageView) view.findViewById(R.id.iv_favorite);
            cv_product= (CardView) view.findViewById(R.id.cv_product);
        }
    }


    public FeatureProductAdapter(Context context, List<String> horizontalList) {
        this.horizontalList = horizontalList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_feature_products, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv_name.setText(horizontalList.get(position));

    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}