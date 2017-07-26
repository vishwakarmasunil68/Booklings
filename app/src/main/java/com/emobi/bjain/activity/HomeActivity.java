package com.emobi.bjain.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emobi.bjain.R;
import com.emobi.bjain.adapter.CustomswipeAdapter;
import com.emobi.bjain.adapter.FeatureListProductAdapter;
import com.emobi.bjain.adapter.FeatureProductAdapter;
import com.emobi.bjain.database.DatabaseHelper;
import com.emobi.bjain.fragment.CartFragment;
import com.emobi.bjain.fragment.CategoryProductFragment;
import com.emobi.bjain.fragment.ProductViewFragment;
import com.emobi.bjain.fragment.ScannerFragment;
import com.emobi.bjain.fragment.SearchFragment;
import com.emobi.bjain.fragment.ShopByCategoriesFragment;
import com.emobi.bjain.fragment.ViewAllFragment;
import com.emobi.bjain.fragment.WishListFragment;
import com.emobi.bjain.pojo.CategoryPOJO;
import com.emobi.bjain.pojo.ChildrenPOJO;
import com.emobi.bjain.pojo.cart.CartResultPOJO;
import com.emobi.bjain.pojo.featurelist.FeatureDataPOJO;
import com.emobi.bjain.pojo.featurelist.FeaturePOJO;
import com.emobi.bjain.pojo.newcategory.NewCategoryDataPOJO;
import com.emobi.bjain.pojo.newcategory.NewCategoryPOJO;
import com.emobi.bjain.pojo.newcategory.NewCategoryResultPOJO;
import com.emobi.bjain.pojo.offer.OfferPOJO;
import com.emobi.bjain.pojo.recentview.RecentViewPOJO;
import com.emobi.bjain.pojo.wishlist.WishListResultPOJO;
import com.emobi.bjain.utils.GPSTracker;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.StringUtils;
import com.emobi.bjain.utils.TagUtils;
import com.emobi.bjain.utils.ToastClass;
import com.emobi.bjain.webservice.GetWebServices;
import com.emobi.bjain.webservice.WebServiceBase;
import com.emobi.bjain.webservice.WebServiceCallBackView;
import com.emobi.bjain.webservice.WebServicesCallBack;
import com.emobi.bjain.webservice.WebServicesUrls;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements WebServicesCallBack, WebServiceCallBackView, FloatingActionsMenu.OnFloatingActionsMenuUpdateListener {

    private static final String RECENT_VIEW = "recent_view";
    private static final String RECENT_VIEW_ALL = "recent_view_all";
    private static final String BUY_CART_API = "buy_cart_api";
    private final String TAG = getClass().getSimpleName();
    private final static int BARCODE_SCANNER_RESULT = 102;


    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView nvDrawer;
    private Toolbar toolbar;
    RecyclerView rv_feature_products, rv_offer_deals, rv_recent_view;
    @BindView(R.id.ic_ham)
    ImageView ic_ham;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_feature_list)
    TextView tv_feature_list;
    @BindView(R.id.tv_offer_products)
    TextView tv_offer_products;
    @BindView(R.id.tv_profile_name_toolbar)
    TextView tv_profile_name_toolbar;
    @BindView(R.id.frame_main)
    FrameLayout frame_main;
    @BindView(R.id.ll_account)
    LinearLayout ll_account;
    @BindView(R.id.ll_barcode_scan)
    LinearLayout ll_barcode_scan;
    @BindView(R.id.fab_contact_us)
    FloatingActionButton fab_contact_us;
    @BindView(R.id.ic_fav)
    ImageView ic_fav;
    @BindView(R.id.frame_container)
    FrameLayout frame_container;
    @BindView(R.id.ll_products)
    LinearLayout ll_products;
    @BindView(R.id.iv_cart)
    ImageView iv_cart;
    @BindView(R.id.tv_recent_view_all)
    TextView tv_recent_view_all;
    @BindView(R.id.ll_search)
    LinearLayout ll_search;
    @BindView(R.id.addFabLayoutContainer)
    LinearLayout addFabLayoutContainer;
    @BindView(R.id.addFAB)
    FloatingActionsMenu addFabMenu;

//    @BindView(R.id.fab_home_menu)
//    FloatingActionMenu fab_home_menu;

    private final String IMAGE_SLIDER_API_CALL = "image_slider_apicall";
    private final String CATEGORY_API_CALL = "category_api_call";
    private final String FEATURE_API_CALL = "feature_api_call";
    private final String OFFER_API_CALL = "offer_api_call";
    private final String ADD_TO_CART_API = "add_to_cart_api";
    private final String DELETE_SINGLE_ITEM = "delete_single_item";


    DatabaseHelper databaseHelper;

    List<Fragment> activeCenterFragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        settingNavDrawer();
//        setUpViewPager();
        new GetWebServices(this, IMAGE_SLIDER_API_CALL, true).execute(WebServicesUrls.NEW_IMAGE_SLIDER_URL);


        try {
            if (Pref.GetStringPref(getApplicationContext(), StringUtils.CATEGORY_DATA, "").equals("")) {

            } else {
                parseCategoryData(Pref.GetStringPref(getApplicationContext(), StringUtils.CATEGORY_DATA, ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        new GetWebServices(this, CATEGORY_API_CALL, true).execute(WebServicesUrls.CATEGORY_URL);


        setUpRecyclerViews();

        databaseHelper = new DatabaseHelper(this);
        ll_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
                    startActivity(new Intent(HomeActivity.this, MyAccountActivity.class));
                } else {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
            }
        });
        ll_barcode_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBarcodeFragment();
            }
        });
        fab_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ContactUsActivity.class));
            }
        });
        ic_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
                    removeActiveCenterFragments();
                    showWishListFragment();
                } else {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
            }
        });
        iv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
                    removeActiveCenterFragments();
                    showCartFragment();
                } else {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
            }
        });

        Log.d(TagUtils.getTag(), "ids:-" + Pref.GetStringPref(getApplicationContext(), StringUtils.ENTITY_ID, ""));
        Log.d(TagUtils.getTag(), "ids:-" + Pref.GetStringPref(getApplicationContext(), StringUtils.QUOTOID, ""));


        tv_recent_view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recentViewALL != null) {
                    FeaturePOJO featurePOJO = new FeaturePOJO();
                    featurePOJO.setFeatureDataPOJOList(recentViewALL);
                    showViewAllFragment(featurePOJO);
                } else {
                    callAllRecentViewAPI();
                }
            }
        });
//        getLocation();
        refreshAllViews();

        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchFragment();
            }
        });

