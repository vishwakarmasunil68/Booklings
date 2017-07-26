package com.emobi.bjain.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.emobi.bjain.R;
import com.emobi.bjain.activity.HomeActivity;
import com.emobi.bjain.activity.ZxingScanner;
import com.emobi.bjain.adapter.ScanProductAdapter;
import com.emobi.bjain.pojo.scanpojo.NewSearchResultPOJO;
import com.emobi.bjain.utils.TagUtils;
import com.emobi.bjain.utils.ToastClass;
import com.emobi.bjain.webservice.WebServiceBase;
import com.emobi.bjain.webservice.WebServicesCallBack;
import com.emobi.bjain.webservice.WebServicesUrls;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by sunil on 26-05-2017.
 */

public class ScannerFragment extends Fragment implements WebServicesCallBack {

    private final String TAG = getClass().getSimpleName();
    private final String SEARCH_PRODUCT_API = "search_product_api";


    @BindView(R.id.rv_search)
    RecyclerView rv_search;

    @BindView(R.id.ll_nav_cat)
    LinearLayout ll_nav_cat;
    @BindView(R.id.ll_search)
    LinearLayout ll_search;
    @BindView(R.id.ll_barcode_scan)
    LinearLayout ll_barcode_scan;
    private final static int BARCODE_SCANNER_RESULT = 102;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_scanner, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ll_nav_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity homeActivity= (HomeActivity) getActivity();
                homeActivity.openCatNavDrawer();
            }
        });
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity homeActivity= (HomeActivity) getActivity();
                homeActivity.showSearchFragment();
            }
        });
        ll_barcode_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ZxingScanner.class);
                getActivity().startActivityForResult(i, BARCODE_SCANNER_RESULT);
            }
        });
        Intent i = new Intent(getActivity(), ZxingScanner.class);
        getActivity().startActivityForResult(i, BARCODE_SCANNER_RESULT);
    }



    public void callSearchAPI(String product_name) {
        Log.d(TagUtils.getTag(),"calling scan api");
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("sku", product_name));
        new WebServiceBase(nameValuePairs, getActivity(), this, SEARCH_PRODUCT_API).execute(WebServicesUrls.SCANNER_PRODUCT_URL);
    }

    public void parseSearchResponse(String response) {
        Log.d(TAG, "search response:-" + response);
        try {
            JSONObject jsonObject=new JSONObject(response);
            if(jsonObject.optString("success").equals("true")){
                JSONArray jsonArray=jsonObject.optJSONArray("result");
                String resultstring=jsonArray.optString(0);
                Gson gson=new Gson();
                NewSearchResultPOJO newSearchResultPOJO=gson.fromJson(resultstring,NewSearchResultPOJO.class);
                List<NewSearchResultPOJO> newSearchResultPOJOList=new ArrayList<>();
                newSearchResultPOJOList.add(newSearchResultPOJO);
                inflateAdapter(newSearchResultPOJOList);
            }else{
                ToastClass.showShortToast(getActivity().getApplicationContext(),"No Product Found");
                List<NewSearchResultPOJO> searchResultPOJOList = new ArrayList<>();
                inflateAdapter(searchResultPOJOList);
            }
//            inflateAdapter(searchResultPOJOList);

        } catch (Exception e) {
            ToastClass.showShortToast(getActivity().getApplicationContext(),"No Product Found");
            e.printStackTrace();
            List<NewSearchResultPOJO> searchResultPOJOList = new ArrayList<>();
            inflateAdapter(searchResultPOJOList);
        }
    }

    public void inflateAdapter(List<NewSearchResultPOJO> searchResultPOJOList) {
        ScanProductAdapter adapter = new ScanProductAdapter(getActivity(), searchResultPOJOList);
        GridLayoutManager gridLayoutManager
                = new GridLayoutManager(getActivity(), 3);
        rv_search.setLayoutManager(gridLayoutManager);
        rv_search.setHasFixedSize(true);
        rv_search.setItemAnimator(new DefaultItemAnimator());
        rv_search.setAdapter(adapter);
    }

    @Override
    public void onGetMsg(String[] msg) {
        String apicall = msg[0];
        String response = msg[1];
        switch (apicall) {
            case SEARCH_PRODUCT_API:
                parseSearchResponse(response);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BARCODE_SCANNER_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                Log.d(TagUtils.getTag(),"scanner result:-"+result);
                if (result.equals("cancel")) {
                    Toast.makeText(getApplicationContext(), "Scan Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    String res = "code:-" + data.getStringExtra("code") + "\n" + "format:-" + data.getStringExtra("format");
                    Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
                    callSearchAPI(data.getStringExtra("code"));
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
