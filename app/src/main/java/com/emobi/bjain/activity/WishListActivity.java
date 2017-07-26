package com.emobi.bjain.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emobi.bjain.R;
import com.emobi.bjain.pojo.wishlist.WishListPOJO;
import com.emobi.bjain.pojo.wishlist.WishListResultPOJO;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.StringUtils;
import com.emobi.bjain.webservice.WebServiceBase;
import com.emobi.bjain.webservice.WebServicesCallBack;
import com.emobi.bjain.webservice.WebServicesUrls;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishListActivity extends AppCompatActivity implements WebServicesCallBack {

    private final String TAG = getClass().getSimpleName();
    private final String WISH_LIST_API_CALL = "wish_list_api_call";


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_scroll)
    LinearLayout ll_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Wish List");

        callWishListAPI();
    }

    public void callWishListAPI() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("user_id", Pref.GetStringPref(getApplicationContext(), StringUtils.ENTITY_ID, "")));
        new WebServiceBase(nameValuePairs, this, WISH_LIST_API_CALL).execute(WebServicesUrls.WISH_LIST_URL);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetMsg(String[] msg) {
        String apicall = msg[0];
        String response = msg[1];
        switch (apicall) {
            case WISH_LIST_API_CALL:
                parseWishListData(response);
                break;
        }
    }

    public void parseWishListData(String response) {
        try {
            Gson gson = new Gson();
            WishListPOJO wishListPOJO = gson.fromJson(response, WishListPOJO.class);
            inflateWishList(wishListPOJO.getWishListResultPOJOs());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inflateWishList(List<WishListResultPOJO> listResultPOJOs) {
        ll_scroll.removeAllViews();
        for (int i = 0; i < listResultPOJOs.size(); i++) {
            final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.inflate_wish_list, null);
            ImageView img_book = (ImageView) view.findViewById(R.id.img_book);
            TextView tv_book_name = (TextView) view.findViewById(R.id.tv_book_name);
            TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
            TextView tv_sku = (TextView) view.findViewById(R.id.tv_sku);
            ImageView iv_decrease_product = (ImageView) view.findViewById(R.id.iv_decrease_product);
            final TextView tv_quant = (TextView) view.findViewById(R.id.tv_quant);
            ImageView iv_increase_product = (ImageView) view.findViewById(R.id.iv_increase_product);
            ImageView iv_add_cart = (ImageView) view.findViewById(R.id.iv_add_cart);


            Glide.with(getApplicationContext())
                    .load(WebServicesUrls.IMAGE_BASE_URL + listResultPOJOs.get(i).getSmallImage())
                    .into(img_book);

            tv_price.setText("INR " + listResultPOJOs.get(i).getPrice());
            tv_book_name.setText(listResultPOJOs.get(i).getName());
            tv_sku.setText(listResultPOJOs.get(i).getSku());
            tv_quant.setText(String.valueOf(converttoInt(listResultPOJOs.get(i).getQty())));

            iv_decrease_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int val = converttoInt(tv_quant.getText().toString());
                        if (val == 0) {

                        } else {
                            val = val--;
                            tv_quant.setText(String.valueOf(val));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            iv_increase_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int val = converttoInt(tv_quant.getText().toString());
                        val = val++;
                        tv_quant.setText(String.valueOf(val));
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });


            ll_scroll.addView(view);
        }
    }

    public int converttoInt(String str){
        try{
            double num=Double.parseDouble(str);
            return (int) num;
        }catch (Exception e){
            return 0;
        }
    }
}
