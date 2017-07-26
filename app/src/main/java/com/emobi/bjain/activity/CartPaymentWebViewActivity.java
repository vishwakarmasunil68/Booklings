package com.emobi.bjain.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.emobi.bjain.R;
import com.emobi.bjain.database.DatabaseHelper;
import com.emobi.bjain.pojo.cart.CartPOJO;
import com.emobi.bjain.pojo.cart.CartResultPOJO;
import com.emobi.bjain.pojo.newaddress.NewAddressResultPOJO;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.StringUtils;
import com.emobi.bjain.utils.TagUtils;
import com.emobi.bjain.utils.ToastClass;
import com.emobi.bjain.webservice.WebServiceBase;
import com.emobi.bjain.webservice.WebServicesCallBack;
import com.emobi.bjain.webservice.WebServicesUrls;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.emobi.bjain.R.id.webview;

public class CartPaymentWebViewActivity extends AppCompatActivity implements WebServicesCallBack{

    private static final String ONLINE_PAYMENT_API = "online_payment_api";
    private static final String CALL_MAIL_API = "call_mail_api";
    private final String TAG = getClass().getSimpleName();

    @BindView(webview)
    WebView webView;
    NewAddressResultPOJO newAddressResultPOJO;
    CartPOJO cartPOJO;
    String carrier="";
    String order_no="";
    String grand_amount="";
    String extra_amount="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_payment_web_view);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        newAddressResultPOJO= (NewAddressResultPOJO) getIntent().getSerializableExtra("addresspojo");
        cartPOJO= (CartPOJO) getIntent().getSerializableExtra("cartpojo");
        if (bundle != null) {

            String user_name = bundle.getString("user_name");
            String user_address = bundle.getString("user_address");
            String user_zipcode = bundle.getString("user_zipcode");
            String user_city = bundle.getString("user_city");
            String user_state = bundle.getString("user_state");
            String user_country = bundle.getString("user_country");
            String user_phone = bundle.getString("user_phone");
            String user_email = bundle.getString("user_email");
            String user_id = bundle.getString("user_id");
            String modelno = bundle.getString("modelno");
            String finalamount = bundle.getString("finalamount");
            extra_amount = bundle.getString("extra_amount");
            grand_amount=finalamount;
            order_no = bundle.getString("order_no");
            String carrier = bundle.getString("carrier");


//            webView.getSettings().setBuiltInZoomControls(true);
//            webView.setInitialScale(1);
//            webView.getSettings().setAppCacheEnabled(false);
//            webView.getSettings().setJavaScriptEnabled(true);

            String url = "http://www.bjain.com/ebs_getway/index.php?" +
                    "user_name=" + user_name +
                    "&user_address=" + user_address +
                    "&user_zipcode=" + user_zipcode +
                    "&user_city=" + user_city +
                    "&user_state=" + user_state +
                    "&user_country=" + user_country +
                    "&user_phone=" + user_phone +
                    "&user_email=" + user_email +
                    "&user_id=" + user_id +
                    "&modelno=" + modelno +
                    "&finalamount=" + finalamount +
                    "&order_no=" + order_no;
            Log.d(TagUtils.getTag(), "url:-" + url);
//            webView.setWebViewClient(new WebViewClient());
////            webView.setWebViewClient(new AppWebViewClients(this));
//            webView.loadUrl(url);

            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            //ws.setJavaScriptEnabled(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//            callAPI();
            webView.setWebViewClient(new WebViewClient() {

                public boolean shouldOverrideUrlLoading(WebView view, String url) {


                    if (url.startsWith("http:") || url.startsWith("https:")) {
                        return false;
                    }
                    //  Log.i(TAG, "Processing webview url click..."+url);
                    //  view.loadUrl(url);

                    return true;
                }


                public void onPageFinished(WebView view, String url) {
                    Log.e(TAG, "Finished loading URL: " + url);
                    if(url.contains("http://www.bjain.com/ebs_getway/Response.php")){
                        callAPI();
                    }
                }


                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {


                }
            });

            webView.loadUrl(url);
        }
    }


    public void callAPI(){
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("payment_method", "secureebs_standard"));
        nameValuePairs.add(new BasicNameValuePair("shipping_description", carrier));
        nameValuePairs.add(new BasicNameValuePair("is_virtual", "0"));
        nameValuePairs.add(new BasicNameValuePair("customer_id", Pref.GetStringPref(getApplicationContext(), StringUtils.ENTITY_ID, "")));
        nameValuePairs.add(new BasicNameValuePair("base_grand_total", String.valueOf(grand_amount)));
        nameValuePairs.add(new BasicNameValuePair("base_shipping_invoiced", "0"));
        nameValuePairs.add(new BasicNameValuePair("base_subtotal", String.valueOf(grand_amount)));
        nameValuePairs.add(new BasicNameValuePair("grand_total", String.valueOf(grand_amount)));
        nameValuePairs.add(new BasicNameValuePair("shipping_amount", String.valueOf(extra_amount)));
        nameValuePairs.add(new BasicNameValuePair("subtotal", String.valueOf(grand_amount)));
        nameValuePairs.add(new BasicNameValuePair("quote_id", Pref.GetStringPref(getApplicationContext(), StringUtils.QUOTOID, "")));
        nameValuePairs.add(new BasicNameValuePair("order_no", order_no));
        nameValuePairs.add(new BasicNameValuePair("currency_code", Pref.GetCurrency(getApplicationContext())));
        nameValuePairs.add(new BasicNameValuePair("region", newAddressResultPOJO.getRegion()));
        nameValuePairs.add(new BasicNameValuePair("postcode", newAddressResultPOJO.getPostcode()));
        nameValuePairs.add(new BasicNameValuePair("lastname", newAddressResultPOJO.getLastname()));
        nameValuePairs.add(new BasicNameValuePair("street", newAddressResultPOJO.getStreet()));
        nameValuePairs.add(new BasicNameValuePair("city", newAddressResultPOJO.getCity()));
        nameValuePairs.add(new BasicNameValuePair("email", Pref.GetStringPref(getApplicationContext(), StringUtils.EMAIL_ID, "")));
        nameValuePairs.add(new BasicNameValuePair("telephone", newAddressResultPOJO.getTelephone()));
        nameValuePairs.add(new BasicNameValuePair("country_id", newAddressResultPOJO.getCountryId()));
        nameValuePairs.add(new BasicNameValuePair("firstname", newAddressResultPOJO.getFirstname()));
