package com.emobi.bjain.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emobi.bjain.R;
import com.emobi.bjain.activity.HomeActivity;
import com.emobi.bjain.activity.LoginActivity;
import com.emobi.bjain.adapter.CustomswipeAdapter;
import com.emobi.bjain.adapter.FeatureListProductAdapter;
import com.emobi.bjain.database.DatabaseHelper;
import com.emobi.bjain.pojo.cart.AddToCartPOJO;
import com.emobi.bjain.pojo.cart.CartResultPOJO;
import com.emobi.bjain.pojo.featurelist.FeatureDataPOJO;
import com.emobi.bjain.pojo.newproductview.NewProductGalleryPOJO;
import com.emobi.bjain.pojo.newproductview.NewProductPOJO;
import com.emobi.bjain.pojo.newproductview.NewProductResultPOJO;
import com.emobi.bjain.pojo.productview.ProductPOJO;
import com.emobi.bjain.pojo.related.RelatedMainPOJO;
import com.emobi.bjain.pojo.review.UserReviewPOJO;
import com.emobi.bjain.pojo.review.UserReviewResultPOJO;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.StringUtils;
import com.emobi.bjain.utils.TagUtils;
import com.emobi.bjain.utils.ToastClass;
import com.emobi.bjain.webservice.WebServiceBase;
import com.emobi.bjain.webservice.WebServicesCallBack;
import com.emobi.bjain.webservice.WebServicesUrls;
import com.emobi.bjain.widgets.ExpandableTextView;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunil on 29-04-2017.
 */

public class ProductViewFragment extends Fragment implements WebServicesCallBack {

    private final String TAG = getClass().getSimpleName();
    private final String PRODUCT_VIEW_API = "product_view_api";
    private final String ADD_TO_WISHLIST_API = "add_to_wishlist_api";
    private final String ADD_TO_CART_API = "add_to_cart_api";
    private final String RELATED_PRODUCT_API = "related_product_api";
    private final String USER_REVIEW_API = "user_review_api";
    private final String ADD_REVIEW_API = "add_review_api";

    TextView tv_book_name, tv_author, tv_sku, tv_book_quantity, tv_price,
            tv_add_to_wish_list, tv_availability, tv_no_reviews,tv_discount_price;
    ExpandableTextView tv_quick_overview;
    ImageView  iv_share, iv_decrease_product, iv_increase_product;
    Button btn_add_to_cart, btn_buy_now;
    RecyclerView rv_related_products;
    LinearLayout ll_nav_cat, ll_related, ll_review_scroll, ll_write_review;
    DatabaseHelper databaseHelper;
    ViewPager image_pager;

    FeatureDataPOJO featureDataPOJO;
    String product_id;

    List<FeatureDataPOJO> featureDataPOJOList;
    int position;

    public ProductViewFragment(FeatureDataPOJO featureDataPOJO,List<FeatureDataPOJO> featureDataPOJOList,int position) {
        this.featureDataPOJO = featureDataPOJO;
        product_id=featureDataPOJO.getProduct_id();
        this.featureDataPOJOList=featureDataPOJOList;
        this.position=position;
    }

    public ProductViewFragment(String product_id) {
        this.product_id = product_id;
    }

    public ProductViewFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_product_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_book_name = (TextView) view.findViewById(R.id.tv_book_name);
        tv_author = (TextView) view.findViewById(R.id.tv_author);
        tv_book_quantity = (TextView) view.findViewById(R.id.tv_book_quantity);
        tv_add_to_wish_list = (TextView) view.findViewById(R.id.tv_add_to_wish_list);
        tv_quick_overview = (ExpandableTextView) view.findViewById(R.id.tv_quick_overview);
        tv_sku = (TextView) view.findViewById(R.id.tv_sku);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_discount_price = (TextView) view.findViewById(R.id.tv_discount_price);
        tv_availability = (TextView) view.findViewById(R.id.tv_availability);
        tv_no_reviews = (TextView) view.findViewById(R.id.tv_no_reviews);
//        iv_image_pic = (ImageView) view.findViewById(R.id.iv_image_pic);
        iv_share = (ImageView) view.findViewById(R.id.iv_share);
        iv_decrease_product = (ImageView) view.findViewById(R.id.iv_decrease_product);
        iv_increase_product = (ImageView) view.findViewById(R.id.iv_increase_product);
        btn_buy_now = (Button) view.findViewById(R.id.btn_buy_now);
        btn_add_to_cart = (Button) view.findViewById(R.id.btn_add_to_cart);
        rv_related_products = (RecyclerView) view.findViewById(R.id.rv_related_products);
        ll_nav_cat = (LinearLayout) view.findViewById(R.id.ll_nav_cat);
        ll_related = (LinearLayout) view.findViewById(R.id.ll_related);
        ll_review_scroll = (LinearLayout) view.findViewById(R.id.ll_review_scroll);
        ll_write_review = (LinearLayout) view.findViewById(R.id.ll_write_review);
        image_pager = (ViewPager) view.findViewById(R.id.image_pager);

