package com.emobi.bjain.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emobi.bjain.R;
import com.emobi.bjain.pojo.order.ItemPOJO;
import com.emobi.bjain.pojo.order.OrderDetailPOJO;
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

public class OrderDetailActivity extends AppCompatActivity implements WebServicesCallBack {

    private final String TAG = getClass().getSimpleName();
    private final static String ORDER_API_CALL = "order_api_call";


    @BindView(R.id.tv_order_no)
    TextView tv_order_no;
    @BindView(R.id.tv_order_date)
    TextView tv_order_date;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_company)
    TextView tv_company;
    @BindView(R.id.tv_region)
    TextView tv_region;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_country)
    TextView tv_country;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_fax)
    TextView tv_fax;
    @BindView(R.id.tv_billing_name)
    TextView tv_billing_name;
    @BindView(R.id.tv_billing_company)
    TextView tv_billing_company;
    @BindView(R.id.tv_billing_region)
    TextView tv_billing_region;
    @BindView(R.id.tv_billing_city)
    TextView tv_billing_city;
    @BindView(R.id.tv_billing_country)
    TextView tv_billing_country;
    @BindView(R.id.tv_billing_phone)
    TextView tv_billing_phone;
    @BindView(R.id.tv_billing_fax)
    TextView tv_billing_fax;
    @BindView(R.id.tv_sub_total)
    TextView tv_sub_total;
    @BindView(R.id.tv_grand_total)
    TextView tv_grand_total;

    @BindView(R.id.ll_items_scroll)
    LinearLayout ll_items_scroll;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Order Detail");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String order_id = bundle.getString("order_id");
            Log.d(TAG, "order id:-" + order_id);
            callOrderListAPI(order_id);
        } else {
            finish();
        }
    }

    public void callOrderListAPI(String order_id) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("orderid", order_id));
        new WebServiceBase(nameValuePairs, this, ORDER_API_CALL).execute(WebServicesUrls.ORDER_DETAIL_API);
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
            case ORDER_API_CALL:
                parseOderData(response);
                break;
        }
    }

    public void parseOderData(String response) {
        Log.d(TAG, "response:-" + response);
        try {
            Gson gson = new Gson();
            OrderDetailPOJO orderDetailPOJO = gson.fromJson(response, OrderDetailPOJO.class);
            Log.d(TAG, "order detail:-" + orderDetailPOJO.toString());
            tv_order_no.setText("Order #" + orderDetailPOJO.getIncrement_id() + "-Accepted");
            tv_order_date.setText(orderDetailPOJO.getCreated_at());
            tv_name.setText(orderDetailPOJO.getShippingPOJO().getFirstname() + " " +
                    orderDetailPOJO.getShippingPOJO().getLastname());

            tv_company.setText(orderDetailPOJO.getShippingPOJO().getCompany());
            tv_region.setText(orderDetailPOJO.getShippingPOJO().getStreet());
            tv_city.setText(orderDetailPOJO.getShippingPOJO().getCity() + "," +
                    orderDetailPOJO.getShippingPOJO().getRegion() + "," + orderDetailPOJO.getShippingPOJO().getPostcode());
            tv_country.setText(orderDetailPOJO.getShippingPOJO().getCountry_id());
            tv_phone.setText("Phone : "+orderDetailPOJO.getShippingPOJO().getTelephone());
            tv_fax.setText("Fax : "+orderDetailPOJO.getShippingPOJO().getFax());


//            for billing info
            tv_billing_name.setText(orderDetailPOJO.getBiShippingPOJO().getFirstname() + " " +
                    orderDetailPOJO.getBiShippingPOJO().getLastname());

            tv_billing_company.setText(orderDetailPOJO.getBiShippingPOJO().getCompany());
            tv_billing_region.setText(orderDetailPOJO.getBiShippingPOJO().getStreet());
            tv_billing_city.setText(orderDetailPOJO.getBiShippingPOJO().getCity() + "," +
                    orderDetailPOJO.getBiShippingPOJO().getRegion() + "," + orderDetailPOJO.getBiShippingPOJO().getPostcode());
            tv_billing_country.setText(orderDetailPOJO.getBiShippingPOJO().getCountry_id());
            tv_billing_phone.setText("Phone : "+orderDetailPOJO.getBiShippingPOJO().getTelephone());
            tv_billing_fax.setText("Fax : "+orderDetailPOJO.getBiShippingPOJO().getFax());

            tv_sub_total.setText("Sub Total : "+orderDetailPOJO.getSubtotal());
            tv_grand_total.setText("Grand Total : "+orderDetailPOJO.getGrand_total());

            inflateItems(orderDetailPOJO.getItemPOJOList());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inflateItems(List<ItemPOJO> itemPOJOList) {
        ll_items_scroll.removeAllViews();
        for (int i = 0; i < itemPOJOList.size(); i++) {
            final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.inflate_items_ordered, null);

            TextView tv_product_name = (TextView) view.findViewById(R.id.tv_product_name);
            TextView tv_sku = (TextView) view.findViewById(R.id.tv_sku);
            TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
            TextView tv_qty = (TextView) view.findViewById(R.id.tv_qty);
            TextView tv_subtotal = (TextView) view.findViewById(R.id.tv_subtotal);


            tv_product_name.setText("Product Name : "+itemPOJOList.get(i).getName());
            tv_sku.setText("SKU : "+itemPOJOList.get(i).getSku());
            tv_price.setText("Price : "+itemPOJOList.get(i).getPrice());
            tv_qty.setText("Qty : "+itemPOJOList.get(i).getQtyOrdered());
            tv_subtotal.setText("Subtotal : "+itemPOJOList.get(i).getOriginalPrice());


            ll_items_scroll.addView(view);
        }
    }
}