//        fab_home_menu.setClosedOnTouchOutside(true);

        getLocation();

        addFabMenu.setOnFloatingActionsMenuUpdateListener(this);
        printHashKey();
        new GetVersionCode().execute();
    }


    private class GetVersionCode extends AsyncTask<Void, String, String> {
        @Override
        protected String doInBackground(Void... voids) {

            String newVersion = null;
            try {
                newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + HomeActivity.this.getPackageName() + "&hl=it")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div[itemprop=softwareVersion]")
                        .first()
                        .ownText();
                return newVersion;
            } catch (Exception e) {
                return newVersion;
            }
        }

        @Override
        protected void onPostExecute(String onlineVersion) {
            super.onPostExecute(onlineVersion);
            try {
                String currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                if (onlineVersion != null && !onlineVersion.isEmpty()) {
                    if (Float.valueOf(currentVersion) < Float.valueOf(onlineVersion)) {
                        //show dialog
                        showUpdateDialog();
                    }
                }
                Log.d("update", "Current version " + currentVersion + "playstore version " + onlineVersion);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void showUpdateDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Update your App")
                .setCancelable(false)
                .setTitle("Alert")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                        finishAffinity();
                    }
                }).setNegativeButton("exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }



    public void printHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.emobi.bjain",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
    ScannerFragment scannerFragment;
    public void showBarcodeFragment(){
        scannerFragment= new ScannerFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_main, scannerFragment, "scannerFragment");
        transaction.addToBackStack(null);
        transaction.commit();
        activeCenterFragments.add(scannerFragment);
    }


    GPSTracker gps;

    public void getLocation() {
        gps = new GPSTracker(HomeActivity.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Log.d(TAG, "location:-latitude:-" + latitude);
            Log.d(TAG, "location:-longitude:-" + longitude);
            LatLng latlng = new LatLng(latitude, longitude);
            String countryName = getCountryName(latitude, longitude);
            Log.d(TAG, "location:-countryName" + countryName);
            if (countryName.equals("") || countryName.equalsIgnoreCase("india")) {
                Pref.SetCurrency(getApplicationContext(), "INR");
            } else {
                Pref.SetCurrency(getApplicationContext(), "USD");
            }
        } else {
            gps.showSettingsAlert();
        }
    }

    @Override
    public void onMenuExpanded() {
        addFabLayoutContainer.setBackgroundColor(Color.parseColor("#00F0F8FF"));
        addFabLayoutContainer.setClickable(true);
        addFabLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFabMenu.collapse();
            }
        });
    }

    @Override
    public void onMenuCollapsed() {
        addFabLayoutContainer.setBackgroundColor(Color.TRANSPARENT);
        addFabLayoutContainer.setClickable(false);
    }

    public String getCountryName(double latitude, double longitude) {
        String address = "";
//                    LocationAddress.getAddressFromLocation(latitude,longitude,LocationService.this,new GeocoderHandler());
        Geocoder geocoder = new Geocoder(HomeActivity.this, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address returnedAddress = addresses.get(0);

                StringBuilder strReturnedAddress = new StringBuilder();
                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                Log.d("sunil", strReturnedAddress.toString());
//                address = strReturnedAddress.toString();
                return returnedAddress.getCountryName();
            } else {
                Log.d("sunil", "No Address returned!");
                return "";
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("sunil", "Canont get Address!");
            return "";
        }
    }

    public void refreshAllViews() {

        try {
//            String feature_response = Pref.GetStringPref(getApplicationContext(), StringUtils.FEATURE_RESPONSE, "");
//            if (!feature_response.equals("")) {
//                parseNewFeatureAPIData(feature_response);
//            }
            FeaturePOJO featurePOJO = Pref.GetPOJO(getApplicationContext(), StringUtils.FEATURE_TYPE);
            mainfeatureDataPOJOList = featurePOJO.getFeatureDataPOJOList();
            LoadFeatureRV();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
//            String offer_response = Pref.GetStringPref(getApplicationContext(), StringUtils.OFFER_RESPONSE, "");
//            if (!offer_response.equals("")) {
//                parseNewOfferData(offer_response,true);
//            }
            FeaturePOJO featurePOJO = Pref.GetPOJO(getApplicationContext(), StringUtils.OFFER_TYPE);
            offerDataPOJOList = featurePOJO.getFeatureDataPOJOList();
            LoadOFFerRV();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
//            String recent_response = Pref.GetStringPref(getApplicationContext(), StringUtils.RECENT_RESPONSE, "");
//            if (!recent_response.equals("")) {
//                parseRecentViewData(recent_response);
//            }
            FeaturePOJO featurePOJO = Pref.GetPOJO(getApplicationContext(), StringUtils.RECENT_TYPE);
            recentDataPOJOs = featurePOJO.getFeatureDataPOJOList();
            loadRecentRV();
        } catch (Exception e) {
            e.printStackTrace();
        }


        callRecentViewAPi();
        callFeautrListAPI();
        callOfferAPI();
        removeActiveCenterFragments();
    }


    public void callOfferAPI() {
        Log.d(TAG, "offer called");
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("per_p_id", "538"));
        nameValuePairs.add(new BasicNameValuePair("type", Pref.GetCurrency(getApplicationContext())));
        new WebServiceBase(nameValuePairs, this, OFFER_API_CALL, false).execute(WebServicesUrls.NEW_OFFER_PRODUCT_LIST_API);
//        parseOfferAPIData(Pref.offer_products);
    }

    public void callFeautrListAPI() {
        boolean is_flag = false;
        if (Pref.GetStringPref(getApplicationContext(), StringUtils.FEATURE_RESPONSE, "").equals("")) {
            is_flag = true;
        } else {
            is_flag = false;
        }
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("feture_p_id", "537"));
        nameValuePairs.add(new BasicNameValuePair("type", Pref.GetCurrency(getApplicationContext())));
        new WebServiceBase(nameValuePairs, this, FEATURE_API_CALL, is_flag).execute(WebServicesUrls.NEW_FEATURE_PRODUCT_LIST_URL);
        //Log.d(TAG,"feature list:-"+Pref.feature_string);
//        parseFeatureApiData(Pref.feature_string);
    }

    public void callRecentViewAPi() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("type", Pref.GetCurrency(getApplicationContext())));
        new WebServiceBase(nameValuePairs, this, RECENT_VIEW, false).execute(WebServicesUrls.RECENT_VIEW_URL);
        //Log.d(TAG,"feature list:-"+Pref.feature_string);
//        parseFeatureApiData(Pref.feature_string);
    }

    public void callAllRecentViewAPI() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("type", Pref.GetCurrency(getApplicationContext())));
        new WebServiceBase(nameValuePairs, this, RECENT_VIEW_ALL).execute(WebServicesUrls.RECENT_VIEW_ALL_URL);
        //Log.d(TAG,"feature list:-"+Pref.feature_string);
