package com.emobi.bjain.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emobi.bjain.R;
import com.emobi.bjain.activity.CartCheckOutActivity;
import com.emobi.bjain.activity.HomeActivity;
import com.emobi.bjain.database.DatabaseHelper;
import com.emobi.bjain.pojo.cart.CartPOJO;
import com.emobi.bjain.pojo.cart.CartResultPOJO;
import com.emobi.bjain.pojo.newcart.NewCartPOJO;
import com.emobi.bjain.pojo.newcart.NewCartResultPOJO;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.StringUtils;
import com.emobi.bjain.utils.TagUtils;
import com.emobi.bjain.utils.ToastClass;
import com.emobi.bjain.webservice.WebServiceBase;
import com.emobi.bjain.webservice.WebServicesCallBack;
import com.emobi.bjain.webservice.WebServicesUrls;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sunil on 02-05-2017.
 */

public class CartFragment extends Fragment implements WebServicesCallBack {
    private final String TAG = getClass().getSimpleName();
    private final String CART_API_CALL = "cart_api_call";
    private final String UPDATE_CART_ITEM = "update_cart_item";
    private final String DELETE_SINGLE_ITEM = "delete_single_item";
    private final String CLEAR_CART_ITEM = "clear_cart_items";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_scroll)
    LinearLayout ll_scroll;
    @BindView(R.id.btn_checkout)
    Button btn_checkout;
    @BindView(R.id.tv_cart_sub_total)
    TextView tv_cart_sub_total;
    @BindView(R.id.ll_checkout)
    LinearLayout ll_checkout;
    boolean isDataAvail=false;
    DatabaseHelper databaseHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_cart, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper=new DatabaseHelper(getActivity());
        callCartListAPI();
    }

    public void callCartListAPI() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("quote_id", Pref.GetStringPref(getActivity().getApplicationContext(), StringUtils.QUOTOID,"")));
        new WebServiceBase(nameValuePairs, getActivity(), this, CART_API_CALL).execute(WebServicesUrls.CART_LIST_URL);
    }


    @Override
    public void onGetMsg(String[] msg) {
        String apicall = msg[0];
        String response = msg[1];
        switch (apicall) {
            case CART_API_CALL:
//                parseCartData(response);
                parseNewCartData(response);
                break;
            case UPDATE_CART_ITEM:
                parseCartUpdateData(response);
                break;
            case DELETE_SINGLE_ITEM:
                parseSingleDeleteItem(response);
                break;
            case CLEAR_CART_ITEM:
                parseClearCartItems(response);
                break;
        }
    }

    public void parseNewCartData(String response){
        Log.d(TAG,"cart response:-"+response);
        try{
            Gson gson=new Gson();
            NewCartPOJO newCartPOJO=gson.fromJson(response,NewCartPOJO.class);
            if(newCartPOJO.getSuccess().equals("true")){

                List<NewCartResultPOJO> newCartResultPOJOList=newCartPOJO.getNewCartResultPOJOList();
                Set<String> entitySet=new HashSet<>();
                for(NewCartResultPOJO newCartResultPOJO:newCartResultPOJOList){
                    entitySet.add(newCartResultPOJO.getEntityId());
                }


                CartPOJO cartPOJO=new CartPOJO();
                cartPOJO.setGrand_total(newCartPOJO.getGrand_total());
                cartPOJO.setSuccess("true");

                List<CartResultPOJO> cartResultPOJOList=new ArrayList<>();
                for(String s:entitySet){
                    CartResultPOJO cartResultPOJO=new CartResultPOJO();
                    for(NewCartResultPOJO newCartResultPOJO:newCartResultPOJOList){
                        if(s.equals(newCartResultPOJO.getEntityId())){
                            switch (newCartResultPOJO.getAttributeId()){
                                case "71":
                                    cartResultPOJO.setProduct_id(newCartResultPOJO.getProductId());
                                    cartResultPOJO.setCart_id(newCartResultPOJO.getCartId());
                                    cartResultPOJO.setName(newCartResultPOJO.getName());
                                    cartResultPOJO.setPrice(newCartResultPOJO.getPrice());
                                    cartResultPOJO.setSku(newCartResultPOJO.getSku());
                                    cartResultPOJO.setQty(newCartResultPOJO.getQty());
                                    break;
                                case "85":
                                    cartResultPOJO.setImage(newCartResultPOJO.getValue());
                                    break;
                            }
                        }
                    }
                    cartResultPOJOList.add(cartResultPOJO);
                }

                cartPOJO.setCartResultPOJOList(cartResultPOJOList);
                Log.d(TagUtils.getTag(),"cart pojo:-"+cartPOJO.toString());
                parseCartData(cartPOJO);


            }else{
                ToastClass.showShortToast(getActivity(),"No Item Found");
                ll_scroll.removeAllViews();
            }
        }catch (Exception e){
            e.printStackTrace();
            ToastClass.showShortToast(getActivity(),"No Item Found");
            ll_scroll.removeAllViews();
        }
    }

    public void parseClearCartItems(String response){
        Log.d(TAG,"clear cart response:-"+response);
        try{
            JSONObject jsonObject=new JSONObject(response);
            if(jsonObject.optString("success").equals("true")){
                databaseHelper.deleteAllCartItems();
                ToastClass.showShortToast(getActivity().getApplicationContext(),"Cart cleared");
            }else{
                ToastClass.showShortToast(getActivity().getApplicationContext(),"Failed to clear cart");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void parseSingleDeleteItem(String response){
        Log.d(TAG,"delete cart response:-"+response);
        try{
            JSONObject jsonObject=new JSONObject(response);
            if(jsonObject.optString("success").equals("true")){
                ToastClass.showShortToast(getActivity().getApplicationContext(),"Item Deleted");
                callCartListAPI();
                databaseHelper.deleteCartData(jsonObject.optString("cart_id"));
            }else{
                ToastClass.showShortToast(getActivity().getApplicationContext(),"Item Not Deleted");
            }
        }catch (Exception e){
            e.printStackTrace();
            ToastClass.showShortToast(getActivity().getApplicationContext(),"Item Not Deleted");
        }
    }

    public void clearCartData(){
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("quote_id", Pref.GetStringPref(getActivity().getApplicationContext(), StringUtils.QUOTOID,"")));
        new WebServiceBase(nameValuePairs, getActivity(), this, CLEAR_CART_ITEM).execute(WebServicesUrls.CLEAR_CART_URL);
    }

    public void parseCartUpdateData(String response){
        Log.d(TAG,"cart update data:-"+response);
        ToastClass.showShortToast(getActivity().getApplicationContext(),"Cart Updated");
        parseNewCartData(response);
    }

    public void parseCartData(final CartPOJO cartPOJO) {
        String price_currency=Pref.GetCurrency(getActivity().getApplicationContext());
//        Log.d(TAG, "cart response:-" + response);
        try {
//            Gson gson = new Gson();
//            final CartPOJO cartPOJO = gson.fromJson(response, CartPOJO.class);
            if (cartPOJO.getSuccess().equals("true") && cartPOJO.getCartResultPOJOList().size() > 0) {

                tv_cart_sub_total.setText("Grand Total : "+price_currency+" "+cartPOJO.getGrand_total());
                inflateCartList(cartPOJO.getCartResultPOJOList());
                isDataAvail=true;
                btn_checkout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(), CartCheckOutActivity.class);
                        intent.putExtra("cartpojo",cartPOJO);
                        startActivity(intent);
                    }
                });
            } else {
                tv_cart_sub_total.setText("Grand Total : "+price_currency+" 0");
                ll_scroll.removeAllViews();
                isDataAvail=false;
                ToastClass.showShortToast(getActivity().getApplicationContext(), "No Product Found");
            }
        } catch (Exception e) {
            tv_cart_sub_total.setText("Grand Total : "+price_currency+" 0");
            ll_scroll.removeAllViews();
            e.printStackTrace();
            isDataAvail=false;
            ToastClass.showShortToast(getActivity().getApplicationContext(), "No Product Found");
        }
    }


    public void inflateCartList(final List<CartResultPOJO> listResultPOJOs) {
        ll_scroll.removeAllViews();
        databaseHelper.deleteAllCartItems();
        for (int i = 0; i < listResultPOJOs.size(); i++) {
            final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.inflate_cart_list, null);
            databaseHelper.insertCartData(listResultPOJOs.get(i));
            ImageView img_book = (ImageView) view.findViewById(R.id.img_book);
            TextView tv_book_name = (TextView) view.findViewById(R.id.tv_book_name);
            TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
            TextView tv_sku = (TextView) view.findViewById(R.id.tv_sku);
            ImageView iv_decrease_product = (ImageView) view.findViewById(R.id.iv_decrease_product);
            ImageView iv_delete_item = (ImageView) view.findViewById(R.id.iv_delete_item);
            final TextView tv_quant = (TextView) view.findViewById(R.id.tv_quant);
            ImageView iv_increase_product = (ImageView) view.findViewById(R.id.iv_increase_product);
            Button btn_update = (Button) view.findViewById(R.id.btn_update);

            LinearLayout ll_wish_item = (LinearLayout) view.findViewById(R.id.ll_cart_item);


            Glide.with(getActivity().getApplicationContext())
                    .load(WebServicesUrls.IMAGE_BASE_URL + listResultPOJOs.get(i).getImage())
                    .into(img_book);
            String price_currency=Pref.GetCurrency(getActivity().getApplicationContext());
            tv_price.setText(price_currency+" " + listResultPOJOs.get(i).getPrice());
            tv_book_name.setText(listResultPOJOs.get(i).getName());
            tv_sku.setText(listResultPOJOs.get(i).getSku());
            tv_quant.setText(String.valueOf(converttoInt(listResultPOJOs.get(i).getQty())));
            iv_decrease_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int val = converttoInt(tv_quant.getText().toString());
                        Log.d(TAG, "increase value:-" + val);
                        if (val == 0) {

                        } else {
                            val = val - 1;
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
                        val = val + 1;
                        Log.d(TAG, "increase value:-" + val);
                        tv_quant.setText(String.valueOf(val));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            final int finalI = i;
            ll_wish_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HomeActivity homeActivity = (HomeActivity) getActivity();
                    homeActivity.ViewProduct(listResultPOJOs.get(finalI).getProduct_id());
                }
            });
            final int finalI1 = i;


            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateCartItem(listResultPOJOs.get(finalI).getCart_id(),tv_quant.getText().toString());
                }
            });

            iv_delete_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callCartSingleItemDelete(listResultPOJOs.get(finalI).getCart_id(),
                            listResultPOJOs.get(finalI).getProduct_id());
                }
            });

            ll_scroll.addView(view);
        }
    }

    public void callCartSingleItemDelete(String cart_id,String product_id){
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("cart_id", cart_id));
        nameValuePairs.add(new BasicNameValuePair("product_id", product_id));
        new WebServiceBase(nameValuePairs, getActivity(), this, DELETE_SINGLE_ITEM).execute(WebServicesUrls.CART_SINGLE_DELETE_URL);
    }

    public void updateCartItem(String cart_id, String product_qty) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("cart_id", cart_id));
        nameValuePairs.add(new BasicNameValuePair("quote_id", Pref.GetStringPref(getActivity().getApplicationContext(), StringUtils.QUOTOID,"")));
        nameValuePairs.add(new BasicNameValuePair("product_qty", product_qty));
        new WebServiceBase(nameValuePairs, getActivity(), this, UPDATE_CART_ITEM).execute(WebServicesUrls.CART_ITEM_UPDATE_URL);
    }

    public int converttoInt(String str) {
        try {
            double num = Double.parseDouble(str);
            return (int) num;
        } catch (Exception e) {
            return 0;
        }
    }
}
