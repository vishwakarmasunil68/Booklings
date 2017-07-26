package com.emobi.bjain.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.emobi.bjain.R;
import com.emobi.bjain.database.DatabaseHelper;
import com.emobi.bjain.pojo.address.AddressPOJO;
import com.emobi.bjain.pojo.address.AddressResultPOJO;
import com.emobi.bjain.pojo.cart.CartPOJO;
import com.emobi.bjain.pojo.cart.CartResultPOJO;
import com.emobi.bjain.pojo.newaddress.NewAddressPOJO;
import com.emobi.bjain.pojo.newaddress.NewAddressResultPOJO;
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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartCheckOutActivity extends AppCompatActivity implements WebServicesCallBack {

    private static final String CASH_ON_DELIVERY_API = "cash_on_delivery_api";
    private static final String CALL_MAIL_API = "call_mail_api";
    private final String TAG = getClass().getSimpleName();
    private final String SHIPPING_API_CALL = "shipping_api_call";
    private final String BILLING_API_CALL = "billing_api_call";
    private DatabaseHelper databaseHelper;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_scroll_orders)
    LinearLayout ll_scroll_orders;

    @BindView(R.id.spinner_address)
    Spinner spinner_address;

    @BindView(R.id.rg_shipping_method)
    RadioGroup rg_shipping_method;
    @BindView(R.id.rb_standard_carrier)
    RadioButton rb_standard_carrier;
    @BindView(R.id.rb_express_carrier)
    RadioButton rb_express_carrier;
    @BindView(R.id.rg_payment_method)
    RadioGroup rg_payment_method;
    @BindView(R.id.rb_online_payment)
    RadioButton rb_online_payment;
    @BindView(R.id.rb_cash_on_delivery)
    RadioButton rb_cash_on_delivery;
    @BindView(R.id.tv_subtotal)
    TextView tv_subtotal;
    @BindView(R.id.tv_shipping_charges)
    TextView tv_shipping_charges;
    @BindView(R.id.tv_grand_total)
    TextView tv_grand_total;
    @BindView(R.id.btn_place_order)
    Button btn_place_order;
    @BindView(R.id.ll_billing_addr)
    LinearLayout ll_billing_addr;
    @BindView(R.id.spinner_billing_address)
    Spinner spinner_billing_address;
    @BindView(R.id.check_shipping)
    CheckBox check_shipping;
    @BindView(R.id.btn_add_new)
    Button btn_add_new;
    double grand_amount = 0.00;
    double standard_carrier_charges = 0.0;
    double express_carrier_charges = 0.0;
    double extra_amount = 0.00;
    CartPOJO cartPOJO;
    String string_grand_total = "";
    boolean is_online_payment = true;
    boolean is_standart_delivery = true;
    String price_currency = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_check_out);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Cart Checkout");

        databaseHelper = new DatabaseHelper(this);

        price_currency = Pref.GetCurrency(getApplicationContext());
        cartPOJO = (CartPOJO) getIntent().getSerializableExtra("cartpojo");
        if (cartPOJO != null) {
            inflateCartItems(cartPOJO.getCartResultPOJOList());
            tv_grand_total.setText("GRAND TOTAL     " + price_currency + " " + cartPOJO.getGrand_total());
            tv_subtotal.setText("Subtotal     " + price_currency + " " + cartPOJO.getGrand_total());
            tv_shipping_charges.setText("Shipping & Handling (Express Carrier - (Free delivery in 3-5 Working Days)) " + price_currency + " 0.00");

            try {
                Log.d(TAG, "grand_amount:-" + cartPOJO.getGrand_total());
                grand_amount = Double.parseDouble(cartPOJO.getGrand_total());

                if (grand_amount <= 300) {
                    extra_amount = 50;
                    standard_carrier_charges = 50;
                    rb_standard_carrier.setText("Standard Carrier (Free delivery in 12-15 Working Days) " + price_currency + " 50.00");
                    tv_shipping_charges.setText("Shipping & Handling (Standard Carrier (Free delivery in 12-15 Working Days)) " + price_currency + " 50.00");
                } else {
                    extra_amount = 0;
                    grand_amount = Double.parseDouble(cartPOJO.getGrand_total());
                    standard_carrier_charges = 0;
                    rb_standard_carrier.setText("Standard Carrier (Free delivery in 12-15 Working Days) " + price_currency + " 00.00");
                    tv_shipping_charges.setText("Shipping & Handling (Standard Carrier (Free delivery in 12-15 Working Days)) " + price_currency + " 0.00");
                }

                if (grand_amount > 1000) {
                    extra_amount = 0;
                    express_carrier_charges = 0;
                    rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) " + price_currency + " 0.00");
                } else {
                    if (grand_amount <= 100) {
                        extra_amount = 50;
                        express_carrier_charges = 50;
                        rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) " + price_currency + " 50.00");
                    } else {
                        if (grand_amount > 100 && grand_amount <= 200) {
                            express_carrier_charges = 60;
                            extra_amount = 60;
                            rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) " + price_currency + " 60.00");
                        } else {
                            if (grand_amount > 200 && grand_amount <= 300) {
                                express_carrier_charges = 70;
                                extra_amount = 70;
                                rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) " + price_currency + " 70.00");
                            } else {
                                if (grand_amount > 300 && grand_amount <= 400) {
                                    express_carrier_charges = 80;
                                    extra_amount = 80;
                                    rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) " + price_currency + " 80.00");
                                } else {
                                    if (grand_amount > 400 && grand_amount <= 500) {
                                        express_carrier_charges = 90;
                                        extra_amount = 90;
                                        rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) " + price_currency + " 90.00");
                                    } else {
                                        if (grand_amount > 500 && grand_amount <= 600) {
                                            express_carrier_charges = 100;
                                            extra_amount = 100;
                                            rb_express_carrier.setText("Express Carrier(Free Delivery in /3-5 Working Days) " + price_currency + " 100.00");
                                        } else {
                                            if (grand_amount > 600 && grand_amount <= 700) {
                                                express_carrier_charges = 110;
                                                extra_amount = 110;
                                                rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) " + price_currency + " 110.00");
                                            } else {
                                                if (grand_amount > 700 && grand_amount <= 800) {
                                                    express_carrier_charges = 120;
                                                    extra_amount = 120;
                                                    rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) " + price_currency + " 120.00");
                                                } else {
                                                    if (grand_amount > 800 && grand_amount <= 900) {
                                                        express_carrier_charges = 130;
                                                        extra_amount = 130;
                                                        rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) " + price_currency + " 130.00");
                                                    } else {
                                                        if (grand_amount > 900 && grand_amount <= 1000) {
                                                            express_carrier_charges = 140;
                                                            extra_amount = 140;
                                                            rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) " + price_currency + " 140.00");
                                                        } else {

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

//                if(grand_amount<=100){
//                    express_carrier_charges=50;
//                    grand_amount=Double.parseDouble(cartPOJO.getGrand_total())+50;
//                    rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) "+price_currency+" 50.00");
//                }else{
//                    if(grand_amount>100&&grand_amount<1000){
//                        express_carrier_charges=10;
//                        grand_amount=Double.parseDouble(cartPOJO.getGrand_total())+10;
//                        rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) "+price_currency+" 10.00");
//                    }else{
//                        express_carrier_charges=0;
//                        grand_amount=Double.parseDouble(cartPOJO.getGrand_total());
//                        rb_express_carrier.setText("Express Carrier(Free Delivery in 3-5 Working Days) "+price_currency+" 00.00");
//                    }
//                }
                try {
                    is_standart_delivery = true;

                    if (grand_amount <= 300) {
                        standard_carrier_charges = 50;
                        extra_amount = 50;
                        grand_amount = Double.parseDouble(cartPOJO.getGrand_total()) + 50;
                        rb_standard_carrier.setText("Standard Carrier (Free delivery in 12-15 Working Days) " + price_currency + " 50.00");
                        tv_shipping_charges.setText("Shipping & Handling (Standard Carrier (Free delivery in 12-15 Working Days)) " + price_currency + " 50.00");
                    } else {
                        extra_amount = 0;
                        standard_carrier_charges = 0;
                        grand_amount = Double.parseDouble(cartPOJO.getGrand_total());
                        rb_standard_carrier.setText("Standard Carrier (Free delivery in 12-15 Working Days) " + price_currency + " 00.00");
                        tv_shipping_charges.setText("Shipping & Handling (Standard Carrier (Free delivery in 12-15 Working Days)) " + price_currency + ".00");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rg_shipping_method.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        try {
                            if (rg_shipping_method.getCheckedRadioButtonId() == R.id.rb_standard_carrier) {
                                // Do something with the button
                                is_standart_delivery = true;
                                extra_amount = standard_carrier_charges;
                                grand_amount = Double.parseDouble(cartPOJO.getGrand_total()) + standard_carrier_charges;
                                tv_shipping_charges.setText("Shipping & Handling (Standard Carrier (Free delivery in 12-15 Working Days)) " + price_currency + " " + standard_carrier_charges);
                            } else {
                                is_standart_delivery = false;
                                extra_amount = express_carrier_charges;
                                grand_amount = Double.parseDouble(cartPOJO.getGrand_total()) + extra_amount;
                                tv_shipping_charges.setText("Shipping & Handling (Express Carrier(Free Delivery in 3-5 Working Days)) " + price_currency + " " + express_carrier_charges);
                            }

                            tv_grand_total.setText("GRAND TOTAL     " + price_currency + " " + grand_amount);
                        } catch (Exception e) {
                            e.printStackTrace();
                            tv_grand_total.setText("GRAND TOTAL     " + price_currency + " " + cartPOJO.getGrand_total());
                        }
                    }
                });

                tv_grand_total.setText("GRAND TOTAL     " + price_currency + " " + grand_amount);
            } catch (Exception e) {
                e.printStackTrace();
                tv_grand_total.setText("GRAND TOTAL     " + price_currency + " " + cartPOJO.getGrand_total());
            }
            string_grand_total = cartPOJO.getGrand_total();

        } else {
            finish();
        }

        check_shipping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ll_billing_addr.setVisibility(View.GONE);
                } else {
                    ll_billing_addr.setVisibility(View.VISIBLE);
                }
            }
        });