//        parseFeatureApiData(Pref.feature_string);
    }


    public void setUpViewPager(final List<String> imageIdList) {
//        List<String> imageIdList = new ArrayList<>();
//        imageIdList.add("http://www.bjain.com/bookstore/media/em_minislideshow/1492172077_1_333.jpg");
//        imageIdList.add("http://www.bjain.com/bookstore/media/em_minislideshow/1492172077_1_333.jpg");
//        imageIdList.add("http://www.bjain.com/bookstore/media/em_minislideshow/1492172077_1_333.jpg");
//        imageIdList = new ArrayList<>();
//        imageIdList.add(R.drawable.ic_action_navigation_more_vert);
//        imageIdList.add(R.drawable.ic_add_cart);
//        imageIdList.add(R.drawable.ic_barcode_scanner);
//        Integer images[]=new Integer[]{R.drawable.ic_action_navigation_more_vert,R.drawable.ic_add_cart,R.drawable.ic_barcode_scanner};

        viewPager.setAdapter(new CustomswipeAdapter(this, imageIdList));
        viewPager.setOffscreenPageLimit(imageIdList.size());
//        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

//        viewPager.setInterval(2000);
//        viewPager.startAutoScroll();
//        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageIdList.size());
        if (imageIdList.size() > 0) {
            new CountDownTimer(999999999, 5000) {

                @Override
                public void onTick(long l) {
                    if ((viewPager.getCurrentItem() + 1) == imageIdList.size()) {
                        viewPager.setCurrentItem(0);
                    } else {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                }

                @Override
                public void onFinish() {
                    start();
                }
            }.start();
        }
    }

    @Override
    public void onGetMsg(String[] msg) {
        String apicall = msg[0];
        String resoponse = msg[1];
        switch (apicall) {
            case IMAGE_SLIDER_API_CALL:
                parseNewSliderData(resoponse);
//                parseImageSliderData(resoponse);
                break;
            case CATEGORY_API_CALL:
                parseCategoryData(resoponse);
                break;
            case FEATURE_API_CALL:
                parseNewFeatureAPIData(resoponse);
//                parseFeatureApiData(resoponse);
                break;
            case OFFER_API_CALL:
                parseNewOfferData(resoponse, false);
//                parseOfferAPIData(resoponse);
                break;

            case RECENT_VIEW:
                parseRecentViewData(resoponse);
                break;
            case RECENT_VIEW_ALL:
                parseRecentViewAll(resoponse);
                break;
        }
    }

    List<FeatureDataPOJO> recentViewALL;

    public void parseNewOfferData(final String response, final boolean cond) {

        new AsyncTask<Void, Void, Void>() {
            List<FeatureDataPOJO> featureDataPOJOList = new ArrayList<>();
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (cond) {
                    progressDialog = new ProgressDialog(HomeActivity.this);
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            }

            @Override
            protected Void doInBackground(Void... params) {
                Log.d(TAG, "offer response:-" + response);
                try {
                    Gson gson = new Gson();
                    OfferPOJO offerPOJO = gson.fromJson(response, OfferPOJO.class);
                    if (offerPOJO.getSuccess().equals("true")) {

                        List<NewCategoryResultPOJO> newCategoryResultPOJOList = new ArrayList<>();

                        for (String s : offerPOJO.getOfferStringList()) {
                            if (!s.equals("false")) {
                                Gson gson1 = new Gson();
                                NewCategoryResultPOJO newCategoryResultPOJO = gson1.fromJson(s, NewCategoryResultPOJO.class);
                                newCategoryResultPOJOList.add(newCategoryResultPOJO);
                            }
                        }

                        Set<String> stringSet = new HashSet<>();
                        for (NewCategoryResultPOJO newCategoryResultPOJO : newCategoryResultPOJOList) {
                            stringSet.add(newCategoryResultPOJO.getEntityId());
                        }

                        List<NewCategoryDataPOJO> newCategoryDataPOJOList = new ArrayList<>();

                        for (String s : stringSet) {
                            NewCategoryDataPOJO newCategoryDataPOJO = new NewCategoryDataPOJO();
                            FeatureDataPOJO featureDataPOJO = new FeatureDataPOJO();

                            for (NewCategoryResultPOJO newCategoryResultPOJO : newCategoryResultPOJOList) {
                                if (s.equals(newCategoryResultPOJO.getEntityId())) {
                                    switch (newCategoryResultPOJO.getAttributeId()) {
                                        case "71":
                                            newCategoryDataPOJO.setProduct_name(newCategoryResultPOJO.getValue());
                                            newCategoryDataPOJO.setProduct_id(newCategoryResultPOJO.getProductId());
                                            newCategoryDataPOJO.setDiscount_price(newCategoryResultPOJO.getDiscountPrice());
                                            newCategoryDataPOJO.setMain_price(newCategoryResultPOJO.getMainPrice());

                                            featureDataPOJO.setProduct_id(newCategoryResultPOJO.getProductId());
                                            featureDataPOJO.setName(newCategoryResultPOJO.getValue());
                                            featureDataPOJO.setMain_price(newCategoryResultPOJO.getMainPrice());
                                            featureDataPOJO.setPrice(newCategoryResultPOJO.getMainPrice());
                                            featureDataPOJO.setDiscount_price(newCategoryResultPOJO.getDiscountPrice());
                                            featureDataPOJO.setSku("");
                                            featureDataPOJO.setWeight("");
                                            featureDataPOJO.setAuthor_name("");
                                            featureDataPOJO.setShort_description("");


                                            break;
                                        case "85":
                                            newCategoryDataPOJO.setProduct_image(newCategoryResultPOJO.getValue());
                                            featureDataPOJO.setUrl(WebServicesUrls.IMAGE_BASE_URL + newCategoryResultPOJO.getValue());
                                            break;
                                    }
                                }
                            }
                            newCategoryDataPOJOList.add(newCategoryDataPOJO);
                            featureDataPOJOList.add(featureDataPOJO);
                        }
                    } else {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
                Log.d(TAG, "offer pojos:-" + featureDataPOJOList.toString());
                offerDataPOJOList = featureDataPOJOList;
                FeaturePOJO featurePOJO = new FeaturePOJO();
                featurePOJO.setFeatureDataPOJOList(offerDataPOJOList);
                Pref.SavePOJO(getApplicationContext(), StringUtils.OFFER_TYPE, featurePOJO);
//                Pref.SetStringPref(getApplicationContext(), StringUtils.OFFER_RESPONSE, response);
                LoadOFFerRV();
            }
        }.execute();


    }

    public void parseNewFeatureAPIData(final String response) {
        new AsyncTask<Void, Void, Void>() {
            List<FeatureDataPOJO> featureDataPOJOList = new ArrayList<>();

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    Gson gson = new Gson();
                    NewCategoryPOJO newCategoryPOJO = gson.fromJson(response, NewCategoryPOJO.class);
                    if (newCategoryPOJO.getSuccess().equals("true")) {
                        List<NewCategoryResultPOJO> newCategoryResultPOJOList = newCategoryPOJO.getNewCategoryResultPOJOList();

                        Set<String> stringSet = new HashSet<>();
                        for (NewCategoryResultPOJO newCategoryResultPOJO : newCategoryResultPOJOList) {
                            stringSet.add(newCategoryResultPOJO.getEntityId());
                        }

                        List<NewCategoryDataPOJO> newCategoryDataPOJOList = new ArrayList<>();

                        for (String s : stringSet) {
                            NewCategoryDataPOJO newCategoryDataPOJO = new NewCategoryDataPOJO();
                            FeatureDataPOJO featureDataPOJO = new FeatureDataPOJO();

                            for (NewCategoryResultPOJO newCategoryResultPOJO : newCategoryResultPOJOList) {
                                if (s.equals(newCategoryResultPOJO.getEntityId())) {
                                    switch (newCategoryResultPOJO.getAttributeId()) {
                                        case "71":
                                            newCategoryDataPOJO.setProduct_name(newCategoryResultPOJO.getValue());
                                            newCategoryDataPOJO.setProduct_id(newCategoryResultPOJO.getProductId());
                                            newCategoryDataPOJO.setDiscount_price(newCategoryResultPOJO.getDiscountPrice());
                                            newCategoryDataPOJO.setMain_price(newCategoryResultPOJO.getMainPrice());

                                            featureDataPOJO.setProduct_id(newCategoryResultPOJO.getProductId());
                                            featureDataPOJO.setName(newCategoryResultPOJO.getValue());
                                            featureDataPOJO.setMain_price(newCategoryResultPOJO.getMainPrice());
                                            featureDataPOJO.setPrice(newCategoryResultPOJO.getMainPrice());
                                            featureDataPOJO.setDiscount_price(newCategoryResultPOJO.getDiscountPrice());
                                            featureDataPOJO.setSku("");
                                            featureDataPOJO.setWeight("");
                                            featureDataPOJO.setAuthor_name("");
                                            featureDataPOJO.setShort_description("");


                                            break;
                                        case "85":
                                            newCategoryDataPOJO.setProduct_image(newCategoryResultPOJO.getValue());
                                            featureDataPOJO.setUrl(WebServicesUrls.IMAGE_BASE_URL + newCategoryResultPOJO.getValue());
                                            break;
                                    }
                                }
                            }
                            newCategoryDataPOJOList.add(newCategoryDataPOJO);
                            featureDataPOJOList.add(featureDataPOJO);
                        }

                        Log.d(TAG, "category pojos:-" + featureDataPOJOList.toString());


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
//                Pref.SetStringPref(getApplicationContext(), StringUtils.FEATURE_RESPONSE, response);
                Log.d(TAG, "response:-" + response);
                mainfeatureDataPOJOList = featureDataPOJOList;
                FeaturePOJO featurePOJO = new FeaturePOJO();
                if (featureDataPOJOList.size() > 0) {
                    featurePOJO.setFeatureDataPOJOList(featureDataPOJOList);
                    Pref.SavePOJO(getApplicationContext(), StringUtils.FEATURE_TYPE, featurePOJO);
                }

                LoadFeatureRV();
            }
        }.execute();

    }


    public void parseRecentViewAll(String response) {
        Log.d(TAG, "recent View Data:-" + response);
        try {
            Gson gson = new Gson();
            RecentViewPOJO recentViewPOJO = gson.fromJson(response, RecentViewPOJO.class);
            if (recentViewPOJO.getSuccess().equals("true")) {
                recentViewALL = new ArrayList<>();
                for (String s : recentViewPOJO.getStringList()) {

                    if (!s.equals("false")) {
                        Gson gson1 = new Gson();
                        FeatureDataPOJO featureDataPOJO = gson1.fromJson(s, FeatureDataPOJO.class);
                        recentViewALL.add(featureDataPOJO);
                    }
                }

                if (recentViewALL != null && recentViewALL.size() > 0) {
                    FeaturePOJO featurePOJO = new FeaturePOJO();
                    featurePOJO.setFeatureDataPOJOList(recentViewALL);
                    showViewAllFragment(featurePOJO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<FeatureDataPOJO> recentDataPOJOs;

    public void parseRecentViewData(String response) {
        Log.d(TAG, "recent View Data:-" + response);
        try {
            Gson gson = new Gson();
            RecentViewPOJO recentViewPOJO = gson.fromJson(response, RecentViewPOJO.class);
            if (recentViewPOJO.getSuccess().equals("true")) {
                recentDataPOJOs = new ArrayList<>();
                for (String s : recentViewPOJO.getStringList()) {

                    if (!s.equals("false")) {
                        Gson gson1 = new Gson();
                        FeatureDataPOJO featureDataPOJO = gson1.fromJson(s, FeatureDataPOJO.class);
                        recentDataPOJOs.add(featureDataPOJO);
                    }
                }
                FeaturePOJO featurePOJO = new FeaturePOJO();
                featurePOJO.setFeatureDataPOJOList(recentDataPOJOs);
                Pref.SavePOJO(getApplicationContext(), StringUtils.RECENT_TYPE, featurePOJO);
                loadRecentRV();
//                Pref.SetStringPref(getApplicationContext(), StringUtils.RECENT_RESPONSE, response);
//                if(recentViewResultPOJOs.size()>0){
//                    RecentListAdapter adapter = new RecentListAdapter(HomeActivity.this, recentViewResultPOJOs);
//                    LinearLayoutManager horizontalLayoutManagaer
//                            = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//                    rv_recent_view.setLayoutManager(horizontalLayoutManagaer);
//                    rv_recent_view.setAdapter(adapter);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadRecentRV() {
        if (recentDataPOJOs != null && recentDataPOJOs.size() > 0) {
            FeatureListProductAdapter adapter = new FeatureListProductAdapter(HomeActivity.this, recentDataPOJOs);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            rv_recent_view.setLayoutManager(horizontalLayoutManagaer);
            rv_recent_view.setAdapter(adapter);
        }
    }

    public void parseAddToCartAPI(String response, View view, boolean is_buyed) {
        Log.d(TAG, "Add to cart:-" + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("success").equals("true")) {
                JSONObject resultObject = jsonObject.optJSONArray("result").optJSONObject(0);
                Gson gson = new Gson();
                CartResultPOJO cartResultPOJO = gson.fromJson(resultObject.toString(), CartResultPOJO.class);
                if (cartResultPOJO != null) {
                    Log.d(TAG, "cart pojo:-" + cartResultPOJO.toString());
                    databaseHelper.insertCartData(cartResultPOJO);
                    Log.d(TAG, "databse cart pojo:-" + databaseHelper.getAllCartData());
                    ToastClass.showShortToast(getApplicationContext(), "Added to cart");

                    if (view instanceof Button) {
                        Button button = (Button) view;
                        button.setText("Remove From Cart");
                    } else {
                        if (view instanceof ImageView) {
                            ImageView imageView = (ImageView) view;
                            imageView.setImageResource(R.drawable.ic_loaded_cart);
                        }
                    }
                    if (is_buyed) {
                        showCartFragment();
                    }
                } else {
                    ToastClass.showShortToast(getApplicationContext(), "Failed To Add");
                }
            } else {
                ToastClass.showShortToast(getApplicationContext(), "Sorry this product is currently out of stock.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastClass.showShortToast(getApplicationContext(), "Failed To Add");
        }

    }

    @Override
    public void onGetMsg(String[] msg, View view) {
        String apicall = msg[0];
        String resoponse = msg[1];
        switch (apicall) {
            case WISHLIST_DELETE_ITEM_CALL:
                parseWishListItemDeleteData(resoponse, view);
                break;
            case ADD_TO_WISHLIST_CALL:
                parseAddtoWishlistAPI(resoponse, view);
                break;
            case ADD_TO_CART_API:
                parseAddToCartAPI(resoponse, view, false);
                break;
            case DELETE_SINGLE_ITEM:
                parseDeleteFromCart(resoponse, view);
                break;
            case BUY_CART_API:
                parseAddToCartAPI(resoponse, view, true);
                break;
        }
    }

    public void parseDeleteFromCart(String response, View view) {
        Log.d(TAG, "delete cart response:-" + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("success").equals("true")) {
                databaseHelper.deleteCartData(jsonObject.optString("cart_id"));
                if (view instanceof Button) {
                    Button button = (Button) view;
                    button.setText("Add To Cart");
                } else {
                    if (view instanceof ImageView) {
                        ImageView imageView = (ImageView) view;
                        imageView.setImageResource(R.drawable.ic_empty_cart);
                    }
                }
                ToastClass.showShortToast(getApplicationContext(), "removed from cart");
            } else {
                ToastClass.showShortToast(getApplicationContext(), "Failed to remove from cart");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastClass.showShortToast(getApplicationContext(), "Failed to remove from cart");
        }
    }

    public void parseWishListItemDeleteData(String response, View view) {
        //Log.d(TAG,"delete response:-"+response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("success").equals("true")) {
                String wishlist_id = jsonObject.optString("wishlist_id");
                databaseHelper.deleteWishListItem(wishlist_id);
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    textView.setText("Add To WishList");
                } else {
                    if (view instanceof ImageView) {
                        ImageView imageView = (ImageView) view;
                        imageView.setImageResource(R.drawable.ic_fav);
                    }
                }
            } else {
                ToastClass.showShortToast(getApplicationContext(), "Failed to delete item");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastClass.showShortToast(getApplicationContext(), "Failed to delete item");
        }
    }

    public void parseAddtoWishlistAPI(String response, View view) {
        Log.d(TAG, "add to wishlist response:-" + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("success").equals("true")) {
                String wishlist_item_id = jsonObject.optJSONObject("result").optString("wishlist_item_id");
                String wishlist_id = jsonObject.optJSONObject("result").optString("wishlist_id");
                String product_id = jsonObject.optJSONObject("result").optString("product_id");
                String store_id = jsonObject.optJSONObject("result").optString("store_id");
                String added_at = jsonObject.optJSONObject("result").optString("added_at");
                String description = jsonObject.optJSONObject("result").optString("description");
                String qty = jsonObject.optJSONObject("result").optString("qty");
                WishListResultPOJO wishListResultPOJO
                        = new WishListResultPOJO(product_id, wishlist_item_id, wishlist_id,
                        description, qty, "", "", description, "", "", "");
                databaseHelper.insertWishlistData(wishListResultPOJO);
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    textView.setText("Remove From WishList");
                } else {
                    if (view instanceof ImageView) {
                        ImageView imageView = (ImageView) view;
                        imageView.setImageResource(R.drawable.ic_fav_selected);
                    }
                }
            } else {

                ToastClass.showShortToast(getApplicationContext(), "Failed to add product");
            }
        } catch (Exception e) {

        }
    }


    public void parseOfferAPIData(String response) {
        if (response.contains("NO Recodes Found")) {

        } else {
            Gson gson = new Gson();
            offerPojo = gson.fromJson(response, FeaturePOJO.class);
            LoadOFFerRV();
        }
    }

    FeaturePOJO featurePOJO, offerPojo;
    List<FeatureDataPOJO> mainfeatureDataPOJOList, offerDataPOJOList;

    public void parseFeatureApiData(String response) {
        Log.d(TAG, "feature response data:-" + response);
        if (response.contains("NO Recodes Found")) {

        } else {
//            Pref.SetStringPref(getApplicationContext(), Pref.FEATURE_LIST_DATA, "");

            Gson gson = new Gson();
            featurePOJO = gson.fromJson(response, FeaturePOJO.class);
            LoadFeatureRV();
        }
    }

    public void LoadFeatureRV() {
        if (mainfeatureDataPOJOList != null && mainfeatureDataPOJOList.size() > 0) {
            //Log.d(TAG, "feature pojo:-" + featurePOJO.toString());
            FeatureListProductAdapter adapter = new FeatureListProductAdapter(HomeActivity.this, mainfeatureDataPOJOList);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            rv_feature_products.setLayoutManager(horizontalLayoutManagaer);
            rv_feature_products.setAdapter(adapter);

            tv_feature_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showViewAllFragment(mainfeatureDataPOJOList);
                }
            });
        }
    }

    public void LoadOFFerRV() {
        if (offerDataPOJOList != null && offerDataPOJOList.size() > 0) {
            //Log.d(TAG, "feature pojo:-" + offerPojo.toString());
            FeatureListProductAdapter adapter = new FeatureListProductAdapter(HomeActivity.this, offerDataPOJOList);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            rv_offer_deals.setLayoutManager(horizontalLayoutManagaer);
            rv_offer_deals.setAdapter(adapter);

            tv_offer_products.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showViewAllFragment(offerDataPOJOList);
                }
            });
        }
    }

    public void parseCategoryData(String response) {
        try {
            Gson gson = new Gson();
            CategoryPOJO categoryPOJO = gson.fromJson(response, CategoryPOJO.class);
//            //Log.d(TAG,"category pojos:-"+categoryPOJO.toString());

            List<ChildrenPOJO> childrenPOJOList = categoryPOJO.getCategoryResultPOJO().getChildrenPOJOList().get(0).getChildrenPOJOs();
            List<ChildrenPOJO> childrenPOJOs = new ArrayList<>();
            for (ChildrenPOJO childrenPOJO : childrenPOJOList) {
                if (childrenPOJO.getName().equalsIgnoreCase("homeopathy")
                        || childrenPOJO.getName().equalsIgnoreCase("medical")
                        || childrenPOJO.getName().equalsIgnoreCase("general interest")
                        || childrenPOJO.getName().equalsIgnoreCase("accessories")
                        ) {
                    childrenPOJOs.add(childrenPOJO);
                }
//                //Log.d(TAG,"child name:-"+childrenPOJO.getName());
            }
            Pref.SetStringPref(getApplicationContext(), StringUtils.CATEGORY_DATA, response);
            inflateCategoryLevel0(childrenPOJOs);

        } catch (Exception e) {

        }
    }

    public void inflateCategoryLevel0(final List<ChildrenPOJO> childrenPOJOList) {
        ll_nav_cat_level0.removeAllViews();
        for (int i = 0; i < childrenPOJOList.size(); i++) {
            final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.inflate_ll_child_scroll, null);
            TextView tv_level0 = (TextView) view.findViewById(R.id.tv_level0);
            ImageView iv_cat_drop = (ImageView) view.findViewById(R.id.iv_cat_drop);
            final LinearLayout ll_cat_level_3 = (LinearLayout) view.findViewById(R.id.ll_cat_level_3);
            LinearLayout ll_header_scroll = (LinearLayout) view.findViewById(R.id.ll_header_scroll);
            tv_level0.setText(childrenPOJOList.get(i).getName());
            final int finalI = i;
            if (childrenPOJOList.get(finalI).getChildrenPOJOs().size() > 0) {
                inflateCategoryLevel1(ll_cat_level_3, childrenPOJOList.get(finalI).getChildrenPOJOs());
            }
            boolean isSubVisible = false;

            iv_cat_drop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Log.d(TAG, "visibility:-" + ll_cat_level_3.getVisibility());
                    if (ll_cat_level_3.getVisibility() == View.GONE) {
                        ll_cat_level_3.setVisibility(View.VISIBLE);
                    } else {
                        ll_cat_level_3.setVisibility(View.GONE);
                    }
                }
            });

            ll_header_scroll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ll_cat_level_3.getVisibility() == View.GONE) {
                        ll_cat_level_3.setVisibility(View.VISIBLE);
                    } else {
                        ll_cat_level_3.setVisibility(View.GONE);
                    }
                }
            });