//        nameValuePairs.add(new BasicNameValuePair("middlename", ""));
        String product_ids = "";
        String sku = "";
        String name = "";
        String qty = "";
        String price = "";
        if (cartPOJO != null && cartPOJO.getCartResultPOJOList() != null && cartPOJO.getCartResultPOJOList().size() > 0) {
            for (int i = 0; i < cartPOJO.getCartResultPOJOList().size(); i++) {
                CartResultPOJO cartResultPOJO = cartPOJO.getCartResultPOJOList().get(i);
                if ((i + 1) == cartPOJO.getCartResultPOJOList().size()) {
                    product_ids = product_ids + cartResultPOJO.getProduct_id();
                    sku = sku + cartResultPOJO.getSku();
                    name = name + cartResultPOJO.getName();
                    qty = qty + cartResultPOJO.getQty();
                    price = price + cartResultPOJO.getPrice();
                } else {
                    product_ids = product_ids + cartResultPOJO.getProduct_id() + ",";
                    sku = sku + cartResultPOJO.getSku() + ",";
                    name = name + cartResultPOJO.getName() + ",";
                    qty = qty + cartResultPOJO.getQty() + ",";
                    price = price + cartResultPOJO.getPrice() + ",";
                }
            }
        } else {

        }
        nameValuePairs.add(new BasicNameValuePair("product_id", product_ids));
        nameValuePairs.add(new BasicNameValuePair("sku", sku));
        nameValuePairs.add(new BasicNameValuePair("name", name));
        nameValuePairs.add(new BasicNameValuePair("qty_ordered", qty));
        nameValuePairs.add(new BasicNameValuePair("price", price));
        nameValuePairs.add(new BasicNameValuePair("row_total", String.valueOf(grand_amount)));
        nameValuePairs.add(new BasicNameValuePair("base_grand_total", String.valueOf(grand_amount)));
        nameValuePairs.add(new BasicNameValuePair("base_total_paid", String.valueOf(grand_amount)));
        nameValuePairs.add(new BasicNameValuePair("grand_total", String.valueOf(grand_amount)));
        nameValuePairs.add(new BasicNameValuePair("total_paid", String.valueOf(grand_amount)));
        nameValuePairs.add(new BasicNameValuePair("base_currency_code", Pref.GetCurrency(getApplicationContext())));
        nameValuePairs.add(new BasicNameValuePair("order_currency_code", Pref.GetCurrency(getApplicationContext())));
        nameValuePairs.add(new BasicNameValuePair("shipping_name", newAddressResultPOJO.getFirstname()));
        nameValuePairs.add(new BasicNameValuePair("billing_name", newAddressResultPOJO.getFirstname()));
        nameValuePairs.add(new BasicNameValuePair("status", "processing"));
        nameValuePairs.add(new BasicNameValuePair("base_price", price));
        nameValuePairs.add(new BasicNameValuePair("original_price", price));
        nameValuePairs.add(new BasicNameValuePair("base_original_price", price));
        nameValuePairs.add(new BasicNameValuePair("base_row_total", price));
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        nameValuePairs.add(new BasicNameValuePair("dates", sdf.format(d)));
        nameValuePairs.add(new BasicNameValuePair("customer_firstname", Pref.GetStringPref(getApplicationContext(), StringUtils.FIRST_NAME, "")));
        nameValuePairs.add(new BasicNameValuePair("customer_lastname", Pref.GetStringPref(getApplicationContext(), StringUtils.LAST_NAME, "")));
        new WebServiceBase(nameValuePairs, this, ONLINE_PAYMENT_API).execute(WebServicesUrls.CASH_ON_DELIVERY_URL);
    }

    @Override
    public void onGetMsg(String[] msg) {
        String apicall=msg[0];
        String response=msg[1];
        switch (apicall){
            case ONLINE_PAYMENT_API:
                parseCashOnDeliveryResponse(response);
                break;
            case CALL_MAIL_API:
                parseMailAPI(response);
                break;
        }
    }

    public void parseMailAPI(String response){
        try{
            JSONObject jsonObject=new JSONObject(response);
            if(jsonObject.optString("success").equals("true")){
                startActivity(new Intent(CartPaymentWebViewActivity.this, HomeActivity.class));
                finishAffinity();
            }
        }catch (Exception e){

        }
    }

    public void parseCashOnDeliveryResponse(String response) {
        Log.d(TAG, "cash On delivery response:-" + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("success").equals("true")) {
                JSONObject result = jsonObject.optJSONObject("result");
                if (result.optString("status").equals("accepted")) {
                    ToastClass.showShortToast(getApplicationContext(), "Your Order has been accepted.");
                    DatabaseHelper databaseHelper=new DatabaseHelper(this);
                    databaseHelper.deleteAllCartItems();
                    showOrderDialog();
                } else {
                    ToastClass.showShortToast(getApplicationContext(), "Sorry your order has not been accepted please try again later.");
                }
            } else {
                ToastClass.showShortToast(getApplicationContext(), "Order Placed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showOrderDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                CartPaymentWebViewActivity.this);
        alertDialogBuilder.setTitle("Order Placed");
        alertDialogBuilder
                .setMessage("Your Order has been Placed Successfully and your order id is " + order_no)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        callMailAPI();

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }


    public void callMailAPI() {
        if (newAddressResultPOJO != null) {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("payment_method", "secureebs_standard"));
            nameValuePairs.add(new BasicNameValuePair("shipping_name", newAddressResultPOJO.getFirstname() + " " + newAddressResultPOJO.getLastname()));
            nameValuePairs.add(new BasicNameValuePair("street", newAddressResultPOJO.getStreet()));
            nameValuePairs.add(new BasicNameValuePair("region", newAddressResultPOJO.getRegion()));
            nameValuePairs.add(new BasicNameValuePair("postcode", newAddressResultPOJO.getPostcode()));
            nameValuePairs.add(new BasicNameValuePair("telephone", newAddressResultPOJO.getTelephone()));
            nameValuePairs.add(new BasicNameValuePair("shipping_description", "Standard Carrier - (Delivered in 12-15 Working Days)"));
            nameValuePairs.add(new BasicNameValuePair("order_no", order_no));
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            nameValuePairs.add(new BasicNameValuePair("dates", sdf.format(d)));
            nameValuePairs.add(new BasicNameValuePair("subtotal", String.valueOf(cartPOJO.getGrand_total())));
            nameValuePairs.add(new BasicNameValuePair("currency_code", Pref.GetCurrency(getApplicationContext())));

            String product_ids = "";
            String sku = "";
            String name = "";
            String qty = "";
            String price = "";
            if (cartPOJO != null && cartPOJO.getCartResultPOJOList() != null && cartPOJO.getCartResultPOJOList().size() > 0) {
                for (int i = 0; i < cartPOJO.getCartResultPOJOList().size(); i++) {
                    CartResultPOJO cartResultPOJO = cartPOJO.getCartResultPOJOList().get(i);
                    if ((i + 1) == cartPOJO.getCartResultPOJOList().size()) {
                        product_ids = product_ids + cartResultPOJO.getProduct_id();
                        sku = sku + cartResultPOJO.getSku();
                        name = name + cartResultPOJO.getName();
                        qty = qty + cartResultPOJO.getQty();
                        price = price + (getPrice(cartResultPOJO.getPrice())*getqty(cartResultPOJO.getQty()));
                    } else {
                        product_ids = product_ids + cartResultPOJO.getProduct_id() + ",";
                        sku = sku + cartResultPOJO.getSku() + ",";
                        name = name + cartResultPOJO.getName() + ",";
                        qty = qty + cartResultPOJO.getQty() + ",";
                        price = price + (getPrice(cartResultPOJO.getPrice())*getqty(cartResultPOJO.getQty())) + ",";
                    }
                }
            } else {

            }
            nameValuePairs.add(new BasicNameValuePair("name", name));
            nameValuePairs.add(new BasicNameValuePair("sku", sku));
            nameValuePairs.add(new BasicNameValuePair("qty_ordered", qty));
            nameValuePairs.add(new BasicNameValuePair("row_total", price));
            nameValuePairs.add(new BasicNameValuePair("email", Pref.GetStringPref(getApplicationContext(),StringUtils.EMAIL_ID,"")));
            nameValuePairs.add(new BasicNameValuePair("shipping_handling", String.valueOf(extra_amount)));

            Log.d(TAG,"mail params:-"+nameValuePairs.toString());

            new WebServiceBase(nameValuePairs, this, CALL_MAIL_API).execute(WebServicesUrls.MAIL_URL);
        }
    }

    public double getPrice(String price){
        try{
            return Double.parseDouble(price);
        }catch (Exception e){
            return 0.0;
        }
    }

    public double getqty(String qty){
        try{
            return Double.parseDouble(qty);
        }catch (Exception e){
            return 0.0;
        }
    }
    @Override
    public void onBackPressed() {
        if (webView != null) {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                super.onBackPressed();
            }
        }
    }

}