//        callBillingAPI();

        rb_standard_carrier.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rb_cash_on_delivery.setVisibility(View.VISIBLE);
                } else {
                    rb_cash_on_delivery.setVisibility(View.GONE);
                    rb_online_payment.setChecked(true);
                }
            }
        });

        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callOrderNoAPI();
            }
        });

        btn_add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialog();
            }
        });

        rg_payment_method.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rg_payment_method.getCheckedRadioButtonId() == R.id.rb_cash_on_delivery) {
                    is_online_payment = false;
                } else {
                    is_online_payment = true;
                }
                Log.d(TAG, "is online:-" + is_online_payment);
            }
        });
    }

    public void callCashOnDeliveryAPI(String order_no, NewAddressResultPOJO addressResultPOJO) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        String carrier = "";
        if (is_standart_delivery) {
            carrier = "Standard Carrier - (Delivered in 12-15 Working Days)";
        } else {
            carrier = "Express Carrier(Free Delivery in 3-5 Working Days)";
        }
        nameValuePairs.add(new BasicNameValuePair("payment_method", "cashondelivery"));
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
        nameValuePairs.add(new BasicNameValuePair("region", addressResultPOJO.getRegion()));
        nameValuePairs.add(new BasicNameValuePair("postcode", addressResultPOJO.getPostcode()));
        nameValuePairs.add(new BasicNameValuePair("lastname", addressResultPOJO.getLastname()));
        nameValuePairs.add(new BasicNameValuePair("street", addressResultPOJO.getStreet()));
        nameValuePairs.add(new BasicNameValuePair("city", addressResultPOJO.getCity()));
        nameValuePairs.add(new BasicNameValuePair("email", Pref.GetStringPref(getApplicationContext(), StringUtils.EMAIL_ID, "")));
        nameValuePairs.add(new BasicNameValuePair("telephone", addressResultPOJO.getTelephone()));
        nameValuePairs.add(new BasicNameValuePair("country_id", addressResultPOJO.getCountryId()));
        nameValuePairs.add(new BasicNameValuePair("firstname", addressResultPOJO.getFirstname()));
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
        nameValuePairs.add(new BasicNameValuePair("shipping_name", addressResultPOJO.getFirstname()));
        nameValuePairs.add(new BasicNameValuePair("billing_name", addressResultPOJO.getFirstname()));
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
//        nameValuePairs.add(new BasicNameValuePair("customer_middlename", Pref.GetStringPref(getApplicationContext(), StringUtils.MIDDLE_NAME, "")));
        new WebServiceBase(nameValuePairs, this, CASH_ON_DELIVERY_API).execute(WebServicesUrls.CASH_ON_DELIVERY_URL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        callShippingAPI();
    }

    private final String CALL_GATEWAY_API = "call_gateway_api";

    public void callOrderNoAPI() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        new WebServiceBase(nameValuePairs, this, CALL_GATEWAY_API).execute(WebServicesUrls.ORDER_NO_URL);
    }

    public void addDialog() {
        final Dialog dialog1 = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog1.setContentView(R.layout.dialog_addr_choice);
        dialog1.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog1.setTitle("Select");
        dialog1.setCancelable(true);
        dialog1.show();
        Window window = dialog1.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button btn_close = (Button) dialog1.findViewById(R.id.btn_close);
        TextView tv_billingaddr = (TextView) dialog1.findViewById(R.id.tv_billingaddr);
        TextView tv_shippingaddr = (TextView) dialog1.findViewById(R.id.tv_shippingaddr);

        tv_billingaddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartCheckOutActivity.this, AddNewAddressActivity.class);
                intent.putExtra("isbilling", true);
                startActivity(intent);
                dialog1.dismiss();
            }
        });

        tv_shippingaddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartCheckOutActivity.this, AddNewAddressActivity.class);
                intent.putExtra("isbilling", false);
                startActivity(intent);
                dialog1.dismiss();
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
    }

    public void callShippingAPI() {
        if (addressResultPOJOs != null && addressResultPOJOs.size() > 0) {
            addressResultPOJOs.clear();
        }
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        Log.d(TAG, "user_id:-" + Pref.GetStringPref(getApplicationContext(), StringUtils.ENTITY_ID, ""));
        nameValuePairs.add(new BasicNameValuePair("usre_id", Pref.GetStringPref(getApplicationContext(), StringUtils.ENTITY_ID, "")));
        new WebServiceBase(nameValuePairs, this, SHIPPING_API_CALL).execute(WebServicesUrls.NEW_GET_ADDR);
    }

    public void callBillingAPI() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("user_id", Pref.GetStringPref(getApplicationContext(), StringUtils.ENTITY_ID, "")));
        new WebServiceBase(nameValuePairs, this, BILLING_API_CALL).execute(WebServicesUrls.GET_ALL_BILLING_ADDRESS_URL);
    }

    public void inflateCartItems(List<CartResultPOJO> cartPOJOs) {
        ll_scroll_orders.removeAllViews();
        for (int i = 0; i < cartPOJOs.size(); i++) {
            final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.inflate_cart_items, null);
            TextView tv_product_name = (TextView) view.findViewById(R.id.tv_product_name);
            TextView tv_qty = (TextView) view.findViewById(R.id.tv_qty);
            TextView tv_price = (TextView) view.findViewById(R.id.tv_price);

            tv_product_name.setText(cartPOJOs.get(i).getName());
            tv_qty.setText(getConvertedPrice(cartPOJOs.get(i).getQty()));
            tv_price.setText(price_currency + " " + getConvertedPrice(cartPOJOs.get(i).getPrice()));


            ll_scroll_orders.addView(view);
        }
    }

    public String getConvertedPrice(String price) {
        try {
            double val = Double.parseDouble(price);
            DecimalFormat f = new DecimalFormat("##.00");
            return String.valueOf(f.format(val));
        } catch (Exception e) {
            e.printStackTrace();
            return price;
        }
    }


    public int converttoInt(String str) {
        try {
            double num = Double.parseDouble(str);
            return (int) num;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetMsg(String[] msg) {
        String apicall = msg[0];
        String response = msg[1];
        switch (apicall) {
            case BILLING_API_CALL:
                parseBillingResponse(response);
                break;
            case SHIPPING_API_CALL:
                parseShippingResponse(response);
                break;
            case CALL_GATEWAY_API:
                parseGatewayAPIResponse(response);
                break;
            case CASH_ON_DELIVERY_API:
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
                startActivity(new Intent(CartCheckOutActivity.this, HomeActivity.class));
                finishAffinity();
            }
        }catch (Exception e){

        }
    }

    public void showOrderDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                CartCheckOutActivity.this);
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
        if (addressResultPOJO != null) {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("payment_method", "cashondelivery"));
            nameValuePairs.add(new BasicNameValuePair("shipping_name", addressResultPOJO.getFirstname() + " " + addressResultPOJO.getLastname()));
            nameValuePairs.add(new BasicNameValuePair("street", addressResultPOJO.getStreet()));
            nameValuePairs.add(new BasicNameValuePair("region", addressResultPOJO.getRegion()));
            nameValuePairs.add(new BasicNameValuePair("postcode", addressResultPOJO.getPostcode()));
            nameValuePairs.add(new BasicNameValuePair("telephone", addressResultPOJO.getTelephone()));
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



    public void parseCashOnDeliveryResponse(String response) {
        Log.d(TAG, "cash On delivery response:-" + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("success").equals("true")) {
                JSONObject result = jsonObject.optJSONObject("result");
                if (result.optString("status").equals("accepted")) {
                    ToastClass.showShortToast(getApplicationContext(), "Your Order has been accepted.");
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

    String order_no = "";

    public void parseGatewayAPIResponse(String response) {
        if (addressResultPOJOs != null && addressResultPOJOs.size() > 0) {
            int index = -1;
            if (ll_billing_addr.getVisibility() == View.GONE) {
                index = spinner_address.getSelectedItemPosition();
            } else {
                index = spinner_billing_address.getSelectedItemPosition();
            }
            if (index != -1) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String orderno = jsonObject.optString("order_no");
                    if (orderno.equals("")) {
                        ToastClass.showShortToast(getApplicationContext(), "Something went wrong");
                    } else {
                        order_no = orderno;
                        addressResultPOJO = addressResultPOJOs.get(index);
                        if (is_online_payment) {
                            String carrier="";
                            if (is_standart_delivery) {
                                carrier = "Standard Carrier - (Delivered in 12-15 Working Days)";
                            } else {
                                carrier = "Express Carrier(Free Delivery in 3-5 Working Days)";
                            }
                            Log.d(TAG, "grand amount:-" + grand_amount);
                            Intent intent = new Intent(CartCheckOutActivity.this, CartPaymentWebViewActivity.class);
                            intent.putExtra("addresspojo",addressResultPOJO);
                            intent.putExtra("cartpojo",cartPOJO);
                            intent.putExtra("carrier",carrier);
                            intent.putExtra("extra_amount",String.valueOf(extra_amount));
                            intent.putExtra("user_name", addressResultPOJO.getFirstname() + " " + addressResultPOJO.getLastname());
                            intent.putExtra("user_address", addressResultPOJO.getCity() + "," + addressResultPOJO.getRegion() + "," + addressResultPOJO.getCountryId());
                            intent.putExtra("user_zipcode", addressResultPOJO.getPostcode());
                            intent.putExtra("user_city", addressResultPOJO.getCity());
                            intent.putExtra("user_state", addressResultPOJO.getRegion());
                            intent.putExtra("user_country", addressResultPOJO.getCountryId());
                            intent.putExtra("user_phone", addressResultPOJO.getTelephone());
                            intent.putExtra("user_email", Pref.GetStringPref(getApplicationContext(), StringUtils.EMAIL_ID, ""));
                            intent.putExtra("user_id", Pref.GetStringPref(getApplicationContext(), StringUtils.ENTITY_ID, ""));
                            intent.putExtra("modelno", addressResultPOJO.getTelephone());
                            intent.putExtra("finalamount", String.valueOf(grand_amount));
                            intent.putExtra("order_no", orderno);
                            startActivity(intent);
                        } else {
                            callCashOnDeliveryAPI(orderno, addressResultPOJO);
                        }

                    }
                } catch (Exception e) {
                    ToastClass.showShortToast(getApplicationContext(), "Something went wrong");
                }
            } else {
                ToastClass.showShortToast(getApplicationContext(), "No Address Selected");
            }

        } else {
            ToastClass.showShortToast(getApplicationContext(), "No Address Selected");
        }
        Log.d(TAG, "gateway response:-" + response);

    }

    NewAddressResultPOJO addressResultPOJO;

    List<AddressResultPOJO> list_billing;
    List<AddressResultPOJO> list_shipping;

    public void parseBillingResponse(String response) {
        Log.d(TAG, "billing response:-" + response);
        try {
            Gson gson = new Gson();
            AddressPOJO addressPOJO = gson.fromJson(response, AddressPOJO.class);
            if (addressPOJO.getSuccess().equals("true")) {
                list_billing = addressPOJO.getAddressResultPOJOList();
                List<String> list = new ArrayList<>();
                for (AddressResultPOJO addressResultPOJO : list_billing) {
                    list.add(addressResultPOJO.getFirstname() + ", "
                            + addressResultPOJO.getLastname() + ", "
                            + addressResultPOJO.getStreet() + ", "
                            + addressResultPOJO.getCity() + ", "
                            + addressResultPOJO.getCountryId() + ", "
                    );
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_address.setAdapter(spinnerArrayAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<NewAddressResultPOJO> addressResultPOJOs;

    public void parseShippingResponse(String response) {
        Log.d(TAG, "shipping response:-" + response);

        try {

            Gson gson = new Gson();
            NewAddressPOJO newAddressPOJO = gson.fromJson(response, NewAddressPOJO.class);
            if (newAddressPOJO.getSuccess().equals("true")) {
                addressResultPOJOs = newAddressPOJO.getNewAddressResultPOJOList();

                List<String> list = new ArrayList<>();
                for (NewAddressResultPOJO newAddressResultPOJO : newAddressPOJO.getNewAddressResultPOJOList()) {
                    list.add(newAddressResultPOJO.getFirstname() + ", "
                            + newAddressResultPOJO.getLastname() + ", "
                            + newAddressResultPOJO.getStreet() + ", "
                            + newAddressResultPOJO.getCity() + ", "
                            + newAddressResultPOJO.getCountryId() + ", "
                    );
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_billing_address.setAdapter(spinnerArrayAdapter);

                ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list); //selected item will look like a spinner set from XML
                spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_address.setAdapter(spinnerArrayAdapter2);


            } else {
                ToastClass.showShortToast(getApplicationContext(), "No Address Found");
            }
        } catch (
                Exception e)

        {
            e.printStackTrace();
        }

    }

    public AddressResultPOJO getAddress(List<JSONObject> stringListMap) {
        AddressResultPOJO addressResultPOJO = new AddressResultPOJO();

        String region = "";
        String postcodes = "";
        String lastname = "";
        String street = "";
        String city = "";
        String email = "";
        String telephone = "";
        String countryId = "";
        String firstname = "";
        String addressType = "";
        email = Pref.GetStringPref(getApplicationContext(), StringUtils.EMAIL_ID, "");
        for (JSONObject jsonObject : stringListMap) {
            String attribute_id = jsonObject.optString("attribute_id");
            switch (attribute_id) {
                case "20":
                    firstname = jsonObject.optString("value");
                    break;
                case "22":
                    lastname = jsonObject.optString("value");
                    break;
                case "26":
                    city = jsonObject.optString("value");
                    break;
                case "27":
                    countryId = jsonObject.optString("value");
                    break;
                case "28":
                    region = jsonObject.optString("value");
                    break;
                case "30":
                    postcodes = jsonObject.optString("value");
                    break;
                case "31":
                    telephone = jsonObject.optString("value");
                    break;
            }
        }
        addressResultPOJO.setRegion(region);
        addressResultPOJO.setPostcodes(postcodes);
        addressResultPOJO.setLastname(lastname);
        addressResultPOJO.setStreet(street);
        addressResultPOJO.setCity(city);
        addressResultPOJO.setEmail(email);
        addressResultPOJO.setTelephone(telephone);
        addressResultPOJO.setCountryId(countryId);
        addressResultPOJO.setFirstname(firstname);
        addressResultPOJO.setAddressType(addressType);
        return addressResultPOJO;
    }
}