//            //Log.d(TAG,"name:-"+childrenPOJOList.get(i).getName());
//            iv_cat_drop.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(childrenPOJOList.get(finalI).getChildrenPOJOs().size()>0){
//                        inflateCategoryLevel1(ll_cat_level_3,childrenPOJOList.get(finalI).getChildrenPOJOs());
//                    }
//                }
//            });

            ll_nav_cat_level0.addView(view);
        }
    }

    public void inflateCategoryLevel1(LinearLayout linearLayout, final List<ChildrenPOJO> childrenPOJOList) {
        linearLayout.removeAllViews();
        for (int i = 0; i < childrenPOJOList.size(); i++) {
            final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.inflate_level_cat3, null);
            TextView tv_cat_level_3 = (TextView) view.findViewById(R.id.tv_cat_level_3);
            LinearLayout ll_category = (LinearLayout) view.findViewById(R.id.ll_category);

            tv_cat_level_3.setText(childrenPOJOList.get(i).getName());
            //Log.d(TAG, "sub name name:-" + childrenPOJOList.get(i).getName());

            final int finalI = i;
            ll_category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Log.d(TAG, "category id:-" + childrenPOJOList.get(finalI).getCategory_id());
                    removeActiveCenterFragments();
                    showCategoryProductFragment(childrenPOJOList.get(finalI));
                    mDrawer.closeDrawers();
                }
            });

            linearLayout.addView(view);
        }
    }

    public void parseNewSliderData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("Success").equals("true")) {
                String images = jsonObject.optJSONArray("Result").optJSONObject(0).optString("images");

                JSONArray imageJsonObject1 = new JSONArray(images);
                List<String> stringList = new ArrayList<>();
                for (int i = 0; i < imageJsonObject1.length(); i++) {
                    String image_url = "http://www.bjain.com/bookstore/media/em_minislideshow/" + imageJsonObject1.optJSONObject(i).optString("url");
                    stringList.add(image_url);
                }

                if (stringList.size() > 0) {
                    setUpViewPager(stringList);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseImageSliderData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String Success = jsonObject.optString("Success");
            if (Success.equals("true")) {
                JSONArray array = jsonObject.optJSONArray("Result");
                List<String> list_images = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject1 = array.optJSONObject(i);
                    String slider = jsonObject1.optString("slider");
                    list_images.add(slider);
                }

                if (list_images.size() > 0) {
                    setUpViewPager(list_images);
                }
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setUpRecyclerViews() {
        rv_feature_products = (RecyclerView) findViewById(R.id.rv_feature_products);
        rv_offer_deals = (RecyclerView) findViewById(R.id.rv_offer_deals);
        rv_recent_view = (RecyclerView) findViewById(R.id.rv_recent_view);

        List<String> list_books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list_books.add("Book " + (i + 1));
        }
        FeatureProductAdapter adapter = new FeatureProductAdapter(getApplicationContext(), list_books);


        LinearLayoutManager horizontalLayoutManagaer1
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_feature_products.setLayoutManager(horizontalLayoutManagaer1);
        rv_feature_products.setAdapter(adapter);

        LinearLayoutManager horizontalLayoutManagaer2
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_offer_deals.setLayoutManager(horizontalLayoutManagaer2);
        rv_offer_deals.setAdapter(adapter);

        LinearLayoutManager horizontalLayoutManagaer3
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_recent_view.setLayoutManager(horizontalLayoutManagaer3);
        rv_recent_view.setAdapter(adapter);


    }

    LinearLayout ll_nav_cat, ll_header, ll_nav_cat_level0;
    TextView tv_profile_name, tv_email, tv_profile_symbol, tv_inr, tv_usd;

    private void settingNavDrawer() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupDrawerContent(nvDrawer);
        View headerLayout = nvDrawer.inflateHeaderView(R.layout.home_nav_header);
        final LinearLayout ll_nav_header = (LinearLayout) headerLayout.findViewById(R.id.ll_nav_header);
        ll_nav_cat = (LinearLayout) headerLayout.findViewById(R.id.nav_cat);
        ll_nav_cat_level0 = (LinearLayout) headerLayout.findViewById(R.id.ll_nav_cat_level0);
        ll_header = (LinearLayout) headerLayout.findViewById(R.id.ll_header);
        tv_profile_symbol = (TextView) headerLayout.findViewById(R.id.tv_profile_symbol);
        tv_email = (TextView) headerLayout.findViewById(R.id.tv_email);
        tv_profile_name = (TextView) headerLayout.findViewById(R.id.tv_profile_name);
        tv_profile_name.setText(Pref.GetStringPref(getApplicationContext(), StringUtils.FIRST_NAME, "") +
                " " + Pref.GetStringPref(getApplicationContext(), StringUtils.LAST_NAME, ""));
        tv_email.setText(Pref.GetStringPref(getApplicationContext(), StringUtils.EMAIL_ID, ""));
        tv_usd = (TextView) findViewById(R.id.tv_usd);
        tv_inr = (TextView) findViewById(R.id.tv_inr);


        if (Pref.GetCurrency(getApplicationContext()).equals("INR")) {
            tv_inr.setTextColor(Color.parseColor("#FFFF00"));
            tv_usd.setTextColor(Color.parseColor("#000000"));
        } else {
            tv_inr.setTextColor(Color.parseColor("#000000"));
            tv_usd.setTextColor(Color.parseColor("#FFFF00"));
        }

        tv_inr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pref.SetCurrency(getApplicationContext(), "INR");
                tv_inr.setTextColor(Color.parseColor("#FFFF00"));
                tv_usd.setTextColor(Color.parseColor("#000000"));
                mDrawer.closeDrawers();
                refreshAllViews();
            }
        });
        tv_usd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pref.SetCurrency(getApplicationContext(), "USD");
                tv_inr.setTextColor(Color.parseColor("#000000"));
                tv_usd.setTextColor(Color.parseColor("#FFFF00"));
                mDrawer.closeDrawers();
                refreshAllViews();
            }
        });
        setSymbol();