        databaseHelper = new DatabaseHelper(getActivity());
        Log.d(TAG, "productId:-" + product_id);
        if (featureDataPOJO != null) {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("product_id", featureDataPOJO.getProduct_id()));
            nameValuePairs.add(new BasicNameValuePair("type", Pref.GetCurrency(getActivity().getApplicationContext())));
            new WebServiceBase(nameValuePairs, getActivity(), this, PRODUCT_VIEW_API).execute(WebServicesUrls.PRODUCT_VIEW_URL);


            ////Log.d(TAG, "feature list:-" + Pref.feature_string);


            tv_book_name.setText(featureDataPOJO.getName());
//            Glide.with(getActivity().getApplicationContext())
//                    .load(featureDataPOJO.getUrl())
//                    .into(iv_image_pic);
            tv_author.setText(featureDataPOJO.getAuthor_name());
            iv_decrease_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tv_book_quantity.getText().toString().equals("1")) {

                    } else {
                        try {
                            int val = Integer.parseInt(tv_book_quantity.getText().toString());
                            val = val - 1;
                            tv_book_quantity.setText(String.valueOf(val));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            iv_increase_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int val = Integer.parseInt(tv_book_quantity.getText().toString());
                        val = val + 1;
                        tv_book_quantity.setText(String.valueOf(val));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            String currencyform=Pref.GetCurrency(getActivity().getApplicationContext());
            tv_quick_overview.setText(Html.fromHtml(featureDataPOJO.getShort_description()));
            if(featureDataPOJO.getDiscount_price()!=null) {
                tv_discount_price.setText(currencyform+" "+getConvertedPrice(featureDataPOJO.getDiscount_price()));
                tv_price.setText(currencyform+" " + getConvertedPrice(featureDataPOJO.getPrice()));
                tv_discount_price.setVisibility(View.VISIBLE);
            }else{
                tv_discount_price.setVisibility(View.GONE);
                tv_price.setText(currencyform+" " + getConvertedPrice(featureDataPOJO.getPrice()));
            }

            tv_sku.setText(featureDataPOJO.getSku());

            ll_nav_cat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        HomeActivity homeActivity = (HomeActivity) getActivity();
                        homeActivity.openCatNavDrawer();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        } else {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("product_id", product_id));
            nameValuePairs.add(new BasicNameValuePair("type", Pref.GetCurrency(getActivity().getApplicationContext())));
            new WebServiceBase(nameValuePairs, getActivity(), this, PRODUCT_VIEW_API).execute(WebServicesUrls.PRODUCT_VIEW_URL);
        }
//        parseFeatureApiData(Pref.feature_string);
        String id="";
        if (featureDataPOJO != null) {
            id=featureDataPOJO.getProduct_id();
//            callRelatedProductAPI(featureDataPOJO.getProduct_id());
            callReviewsAPI(featureDataPOJO.getProduct_id());
        } else {
            id=product_id;
//            callRelatedProductAPI(product_id);
            callReviewsAPI(product_id);
        }

        if(featureDataPOJOList!=null&&featureDataPOJOList.size()>0){
            List<FeatureDataPOJO> newFeatureDataPOJOList=new ArrayList<>();
            for(FeatureDataPOJO featureDataPOJO:featureDataPOJOList){
                newFeatureDataPOJOList.add(featureDataPOJO);
            }
            newFeatureDataPOJOList.remove(position);
            FeatureListProductAdapter adapter = new FeatureListProductAdapter(getActivity(), newFeatureDataPOJOList);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            rv_related_products.setLayoutManager(horizontalLayoutManagaer);
            rv_related_products.setAdapter(adapter);
            ll_related.setVisibility(View.VISIBLE);
        }else{
            rv_related_products.setVisibility(View.GONE);
        }

        final String finalId = id;
        ll_write_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Pref.GetBooleanPref(getActivity().getApplicationContext(),StringUtils.IS_LOGIN,false)) {
                    showReviewDialog(finalId);
                }else{
                    getActivity().startActivity(new Intent(getActivity(),LoginActivity.class));
                }
            }
        });
    }



    public void showReviewDialog(final String product_id) {
        final Dialog dialog1 = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog);
