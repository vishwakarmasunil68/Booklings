package com.emobi.bjain.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emobi.bjain.R;
import com.emobi.bjain.pojo.neworder.NewOrderPOJO;
import com.emobi.bjain.pojo.neworder.NewOrderResultPOJO;
import com.emobi.bjain.pojo.order.OrderPOJOResult;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.StringUtils;
import com.emobi.bjain.utils.ToastClass;
import com.emobi.bjain.webservice.WebServiceBase;
import com.emobi.bjain.webservice.WebServicesCallBack;
import com.emobi.bjain.webservice.WebServicesUrls;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity implements WebServicesCallBack {

    private final String TAG = getClass().getSimpleName();
    private final String ORDER_LIST_API = "order_list_api";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_scroll)
    LinearLayout ll_scroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Orders");
    }

    @Override
    protected void onResume() {
        super.onResume();
        callOrderListAPI();
    }

    public void callOrderListAPI() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("user_id", Pref.GetStringPref(getApplicationContext(), StringUtils.ENTITY_ID, "")));
        new WebServiceBase(nameValuePairs, this, ORDER_LIST_API).execute(WebServicesUrls.ORDER_LIST_URL);
//            parseOrderListResponse(Pref.orders);
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
            case ORDER_LIST_API:
//                parseOrderListResponse(response);
                parseNewOrderResponse(response);
                break;
        }
    }

    public void parseNewOrderResponse(String response){
        Log.d(TAG,"new order response:-"+response);
        try{
            Gson gson=new Gson();
            NewOrderPOJO newOrderPOJO=gson.fromJson(response,NewOrderPOJO.class);
            if(newOrderPOJO.getSuccess().equals("true")){
                inflateOrders(newOrderPOJO.getNewOrderResultPOJOList());
            }else{
                ToastClass.showShortToast(getApplicationContext(),"No Orders Found");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void parseOrderListResponse(String response) {
//        Log.d(TAG,"response:-"+response);
        try {
            JSONArray jsonArray = new JSONArray(response);
            if (jsonArray.length() > 0) {
                Toast.makeText(getApplicationContext(), "Total Orders:-" + jsonArray.length(), Toast.LENGTH_LONG).show();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<OrderPOJOResult>>() {
                }.getType();
                List<OrderPOJOResult> orderPOJOResultList = new Gson().fromJson(response, listType);
                Log.d(TAG, "orderPOJOResultList:-" + orderPOJOResultList.toString());
//                inflateOrders(orderPOJOResultList);
            } else {
                Toast.makeText(getApplicationContext(), "No Orders Found", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    public void inflateOrders(List<NewOrderResultPOJO> orderPOJOResultList) {
        ll_scroll.removeAllViews();
        for (int i = 0; i < orderPOJOResultList.size(); i++) {
            final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.inflate_orders_list, null);
            TextView tv_order_no = (TextView) view.findViewById(R.id.tv_order_no);
            TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
            TextView tv_ship_to = (TextView) view.findViewById(R.id.tv_ship_to);
            TextView tv_view_order = (TextView) view.findViewById(R.id.tv_view_order);
            TextView tv_reorder = (TextView) view.findViewById(R.id.tv_reorder);
            TextView tv_order_total = (TextView) view.findViewById(R.id.tv_order_total);
            TextView tv_status = (TextView) view.findViewById(R.id.tv_status);


            final NewOrderResultPOJO orderPOJOResult = orderPOJOResultList.get(i);
            tv_order_no.setText(orderPOJOResult.getIncrementId());
            tv_date.setText(orderPOJOResult.getCreatedAt());
            tv_ship_to.setText(orderPOJOResult.getFirstname()+" "+orderPOJOResult.getLastname());
            tv_order_total.setText(orderPOJOResult.getGrandTotal() + " " + orderPOJOResult.getOrderCurrencyCode());
            tv_status.setText(orderPOJOResult.getStatus());


            tv_view_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(OrderActivity.this, OrderDetailActivity.class);
                    intent.putExtra("order_id", orderPOJOResult.getIncrementId());
                    startActivity(intent);
                }
            });


            ll_scroll.addView(view);
        }
    }
}