//        TextView header_text = (TextView) headerLayout.findViewById(R.id.header_text);
//        drawerToggle = setupDrawerToggle();
        setupDrawerToggle();
//        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(false);

        nvDrawer.setItemIconTintList(null);
//        setFirstItemNavigationView();
        final LinearLayout ll_nav_cat_home = (LinearLayout) findViewById(R.id.ll_nav_cat);
        ll_nav_cat_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCatNavDrawer();
            }
        });
        ic_ham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openDrawer(GravityCompat.START);
                ll_nav_cat.setVisibility(View.GONE);
                ll_header.setVisibility(View.VISIBLE);

            }
        });
    }

    public void setSymbol() {
        if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
            try {
                String s = Pref.GetStringPref(getApplicationContext(), StringUtils.FIRST_NAME, "");
                char ch = s.charAt(0);
                tv_profile_symbol.setText(String.valueOf(ch));
                tv_profile_name_toolbar.setText(String.valueOf(ch));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            tv_profile_symbol.setText("");
            tv_profile_name_toolbar.setText("");
        }

    }


    public void openCatNavDrawer() {
        mDrawer.openDrawer(nvDrawer);
        ll_nav_cat.setVisibility(View.VISIBLE);
        ll_header.setVisibility(View.GONE);
        setMenuVisibilityOFF();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private void setupDrawerToggle() {
        drawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                // Do whatever you want here
                //Log.d(TAG, "drawer closed");
                setMenuVisibilityON();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // Do whatever you want here

            }
        };
