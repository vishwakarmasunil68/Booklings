package com.emobi.bjain.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emobi.bjain.R;
import com.emobi.bjain.activity.HomeActivity;
import com.emobi.bjain.adapter.FeatureListProductAdapter;
import com.emobi.bjain.pojo.ChildrenPOJO;
import com.emobi.bjain.pojo.NewCategoryStringPOJO;
import com.emobi.bjain.pojo.featurelist.FeatureDataPOJO;
import com.emobi.bjain.pojo.newcategory.NewCategoryDataPOJO;
import com.emobi.bjain.pojo.newcategory.NewCategoryResultPOJO;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.TagUtils;
import com.emobi.bjain.utils.ToastClass;
import com.emobi.bjain.webservice.WebServiceBase;
import com.emobi.bjain.webservice.WebServicesCallBack;
import com.emobi.bjain.webservice.WebServicesUrls;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sunil on 02-05-2017.
 */

public class CategoryProductFragment extends Fragment implements WebServicesCallBack {

    private final String TAG = getClass().getSimpleName();
    private final static String CATEGORY_PRODUCT_API = "category_product_api";

    RecyclerView rv_category_products;
    LinearLayout ll_nav_cat;
    TextView tv_category;
    ChildrenPOJO childrenPOJO;

    public CategoryProductFragment(ChildrenPOJO childrenPOJO) {
        this.childrenPOJO = childrenPOJO;
    }

    public CategoryProductFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_category_products, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_category_products = (RecyclerView) view.findViewById(R.id.rv_category_products);
        ll_nav_cat = (LinearLayout) view.findViewById(R.id.ll_nav_cat);
        tv_category = (TextView) view.findViewById(R.id.tv_category);


        if (childrenPOJO != null) {
            String title=childrenPOJO.getName();
            title=title.toUpperCase();
            tv_category.setText(title);
        }

//        getAllCategoryProducts(childrenPOJO.getCategory_id());
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
        getAllCategoryProducts(childrenPOJO.getCategory_id());
//        parseCategoryData(Pref.category_product_string);
    }


    public void getAllCategoryProducts(String category_id) {
//        Log.d(TAG, "category_id:-" + category_id);
//        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//        nameValuePairs.add(new BasicNameValuePair("cat_id", category_id));
//        nameValuePairs.add(new BasicNameValuePair("type", Pref.GetCurrency(getActivity().getApplicationContext())));
//        new WebServiceBase(nameValuePairs, getActivity(), this, CATEGORY_PRODUCT_API).execute(WebServicesUrls.NEW_PRODUCT_LIST_CATEGORY_WISE);
        callCategoryProductAPI(category_id);
    }


    @Override
    public void onGetMsg(String[] msg) {
        String apicall = msg[0];
        String response = msg[1];
        switch (apicall) {
            case CATEGORY_PRODUCT_API:
//                parseCategoryData(response);
                parseNewCategoryData(response);
                break;
        }
    }
    public void callCategoryProductAPI(final String category_id){
        new AsyncTask<String,Void,String>(){
            String jResult;
            ProgressDialog progressDialog;
            List<FeatureDataPOJO> featureDataPOJOList;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(true);
                progressDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("cat_id", category_id));
                    nameValuePairs.add(new BasicNameValuePair("type", Pref.GetCurrency(getActivity().getApplicationContext())));
                    Log.d(TagUtils.getTag(),"category product data:-"+nameValuePairs.toString());
                    jResult = WebServiceBase.httpCall(params[0], nameValuePairs);

                    featureDataPOJOList=parseNewCategoryData(jResult);


                } catch (Exception e) {
                    if(progressDialog!=null){
                        progressDialog.dismiss();
                    }
                    e.printStackTrace();
                }
                return jResult;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(progressDialog!=null){
                    progressDialog.dismiss();
                }
                if(featureDataPOJOList!=null&&featureDataPOJOList.size()>0) {
                    Log.d(TAG, "category pojos:-" + featureDataPOJOList.toString());
                    GridLayoutManager horizontalLayoutManagaer
                            = new GridLayoutManager(getActivity(), 3);
                    rv_category_products.setLayoutManager(horizontalLayoutManagaer);
                    FeatureListProductAdapter featureListProductAdapter = new FeatureListProductAdapter(getActivity(), featureDataPOJOList);
                    rv_category_products.setAdapter(featureListProductAdapter);
                }else{
                    ToastClass.showLongToast(getActivity().getApplicationContext(),"No Books Found");
                }
            }
        }.execute(WebServicesUrls.NEW_PRODUCT_LIST_CATEGORY_WISE);
    }

    public List<FeatureDataPOJO> parseNewCategoryData(String response) {
        Log.d(TAG, "response:-" + response);
        try {
            Gson gson = new Gson();
            NewCategoryStringPOJO newCategoryPOJO = gson.fromJson(response, NewCategoryStringPOJO.class);
            if (newCategoryPOJO.getSuccess().equals("true")) {
                List<NewCategoryResultPOJO> newCategoryResultPOJOList = new ArrayList<>();
                for(String s:newCategoryPOJO.getNewCategoryResultPOJOList()){
                    if(!s.equals("false")){
                        Gson gson1=new Gson();
                        NewCategoryResultPOJO newCategoryResultPOJO=gson1.fromJson(s, NewCategoryResultPOJO.class);
                        newCategoryResultPOJOList.add(newCategoryResultPOJO);
                    }
                }

                Set<String> stringSet = new HashSet<>();
                for (NewCategoryResultPOJO newCategoryResultPOJO : newCategoryResultPOJOList) {
                    stringSet.add(newCategoryResultPOJO.getEntityId());
                }

                List<NewCategoryDataPOJO> newCategoryDataPOJOList = new ArrayList<>();
                List<FeatureDataPOJO> featureDataPOJOList = new ArrayList<>();
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
                                    featureDataPOJO.setUrl(WebServicesUrls.IMAGE_BASE_URL+newCategoryResultPOJO.getValue());
                                    break;
                            }
                        }
                    }
                    newCategoryDataPOJOList.add(newCategoryDataPOJO);
                    featureDataPOJOList.add(featureDataPOJO);
                }

                return featureDataPOJOList;


            }else{
                ToastClass.showShortToast(getActivity().getApplicationContext(),"No Books Found");
            }
        } catch (Exception e) {
            ToastClass.showShortToast(getActivity().getApplicationContext(),"No Books Found");
            e.printStackTrace();
        }
        return null;
    }

//    public void parseCategoryData(String response){
//        Log.d(TAG,"response:-"+response);
//        try{
//            Gson gson=new Gson();
//            CategoryProductPOJO categoryProductPOJO=gson.fromJson(response,CategoryProductPOJO.class);
//            GridLayoutManager horizontalLayoutManagaer
//                    = new GridLayoutManager(getActivity(), 3);
//            rv_category_products.setLayoutManager(horizontalLayoutManagaer);
//            FeatureListProductAdapter featureListProductAdapter=new FeatureListProductAdapter(getActivity(),categoryProductPOJO.getFeatureDataPOJOList());
//            rv_category_products.setAdapter(featureListProductAdapter);
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            ToastClass.showLongToast(getActivity().getApplicationContext(),"No Product Found");
//        }
//    }
}
