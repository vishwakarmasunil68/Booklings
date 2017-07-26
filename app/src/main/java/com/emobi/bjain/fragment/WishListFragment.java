package com.emobi.bjain.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emobi.bjain.R;
import com.emobi.bjain.activity.HomeActivity;
import com.emobi.bjain.database.DatabaseHelper;
import com.emobi.bjain.pojo.newwishlist.NewWishListResultPOJO;
import com.emobi.bjain.pojo.newwishlist.NewWishlistPOJO;
import com.emobi.bjain.pojo.wishlist.WishListPOJO;
import com.emobi.bjain.pojo.wishlist.WishListResultPOJO;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.StringUtils;
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

public class WishListFragment extends Fragment implements WebServicesCallBack {
    private final String TAG = getClass().getSimpleName();
    private final String WISH_LIST_API_CALL = "wish_list_api_call";
    private final String WISHLIST_DELETE_ITEM_CALL = "wishlist_delete_item_call";
    private final String WISHLIST_ITEM_UPDATE_CALL = "wishlist_item_update_call";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_scroll)
    LinearLayout ll_scroll;

    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_wish_list, container, false);
        ButterKnife.bind(this, view);
        databaseHelper = new DatabaseHelper(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callWishListAPI();
    }

    public void callWishListAPI() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("user_id", Pref.GetStringPref(getActivity().getApplicationContext(), StringUtils.ENTITY_ID, "")));
        new WebServiceBase(nameValuePairs, getActivity(), this, WISH_LIST_API_CALL).execute(WebServicesUrls.WISH_LIST_URL);
    }


    @Override
    public void onGetMsg(String[] msg) {
        String apicall = msg[0];
        String response = msg[1];
        switch (apicall) {
            case WISH_LIST_API_CALL:
                parseNewWishListData(response);
//                parseWishListData(response);
                break;
            case WISHLIST_DELETE_ITEM_CALL:
                parseWishListItemDeleteData(response);
                break;
            case WISHLIST_ITEM_UPDATE_CALL:
                parseWishListItemUpdate(response);
                break;
        }
    }

    public void parseWishListItemUpdate(String response) {
        Log.d(TAG, "update response:-" + response);
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseWishListItemDeleteData(String response) {
        Log.d(TAG, "delete response:-" + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("success").equals("true")) {
                String wishlist_id = jsonObject.optString("wishlist_id");
                databaseHelper.deleteWishListItem(wishlist_id);
                callWishListAPI();
            } else {
                ToastClass.showLongToast(getActivity().getApplicationContext(), "Something went wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseNewWishListData(String response) {
        Log.d(TAG, "wishlist data:-" + response);
        try {
            Gson gson = new Gson();
            NewWishlistPOJO newWishlistPOJO = gson.fromJson(response, NewWishlistPOJO.class);

            if (newWishlistPOJO.getSuccess().equals("true")) {

                List<NewWishListResultPOJO> newWishListResultPOJOList = newWishlistPOJO.getWishListResultPOJOList();
                Set<String> entitySet = new HashSet<>();
                for (NewWishListResultPOJO newWishListResultPOJO : newWishListResultPOJOList) {
                    entitySet.add(newWishListResultPOJO.getEntityId());
                }


                List<WishListResultPOJO> wishListResultPOJOList = new ArrayList<>();
                for (String s : entitySet) {
                    WishListResultPOJO wishListResultPOJO = new WishListResultPOJO();
                    for (NewWishListResultPOJO newWishListResultPOJO : newWishListResultPOJOList) {
                        if (s.equals(newWishListResultPOJO.getEntityId())) {
                            switch (newWishListResultPOJO.getAttributeId()) {
                                case "71":
                                    wishListResultPOJO.setWishlistId(newWishListResultPOJO.getWishlistId());
                                    wishListResultPOJO.setWishlistItemId(newWishListResultPOJO.getWishlistItemId());
                                    wishListResultPOJO.setEntity_id(newWishListResultPOJO.getEntityId());
                                    wishListResultPOJO.setDescription(newWishListResultPOJO.getDescription());
                                    wishListResultPOJO.setQty(newWishListResultPOJO.getQty());
                                    wishListResultPOJO.setName(newWishListResultPOJO.getValue());
                                    wishListResultPOJO.setPrice(newWishListResultPOJO.getDiscountPrice());
                                    wishListResultPOJO.setShortDescription(newWishListResultPOJO.getDescription());
                                    wishListResultPOJO.setSku(newWishListResultPOJO.getSku());
                                    wishListResultPOJO.setShortDescription(newWishListResultPOJO.getDescription());
                                    break;
                                case "85":
                                    wishListResultPOJO.setSmallImage(newWishListResultPOJO.getValue());
                                    wishListResultPOJO.setThumbnail(newWishListResultPOJO.getValue());
                                    break;
                            }
                        }
                    }
                    wishListResultPOJOList.add(wishListResultPOJO);
                }

                WishListPOJO wishListPOJO = new WishListPOJO();
                wishListPOJO.setSuccess("true");
                wishListPOJO.setWishListResultPOJOs(wishListResultPOJOList);
                parseWishListData(wishListPOJO);
            } else {
                ToastClass.showShortToast(getActivity().getApplicationContext(), "No Item Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastClass.showShortToast(getActivity().getApplicationContext(), "No Item Found");
        }
    }


    public void parseWishListData(WishListPOJO wishListPOJO) {
//        Log.d(TAG,"response:-"+response);
        try {
//            Gson gson = new Gson();
//            WishListPOJO wishListPOJO = gson.fromJson(response, WishListPOJO.class);
            if (wishListPOJO.getSuccess().equals("true")) {

                if (wishListPOJO.getWishListResultPOJOs().size() > 0) {
                    inflateWishList(wishListPOJO.getWishListResultPOJOs());
                    databaseHelper.deleteAllWishListData();
//                    Log.d(TAG,"all wishlist data:-"+databaseHelper.getAllWishListData().toString());
//                    Log.d(TAG,"all wishlist size:-"+databaseHelper.getAllWishListData().size());
                    for (WishListResultPOJO wishListResultPOJO : wishListPOJO.getWishListResultPOJOs()) {
                        databaseHelper.insertWishlistData(wishListResultPOJO);
                    }
//                    Log.d(TAG, "all wishlist data:-" + databaseHelper.getAllWishListData().toString());
//                    Log.d(TAG, "all wishlist size:-" + databaseHelper.getAllWishListData().size());
                }
            } else {
                ll_scroll.removeAllViews();
                ToastClass.showLongToast(getActivity().getApplicationContext(), "No Order Found");
            }

        } catch (Exception e) {
            ll_scroll.removeAllViews();
            e.printStackTrace();
        }
    }

    public void inflateWishList(final List<WishListResultPOJO> listResultPOJOs) {
        ll_scroll.removeAllViews();
        for (int i = 0; i < listResultPOJOs.size(); i++) {
            final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.inflate_wish_list, null);
            ImageView img_book = (ImageView) view.findViewById(R.id.img_book);
            TextView tv_book_name = (TextView) view.findViewById(R.id.tv_book_name);
            TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
            TextView tv_sku = (TextView) view.findViewById(R.id.tv_sku);
            ImageView iv_decrease_product = (ImageView) view.findViewById(R.id.iv_decrease_product);
            final TextView tv_quant = (TextView) view.findViewById(R.id.tv_quant);
            ImageView iv_increase_product = (ImageView) view.findViewById(R.id.iv_increase_product);
            ImageView iv_add_cart = (ImageView) view.findViewById(R.id.iv_add_cart);
            ImageView iv_delete_item = (ImageView) view.findViewById(R.id.iv_delete_item);
            LinearLayout ll_wish_item = (LinearLayout) view.findViewById(R.id.ll_wish_item);
            final EditText et_review = (EditText) view.findViewById(R.id.et_review);
            Button btn_update = (Button) view.findViewById(R.id.btn_update);


            Glide.with(getActivity().getApplicationContext())
                    .load(WebServicesUrls.IMAGE_BASE_URL + listResultPOJOs.get(i).getSmallImage())
                    .into(img_book);

            tv_price.setText("INR " + listResultPOJOs.get(i).getPrice());
            tv_book_name.setText(listResultPOJOs.get(i).getName());
            tv_sku.setText(listResultPOJOs.get(i).getSku());
            tv_quant.setText(String.valueOf(converttoInt(listResultPOJOs.get(i).getQty())));
            et_review.setText(listResultPOJOs.get(i).getDescription());
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
                    homeActivity.ViewProduct(listResultPOJOs.get(finalI).getEntity_id());
                }
            });
            final int finalI1 = i;
            iv_delete_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteWishList(listResultPOJOs.get(finalI1).getWishlistItemId());
                }
            });

            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateWishlist(et_review.getText().toString(),
                            tv_quant.getText().toString(),
                            listResultPOJOs.get(finalI).getWishlistItemId(),
                            Pref.GetStringPref(getActivity().getApplicationContext(), StringUtils.ENTITY_ID, "")
                    );
                }
            });

            ll_scroll.addView(view);
        }
    }

    public void updateWishlist(String description, String qty, String wishlist_item_id, String user_id) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("description", description));
        nameValuePairs.add(new BasicNameValuePair("qty", qty));
        nameValuePairs.add(new BasicNameValuePair("wishlist_item_id", wishlist_item_id));
        nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
//        nameValuePairs.add(new BasicNameValuePair("wishlist_id", wishlist_id));
        new WebServiceBase(nameValuePairs, getActivity(), this, WISHLIST_ITEM_UPDATE_CALL).execute(WebServicesUrls.WISH_LIST_ITEM_UPDATE_URL);
    }


    public void deleteWishList(String wishlist_item_id) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("wishlist_item_id", wishlist_item_id));
//        nameValuePairs.add(new BasicNameValuePair("wishlist_id", wishlist_id));
        new WebServiceBase(nameValuePairs, getActivity(), this, WISHLIST_DELETE_ITEM_CALL).execute(WebServicesUrls.WISHLIST_DELETE_ITEM_URL);
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