// Set the drawer toggle as the DrawerListener
        mDrawer.addDrawerListener(drawerToggle);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                removeActiveCenterFragments();
                break;
            case R.id.nav_account:
                if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
                    startActivity(new Intent(HomeActivity.this, MyAccountActivity.class));
                } else {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
                break;
            case R.id.nav_wish_list:
                if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
//                    startActivity(new Intent(HomeActivity.this, WishListActivity.class));
                    removeActiveCenterFragments();
                    showWishListFragment();
                } else {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
                break;
            case R.id.nav_orders:
                startActivity(new Intent(HomeActivity.this, OrderActivity.class));
                break;
            case R.id.nav_track_orders:
                startActivity(new Intent(HomeActivity.this, CommingSoonActitivy.class));
                break;
            case R.id.nav_shop_cat:
//                startActivity(new Intent(HomeActivity.this, CommingSoonActitivy.class));
                showShopByCategoryFragment();
                break;
            case R.id.nav_ewallet:
                startActivity(new Intent(HomeActivity.this, CommingSoonActitivy.class));
                break;
            case R.id.nav_customer_service:
                startActivity(new Intent(HomeActivity.this, ContactUsActivity.class));
                break;
            case R.id.nav_login:
                //Log.d(TAG, "islogin:-" + Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false));
                if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
                    Pref.SetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false);
                    Pref.clearSharedPreference(getApplicationContext());
                    databaseHelper.deleteAllWishListData();
                    databaseHelper.deleteAllCartItems();
                    changeLoginMenuName();
                    refreshAllViews();
                    setSymbol();
                } else {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
                break;

        }
        mDrawer.closeDrawers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d(TAG,"actcheck:-onresume");
        changeLoginMenuName();
        setSymbol();