//        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog1.setContentView(R.layout.dialog_review);
        dialog1.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog1.setTitle("Write Review");
        dialog1.setCancelable(true);
        dialog1.show();
        Window window = dialog1.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button btn_add = (Button) dialog1.findViewById(R.id.btn_add);
        final EditText et_review = (EditText) dialog1.findViewById(R.id.et_review);
        final EditText et_summary = (EditText) dialog1.findViewById(R.id.et_summary);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_review.getText().toString().length() > 0&&et_summary.getText().toString().length()>0) {
                    callAddReviewAPI(product_id,et_summary.getText().toString(),et_review.getText().toString());
                    dialog1.dismiss();
                } else {
                    ToastClass.showShortToast(getActivity().getApplicationContext(), "Please Write Something");
                }
            }
        });
    }
    public void callAddReviewAPI(String product_id,String title,String detail){
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("product_id", product_id));
        nameValuePairs.add(new BasicNameValuePair("title", title));
        nameValuePairs.add(new BasicNameValuePair("detail", detail));
        nameValuePairs.add(new BasicNameValuePair("nickname", Pref.GetStringPref(getActivity().getApplicationContext(),StringUtils.FIRST_NAME,"")));
        nameValuePairs.add(new BasicNameValuePair("customer_id", Pref.GetStringPref(getActivity().getApplicationContext(),StringUtils.ENTITY_ID,"")));
        new WebServiceBase(nameValuePairs, getActivity(), this, ADD_REVIEW_API, false).execute(WebServicesUrls.ADD_REVIEW_URL);
    }

    public void callReviewsAPI(String product_id) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("product_id", product_id));
        new WebServiceBase(nameValuePairs, getActivity(), this, USER_REVIEW_API, false).execute(WebServicesUrls.USER_REVIEW_URL);
    }

    public void callRelatedProductAPI(String product_id) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("product_id", product_id));
        nameValuePairs.add(new BasicNameValuePair("type", Pref.GetCurrency(getActivity().getApplicationContext())));
        new WebServiceBase(nameValuePairs, getActivity(), this, RELATED_PRODUCT_API, false).execute(WebServicesUrls.RELATED_PRODUCT_URL);
    }

    public void parseFeatureApiData(String response) {
        try {
            ////Log.d(TAG, "feature response data:-" + response);
            if (response.contains("NO Recodes Found")) {

            } else {
                Gson gson = new Gson();
                final RelatedMainPOJO featurePOJO = gson.fromJson(response, RelatedMainPOJO.class);
                if (featurePOJO.getSuccess().equals("true")) {
                    JSONObject jsonObject = new JSONObject(response);
                    if (!jsonObject.optString("result").equals("null")) {
                        ////Log.d(TAG, "feature pojo:-" + featurePOJO.toString());
                        FeatureListProductAdapter adapter = new FeatureListProductAdapter(getActivity(), featurePOJO.getRelatedPOJOs());
                        LinearLayoutManager horizontalLayoutManagaer
                                = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        rv_related_products.setLayoutManager(horizontalLayoutManagaer);
                        rv_related_products.setAdapter(adapter);
                        ll_related.setVisibility(View.VISIBLE);
                    } else {
                        ll_related.setVisibility(View.GONE);
                    }

                } else {
                    ll_related.setVisibility(View.GONE);
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            ll_related.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetMsg(String[] msg) {
        String apicall = msg[0];
        String response = msg[1];
        switch (apicall) {
            case PRODUCT_VIEW_API:
//                parseProductViewData(response);
                parseNewProductViewData(response);
                break;
            case ADD_TO_WISHLIST_API:
                parseAddToWishListAPI(response);
                break;
            case RELATED_PRODUCT_API:
                parseFeatureApiData(response);
                break;
            case USER_REVIEW_API:
                parseUserReviewData(response);
                break;
            case ADD_REVIEW_API:
                parseReviews(response);
                break;
            case ADD_TO_CART_API:
                parseAddToCartAPI(response);
                break;
        }
    }

    public void parseAddToCartAPI(String response){
        //Log.d(TAG,"Add to cart:-"+response);
        try{
            Gson gson=new Gson();
            AddToCartPOJO addToCartPOJO=gson.fromJson(response,AddToCartPOJO.class);
            if(addToCartPOJO.getSuccess().equals("true")){
                ToastClass.showShortToast(getActivity().getApplicationContext(),"Product Added to cart");
            }else{
                ToastClass.showShortToast(getActivity().getApplicationContext(),"Failed To Add");
            }
        }catch (Exception e){
            e.printStackTrace();
            ToastClass.showShortToast(getActivity().getApplicationContext(),"Failed To Add");
        }
    }

    public void parseReviews(String response){
        //Log.d(TAG,"Review response:-"+response);
        try{
            JSONObject jsonObject=new JSONObject(response);
            if(jsonObject.optString("Success").equals("true")){
                ToastClass.showShortToast(getActivity().getApplicationContext(),"Review Added");
                callReviewsAPI(product_id);
            }else{
                ToastClass.showShortToast(getActivity().getApplicationContext(),"Something went wrong");
            }
        }catch (Exception e){
            e.printStackTrace();
            ToastClass.showShortToast(getActivity().getApplicationContext(),"Something went wrong");
        }
    }

    public void parseUserReviewData(String response) {
        //Log.d(TAG, "user review response:-" + response);
        try {
            Gson gson = new Gson();
            UserReviewPOJO userReviewPOJO = gson.fromJson(response, UserReviewPOJO.class);
            if (userReviewPOJO.getSuccess().equals("true")) {
                tv_no_reviews.setVisibility(View.GONE);
                inflateUserReviewResponse(userReviewPOJO.getUserReviewResultPOJOList());
            } else {
                tv_no_reviews.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            tv_no_reviews.setVisibility(View.VISIBLE);
        }
    }

    public void inflateUserReviewResponse(List<UserReviewResultPOJO> userReviewResultPOJOList) {
        ll_review_scroll.removeAllViews();
        for (int i = 0; i < userReviewResultPOJOList.size(); i++) {
            final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.inflate_user_reviews, null);
            TextView tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
            TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
            TextView tv_review = (TextView) view.findViewById(R.id.tv_review);


            tv_user_name.setText(userReviewResultPOJOList.get(i).getNickname());
            tv_date.setText(userReviewResultPOJOList.get(i).getCreated_at());
            tv_review.setText(userReviewResultPOJOList.get(i).getDetail());

            ll_review_scroll.addView(view);
        }
    }

    public void parseAddToWishListAPI(String response) {
        ////Log.d(TAG, "add to wishlist response:-" + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("success").equals("true")) {
                ToastClass.showLongToast(getActivity().getApplicationContext(), "Product Added To Wishlist");
            } else {
                ToastClass.showLongToast(getActivity().getApplicationContext(), "Product not added");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastClass.showLongToast(getActivity().getApplicationContext(), "Somthing went wrong");
        }
    }
    public void setUpViewPager(final List<String> imageIdList) {
        image_pager.setAdapter(new CustomswipeAdapter(getActivity(), imageIdList));
        image_pager.setOffscreenPageLimit(imageIdList.size());
//        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

//        viewPager.setInterval(2000);
//        viewPager.startAutoScroll();
//        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageIdList.size());
        if (imageIdList.size() > 0) {
            new CountDownTimer(999999999, 3000) {

                @Override
                public void onTick(long l) {
                    if ((image_pager.getCurrentItem() + 1) == imageIdList.size()) {
                        image_pager.setCurrentItem(0);
                    } else {
                        image_pager.setCurrentItem(image_pager.getCurrentItem() + 1);
                    }
                }

                @Override
                public void onFinish() {
                    start();
                }
            }.start();
        }
    }

    public void parseNewProductViewData(String response){
        try{
            Gson gson=new Gson();
            NewProductPOJO newProductPOJO=gson.fromJson(response,NewProductPOJO.class);
            if(newProductPOJO.getSuccess().equals("true")){

                final ProductPOJO productPOJO=new ProductPOJO();


                List<NewProductResultPOJO> newProductResultPOJOList=newProductPOJO.getNewProductResultPOJOList();
                for(NewProductResultPOJO newProductResultPOJO:newProductResultPOJOList){
                    switch (newProductResultPOJO.getAttribute_id()){
                        case "71":
                            productPOJO.setName(newProductResultPOJO.getValue());
                            productPOJO.setShort_description(newProductResultPOJO.getDescription());
                            productPOJO.setSku(newProductResultPOJO.getSku());
                            productPOJO.setBook_authors(newProductResultPOJO.getAuthor_name());
                            productPOJO.setStatus(newProductResultPOJO.getIs_in_stock());
                            break;
                        case "85":
                            productPOJO.setSmall_image(newProductResultPOJO.getValue());
                            productPOJO.setThumbnail(newProductResultPOJO.getValue());
                            break;
                        case "223":
                            productPOJO.setBook_authors(newProductResultPOJO.getAuthor_name());
                            break;
                    }
                }
                Log.d(TAG,"productpojo:-"+productPOJO.toString());
                productPOJO.setPrice(newProductPOJO.getNewPricePOJO().getDiscount_price());

                tv_book_name.setText(productPOJO.getName());

//                Glide.with(getActivity().getApplicationContext())
//                        .load(WebServicesUrls.IMAGE_BASE_URL + productPOJO.getSmall_image())
//                        .into(iv_image_pic);
                try {
                    List<String> images = new ArrayList<>();
                    for (NewProductGalleryPOJO newProductGalleryPOJO : newProductPOJO.getNewProductGalleryPOJOList()) {
                        images.add(WebServicesUrls.IMAGE_BASE_URL + newProductGalleryPOJO.getValue());
                    }
                    setUpViewPager(images);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
                tv_author.setText(productPOJO.getBook_authors());
                iv_decrease_product.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (tv_book_quantity.getText().toString().equals("1")) {

                        } else {
                            try {
                                int val = Integer.parseInt(tv_book_quantity.getText().toString());
                                val = val - 1;
                                tv_book_quantity.setText(String.valueOf(val));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                iv_increase_product.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            int val = Integer.parseInt(tv_book_quantity.getText().toString());
                            val = val + 1;
                            tv_book_quantity.setText(String.valueOf(val));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                tv_quick_overview.setText(Html.fromHtml(productPOJO.getShort_description()));
                String currency=Pref.GetCurrency(getActivity().getApplicationContext());
                if(featureDataPOJO!=null) {

                    if (featureDataPOJO.getDiscount_price() != null) {
                        String original_price = "<s>"+currency+" " + getConvertedPrice(featureDataPOJO.getMain_price()) + "</u>";
                        tv_discount_price.setText(currency+" " + getConvertedPrice(featureDataPOJO.getDiscount_price()));

                        tv_price.setText(Html.fromHtml(original_price));
                        tv_discount_price.setVisibility(View.VISIBLE);
                    } else {
                        tv_discount_price.setVisibility(View.GONE);
                        tv_price.setText(currency+" " + getConvertedPrice(productPOJO.getPrice()));
                    }
                }else{
                    if(newProductPOJO.getNewPricePOJO()!=null){
                        String original_price = "<s>"+currency+" " + getConvertedPrice(newProductPOJO.getNewPricePOJO().getMain_price()) + "</u>";
                        tv_discount_price.setText(currency+" " + getConvertedPrice(newProductPOJO.getNewPricePOJO().getDiscount_price()));

                        tv_price.setText(Html.fromHtml(original_price));
                        tv_discount_price.setVisibility(View.VISIBLE);
                    }else {
                        tv_price.setText(currency+" " + getConvertedPrice(productPOJO.getPrice()));
                    }
                }
                tv_sku.setText(productPOJO.getSku());




                ll_nav_cat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            HomeActivity homeActivity = (HomeActivity) getActivity();
                            homeActivity.openCatNavDrawer();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                final String finalProduct_id = product_id;
                //Log.d(TAG,"product id:-"+finalProduct_id);
                if (databaseHelper.getWishListData(finalProduct_id) != null) {
                    //Log.d(TAG,"product data:-"+databaseHelper.getWishListData(finalProduct_id).toString());
                    tv_add_to_wish_list.setText("Remove From WishList");
                } else {
                    tv_add_to_wish_list.setText("Add To WishList");
                    //Log.d(TAG,"product data:-not added");
                }
                final String add_to_text = tv_add_to_wish_list.getText().toString();
                tv_add_to_wish_list.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HomeActivity homeActivity = (HomeActivity) getActivity();
                        if (add_to_text.equals("Add To WishList")) {
                            homeActivity.AddToWishList(finalProduct_id, tv_book_quantity.getText().toString(), tv_add_to_wish_list);
                        } else {
                            homeActivity.DeleteFromWishList(finalProduct_id, tv_add_to_wish_list);
                        }
                    }
                });
                final CartResultPOJO cartResultPOJO=databaseHelper.getCartData(product_id);
                if(cartResultPOJO!=null){
                    Log.d(TAG,"product cart pojo:-"+cartResultPOJO.toString());
                    btn_add_to_cart.setText("Remove From Cart");
                }else{
                    btn_add_to_cart.setText("Add To Cart");
                }
                btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Pref.GetBooleanPref(getActivity().getApplicationContext(),StringUtils.IS_LOGIN,false)) {
                            if (btn_add_to_cart.getText().toString().equalsIgnoreCase("Add To Cart")) {

                                HomeActivity activity = (HomeActivity) getActivity();
                                String price = "";
                                if (featureDataPOJO != null) {
                                    if (featureDataPOJO.getDiscount_price() != null) {
                                        price = featureDataPOJO.getDiscount_price();
                                    } else {
                                        price = featureDataPOJO.getPrice();
                                    }
                                } else {
                                    price = productPOJO.getPrice();
                                }
                                Log.d(TagUtils.getTag(),"product price:-"+price);
                                activity.addToCart(product_id, productPOJO.getSku(), productPOJO.getName(),
                                        "", tv_book_quantity.getText().toString(),
                                        price, price, btn_add_to_cart,false);
                            } else {
                                try {
                                    Log.d(TAG, "productId:-" + product_id);
                                    CartResultPOJO cartResultPOJO1 = databaseHelper.getCartData(product_id);
                                    HomeActivity activity = (HomeActivity) getActivity();
                                    activity.callCartSingleItemDelete(cartResultPOJO1.getCart_id(),
                                            cartResultPOJO1.getProduct_id(), btn_add_to_cart);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ToastClass.showShortToast(getActivity().getApplicationContext(), "please try again later.");
                                }
                            }
                        }
                        else{
                            startActivity(new Intent(getActivity(),LoginActivity.class));
                        }
                    }
                });
                btn_buy_now.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Pref.GetBooleanPref(getActivity().getApplicationContext(),StringUtils.IS_LOGIN,false)) {
                            if (btn_add_to_cart.getText().toString().equalsIgnoreCase("Add To Cart")) {

                                HomeActivity activity = (HomeActivity) getActivity();
                                String price = "";
                                if (featureDataPOJO != null) {
                                    if (featureDataPOJO.getDiscount_price() != null) {
                                        price = featureDataPOJO.getDiscount_price();
                                    } else {
                                        price = featureDataPOJO.getPrice();
                                    }
                                } else {
                                    price = productPOJO.getPrice();
                                }
                                activity.addToCart(product_id, productPOJO.getSku(), productPOJO.getName(),
                                        "", tv_book_quantity.getText().toString(),
                                        price, price, btn_add_to_cart,true);
                            } else {
                                HomeActivity homeActivity= (HomeActivity) getActivity();
                                homeActivity.showCartFragment();
//                                try {
//                                    Log.d(TAG, "productId:-" + product_id);
//                                    CartResultPOJO cartResultPOJO1 = databaseHelper.getCartData(product_id);
//                                    HomeActivity activity = (HomeActivity) getActivity();
//                                    activity.callCartSingleItemDelete(cartResultPOJO1.getCart_id(),
//                                            cartResultPOJO1.getProduct_id(), btn_add_to_cart);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    ToastClass.showShortToast(getActivity().getApplicationContext(), "please try again later.");
//                                }
                            }
                        }
                        else{
                            startActivity(new Intent(getActivity(),LoginActivity.class));
                        }
                    }
                });
                if (productPOJO.getStatus().equals("1")) {
                    tv_availability.setText("In stock");
                    btn_add_to_cart.setVisibility(View.VISIBLE);
                    tv_add_to_wish_list.setVisibility(View.VISIBLE);
                    btn_buy_now.setVisibility(View.VISIBLE);
                } else {
                    tv_availability.setText("Out of stock");
                    btn_add_to_cart.setVisibility(View.GONE);
                    tv_add_to_wish_list.setVisibility(View.GONE);
                    btn_buy_now.setVisibility(View.GONE);
                }

            }else{

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


//
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


    private static SpannableStringBuilder addClickablePartTextViewResizable(
            final String strSpanned, final TextView tv, final int maxLine,
            final String spanableText, final boolean viewMore) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (strSpanned.contains(spanableText)) {
            ssb.setSpan(
                    new ClickableSpan() {

                        @Override
                        public void onClick(View widget) {

                            if (viewMore) {
                                tv.setLayoutParams(tv.getLayoutParams());
                                tv.setText(tv.getTag().toString(),
                                        TextView.BufferType.SPANNABLE);
                                tv.invalidate();
                                makeTextViewResizable(tv, -5, "...Read Less",
                                        false);
                                tv.setTextColor(Color.BLACK);
                            } else {
                                tv.setLayoutParams(tv.getLayoutParams());
                                tv.setText(tv.getTag().toString(),
                                        TextView.BufferType.SPANNABLE);
                                tv.invalidate();
                                makeTextViewResizable(tv, 5, "...Read More",
                                        true);
                                tv.setTextColor(Color.BLACK);
                            }

                        }
                    }, strSpanned.indexOf(spanableText),
                    strSpanned.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }
    public static void makeTextViewResizable(final TextView tv,
                                             final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0,
                            lineEndIndex - expandText.length() + 1)
                            + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(tv.getText()
                                            .toString(), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0,
                            lineEndIndex - expandText.length() + 1)
                            + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(tv.getText()
                                            .toString(), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(
                            tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex)
                            + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(tv.getText()
                                            .toString(), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }











//    public void parseProductViewData(String response) {
//        Log.d(TAG,"product view response:-"+response);
//        try {
//            Gson gson = new Gson();
//            ProductResponsePOJO productResponsePOJO = gson.fromJson(response, ProductResponsePOJO.class);
//            if (productResponsePOJO.getSuccess().equals("true")) {
//                final ProductPOJO productPOJO = productResponsePOJO.getProductPOJOList().get(0);
//
//                tv_book_name.setText(productPOJO.getName());
//
////                Glide.with(getActivity().getApplicationContext())
////                        .load(WebServicesUrls.IMAGE_BASE_URL + productPOJO.getSmall_image())
////                        .into(iv_image_pic);
//                List<String> images=new ArrayList<>();
//                for(ProductGallery productGallery:productResponsePOJO.getProductGalleryList()){
//                    images.add(WebServicesUrls.IMAGE_BASE_URL+productGallery.getValue());
//                }
//                setUpViewPager(images);
//                iv_decrease_product.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (tv_book_quantity.getText().toString().equals("1")) {
//
//                        } else {
//                            try {
//                                int val = Integer.parseInt(tv_book_quantity.getText().toString());
//                                val = val - 1;
//                                tv_book_quantity.setText(String.valueOf(val));
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                });
//                iv_increase_product.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        try {
//                            int val = Integer.parseInt(tv_book_quantity.getText().toString());
//                            val = val + 1;
//                            tv_book_quantity.setText(String.valueOf(val));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//                tv_quick_overview.setText(Html.fromHtml(productPOJO.getShort_description()));
//                String currency=Pref.GetCurrency(getActivity().getApplicationContext());
//                if(featureDataPOJO!=null) {
//
//                    if (featureDataPOJO.getDiscount_price() != null) {
//                        String original_price = "<s>"+currency+" " + getConvertedPrice(featureDataPOJO.getMain_price()) + "</u>";
//                        tv_discount_price.setText(currency+" " + getConvertedPrice(featureDataPOJO.getDiscount_price()));
//
//                        tv_price.setText(Html.fromHtml(original_price));
//                        tv_discount_price.setVisibility(View.VISIBLE);
//                    } else {
//                        tv_discount_price.setVisibility(View.GONE);
//                        tv_price.setText(currency+" " + getConvertedPrice(productPOJO.getPrice()));
//                    }
//                }else{
//                    if(productResponsePOJO.getPricePOJO()!=null){
//                        String original_price = "<s>"+currency+" " + getConvertedPrice(productResponsePOJO.getPricePOJO().getMain_price()) + "</u>";
//                        tv_discount_price.setText(currency+" " + getConvertedPrice(productResponsePOJO.getPricePOJO().getDiscount_price()));
//
//                        tv_price.setText(Html.fromHtml(original_price));
//                        tv_discount_price.setVisibility(View.VISIBLE);
//                    }else {
//                        tv_price.setText(currency+" " + getConvertedPrice(productPOJO.getPrice()));
//                    }
//                }
//                tv_sku.setText(productPOJO.getSku());
//
//
//                if (productPOJO.getStatus().equals("1")) {
//                    tv_availability.setText("In stock");
//                } else {
//                    tv_availability.setText("Not Available");
//                }
//
//                ll_nav_cat.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        try {
//                            HomeActivity homeActivity = (HomeActivity) getActivity();
//                            homeActivity.openCatNavDrawer();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                final String finalProduct_id = product_id;
//                //Log.d(TAG,"product id:-"+finalProduct_id);
//                if (databaseHelper.getWishListData(finalProduct_id) != null) {
//                    //Log.d(TAG,"product data:-"+databaseHelper.getWishListData(finalProduct_id).toString());
//                    tv_add_to_wish_list.setText("Remove From WishList");
//                } else {
//                    tv_add_to_wish_list.setText("Add To WishList");
//                    //Log.d(TAG,"product data:-not added");
//                }
//                final String add_to_text = tv_add_to_wish_list.getText().toString();
//                tv_add_to_wish_list.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        HomeActivity homeActivity = (HomeActivity) getActivity();
//                        if (add_to_text.equals("Add To WishList")) {
//                            homeActivity.AddToWishList(finalProduct_id, tv_book_quantity.getText().toString(), tv_add_to_wish_list);
//                        } else {
//                            homeActivity.DeleteFromWishList(finalProduct_id, tv_add_to_wish_list);
//                        }
//                    }
//                });
//                final CartResultPOJO cartResultPOJO=databaseHelper.getCartData(product_id);
//                if(cartResultPOJO!=null){
//                    Log.d(TAG,"product cart pojo:-"+cartResultPOJO.toString());
//                    btn_add_to_cart.setText("Remove From Cart");
//                }else{
//                    btn_add_to_cart.setText("Add To Cart");
//                }
//                btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if(Pref.GetBooleanPref(getActivity().getApplicationContext(),StringUtils.IS_LOGIN,false)) {
//                            if (btn_add_to_cart.getText().toString().equalsIgnoreCase("Add To Cart")) {
//
//                                HomeActivity activity = (HomeActivity) getActivity();
//                                String price = "";
//                                if (featureDataPOJO != null) {
//                                    if (featureDataPOJO.getDiscount_price() != null) {
//                                        price = featureDataPOJO.getDiscount_price();
//                                    } else {
//                                        price = featureDataPOJO.getPrice();
//                                    }
//                                } else {
//                                    price = productPOJO.getPrice();
//                                }
//                                activity.addToCart(product_id, productPOJO.getSku(), productPOJO.getName(),
//                                        "", tv_book_quantity.getText().toString(),
//                                        price, price, btn_add_to_cart);
////                        addToCart(productPOJO);
//                            } else {
//                                try {
//                                    Log.d(TAG, "productId:-" + product_id);
//                                    CartResultPOJO cartResultPOJO1 = databaseHelper.getCartData(product_id);
//                                    HomeActivity activity = (HomeActivity) getActivity();
//                                    activity.callCartSingleItemDelete(cartResultPOJO1.getCart_id(),
//                                            cartResultPOJO1.getProduct_id(), btn_add_to_cart);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    ToastClass.showShortToast(getActivity().getApplicationContext(), "please try again later.");
//                                }
//                            }
//                        }
//                        else{
//                            startActivity(new Intent(getActivity(),LoginActivity.class));
//                        }
//                    }
//                });
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