//        if(getSupportFragmentManager().findFragmentById(R.id.frame_main) == null){
//            //Log.d(TAG,"no fragment");
        LoadFeatureRV();
        LoadOFFerRV();
        loadRecentRV();
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d(TAG,"actcheck:-onpause");
    }

    public void changeLoginMenuName() {
        Menu menu = nvDrawer.getMenu();
        MenuItem nav_login = menu.findItem(R.id.nav_login);
        if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
            nav_login.setTitle("Logout");
        } else {
            nav_login.setTitle("Login");
        }
        if (tv_profile_name != null & tv_email != null) {
            tv_profile_name.setText(Pref.GetStringPref(getApplicationContext(), StringUtils.FIRST_NAME, "") +
                    " " + Pref.GetStringPref(getApplicationContext(), StringUtils.LAST_NAME, ""));
            tv_email.setText(Pref.GetStringPref(getApplicationContext(), StringUtils.EMAIL_ID, ""));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
//                //Log.d(TAG,"drawer");
//                ll_nav_cat.setVisibility(View.GONE);
//                ll_header.setVisibility(View.VISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BARCODE_SCANNER_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                if (result.equals("cancel")) {
                    Toast.makeText(getApplicationContext(), "Scan Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    String res = "code:-" + data.getStringExtra("code") + "\n" + "format:-" + data.getStringExtra("format");
                    Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
                    if(scannerFragment!=null){
                        scannerFragment.callSearchAPI(data.getStringExtra("code"));
                    }
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    public void setMenuVisibilityOFF() {
        Menu nav_Menu = nvDrawer.getMenu();
        nav_Menu.findItem(R.id.nav_home).setVisible(false);
        nav_Menu.findItem(R.id.nav_account).setVisible(false);
        nav_Menu.findItem(R.id.nav_wish_list).setVisible(false);
        nav_Menu.findItem(R.id.nav_orders).setVisible(false);
        nav_Menu.findItem(R.id.nav_track_orders).setVisible(false);
        nav_Menu.findItem(R.id.nav_shop_cat).setVisible(false);
        nav_Menu.findItem(R.id.nav_ewallet).setVisible(false);
        nav_Menu.findItem(R.id.nav_customer_service).setVisible(false);
        nav_Menu.findItem(R.id.nav_login).setVisible(false);

    }

    public void setMenuVisibilityON() {
        ll_nav_cat.setVisibility(View.GONE);
        ll_header.setVisibility(View.VISIBLE);
        Menu nav_Menu = nvDrawer.getMenu();
        nav_Menu.findItem(R.id.nav_home).setVisible(true);
        nav_Menu.findItem(R.id.nav_account).setVisible(true);
        nav_Menu.findItem(R.id.nav_wish_list).setVisible(true);
        nav_Menu.findItem(R.id.nav_orders).setVisible(true);
        nav_Menu.findItem(R.id.nav_track_orders).setVisible(true);
        nav_Menu.findItem(R.id.nav_shop_cat).setVisible(true);
        nav_Menu.findItem(R.id.nav_ewallet).setVisible(true);
        nav_Menu.findItem(R.id.nav_customer_service).setVisible(true);
        nav_Menu.findItem(R.id.nav_login).setVisible(true);
    }


    public void ViewProduct(FeatureDataPOJO freFeatureDataPOJO, List<FeatureDataPOJO> featureDataPOJOList, int position) {
        ProductViewFragment productViewFragment = new ProductViewFragment(freFeatureDataPOJO, featureDataPOJOList, position);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_main, productViewFragment, "productfeature");
        transaction.addToBackStack(null);
        transaction.commit();
        activeCenterFragments.add(productViewFragment);
    }

    public void ViewProduct(String product_id) {
        ProductViewFragment productViewFragment = new ProductViewFragment(product_id);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_main, productViewFragment, "productfeature");
        transaction.addToBackStack(null);
        transaction.commit();
        activeCenterFragments.add(productViewFragment);
    }

    public void showCategoryProductFragment(ChildrenPOJO childrenPOJO) {
        CategoryProductFragment childCategoryProductFragment = new CategoryProductFragment(childrenPOJO);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_main, childCategoryProductFragment, "categoryproductfragment");
        transaction.addToBackStack(null);
        transaction.commit();
        activeCenterFragments.add(childCategoryProductFragment);
    }

    public void showShopByCategoryFragment() {
        ShopByCategoriesFragment shopByCategoriesFragment = new ShopByCategoriesFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_main, shopByCategoriesFragment, "shopbycategoryfragment");
        transaction.addToBackStack(null);
        transaction.commit();
        activeCenterFragments.add(shopByCategoriesFragment);
    }

    public void showWishListFragment() {
        WishListFragment wishListFragment = new WishListFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_main, wishListFragment, "wislistfragment");
        transaction.addToBackStack(null);
        transaction.commit();
        activeCenterFragments.add(wishListFragment);
    }

    public void showCartFragment() {
        CartFragment cartFragment = new CartFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_main, cartFragment, "cartfragment");
        transaction.addToBackStack(null);
        transaction.commit();
        activeCenterFragments.add(cartFragment);
    }


    public void showViewAllFragment(FeaturePOJO featurePOJO) {
        ViewAllFragment viewAllFragment = new ViewAllFragment(featurePOJO);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_main, viewAllFragment, "viewallfragment");
        transaction.addToBackStack(null);
        transaction.commit();
        activeCenterFragments.add(viewAllFragment);
    }

    public void showViewAllFragment(List<FeatureDataPOJO> featureDataPOJOList) {
        ViewAllFragment viewAllFragment = new ViewAllFragment(featureDataPOJOList);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_main, viewAllFragment, "viewallfragment");
        transaction.addToBackStack(null);
        transaction.commit();
        activeCenterFragments.add(viewAllFragment);
    }

    public void showSearchFragment() {
        SearchFragment searchFragment = new SearchFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_main, searchFragment, "searchfragment");
        transaction.addToBackStack(null);
        transaction.commit();
        activeCenterFragments.add(searchFragment);
    }


    public void removeActiveCenterFragments() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (activeCenterFragments.size() > 0) {
            transaction = manager.beginTransaction();
            for (Fragment activeFragment : activeCenterFragments) {
                transaction.remove(activeFragment);
            }
            activeCenterFragments.clear();
            transaction.commit();
        }
        LoadOFFerRV();
        LoadFeatureRV();
        loadRecentRV();
        ll_products.setVisibility(View.VISIBLE);
    }

    public void AddToWishList(String product_id, String qty, View view) {
        if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
            String user_id = Pref.GetStringPref(getApplicationContext(), StringUtils.ENTITY_ID, "");

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("product_id", product_id));
            nameValuePairs.add(new BasicNameValuePair("description", ""));
            nameValuePairs.add(new BasicNameValuePair("qty", qty));
            nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
            new WebServiceBase(nameValuePairs, this, ADD_TO_WISHLIST_CALL, view).execute(WebServicesUrls.ADD_WISHLIST_URL);
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    public void DeleteFromWishList(String product_id, View view) {
        //Log.d(TAG, "adding to wishlist");
        WishListResultPOJO wishListResultPOJO = databaseHelper.getWishListData(product_id);
        if (wishListResultPOJO != null) {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("wishlist_item_id", wishListResultPOJO.getWishlistItemId()));
            new WebServiceBase(nameValuePairs, this, WISHLIST_DELETE_ITEM_CALL, view).execute(WebServicesUrls.WISHLIST_DELETE_ITEM_URL);
        }
    }

    public void addToCart(String product_id, String sku, String name, String weight
            , String qty, String price, String base_price, View view, boolean is_buyed) {
        if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("product_id", product_id));
            nameValuePairs.add(new BasicNameValuePair("sku", sku));
            nameValuePairs.add(new BasicNameValuePair("name", name));
            nameValuePairs.add(new BasicNameValuePair("weight", weight));
            nameValuePairs.add(new BasicNameValuePair("qty", qty));
            nameValuePairs.add(new BasicNameValuePair("price", price));
            nameValuePairs.add(new BasicNameValuePair("base_price", base_price));
            nameValuePairs.add(new BasicNameValuePair("quote_id", Pref.GetStringPref(getApplicationContext(), StringUtils.QUOTOID, "")));
            nameValuePairs.add(new BasicNameValuePair("email", Pref.GetStringPref(getApplicationContext(), StringUtils.EMAIL_ID, "")));
            if (is_buyed) {
                new WebServiceBase(nameValuePairs, this, BUY_CART_API, view).execute(WebServicesUrls.ADD_TO_CART_URL);
            } else {
                new WebServiceBase(nameValuePairs, this, ADD_TO_CART_API, view).execute(WebServicesUrls.ADD_TO_CART_URL);
            }
        } else {
            ToastClass.showShortToast(getApplicationContext(), "please login first");
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }
    }

    public void callCartSingleItemDelete(String cart_id, String product_id, View view) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("cart_id", cart_id));
        nameValuePairs.add(new BasicNameValuePair("product_id", product_id));
        new WebServiceBase(nameValuePairs, this, DELETE_SINGLE_ITEM, view).execute(WebServicesUrls.CART_SINGLE_DELETE_URL);
    }


    private final String ADD_TO_WISHLIST_CALL = "add_to_wishlist_call";
    private final String WISHLIST_DELETE_ITEM_CALL = "wishlist_delete_item_call";


    @Override
    public void onBackPressed() {

        if (addFabMenu.isExpanded()) {
            addFabMenu.collapse();
        } else {
            LoadFeatureRV();
            LoadOFFerRV();
            loadRecentRV();
            if (activeCenterFragments.size() > 0) {
                super.onBackPressed();
            } else {
                finish();
            }
        }
    }
}
