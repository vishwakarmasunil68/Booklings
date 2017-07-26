package com.emobi.bjain.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emobi.bjain.R;
import com.emobi.bjain.pojo.CategoryPOJO;
import com.emobi.bjain.pojo.ChildrenPOJO;
import com.emobi.bjain.webservice.GetWebServices;
import com.emobi.bjain.webservice.WebServicesCallBack;
import com.emobi.bjain.webservice.WebServicesUrls;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements WebServicesCallBack{
    private final String TAG=getClass().getSimpleName();
    private final String IMAGE_SLIDER_API_CALL="image_slider_apicall";
    private final String CATEGORY_API_CALL="category_api_call";
    @BindView(R.id.ll_nav_cat_level0)
    LinearLayout ll_nav_cat_level0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        new GetWebServices(this,CATEGORY_API_CALL).execute(WebServicesUrls.CATEGORY_URL);

    }

    @Override
    public void onGetMsg(String[] msg) {
        String apicall=msg[0];
        String resoponse=msg[1];
        switch (apicall){

            case CATEGORY_API_CALL:
                parseCategoryData(resoponse);
                break;
        }
    }

    public void parseCategoryData(String response){
        try{
            Gson gson=new Gson();
            CategoryPOJO categoryPOJO=gson.fromJson(response,CategoryPOJO.class);
//            Log.d(TAG,"category pojos:-"+categoryPOJO.toString());

            List<ChildrenPOJO> childrenPOJOList=categoryPOJO.getCategoryResultPOJO().getChildrenPOJOList().get(0).getChildrenPOJOs();

            List<ChildrenPOJO> childrenPOJOs=new ArrayList<>();

            for(ChildrenPOJO childrenPOJO:childrenPOJOList){
                if(childrenPOJO.getName().equalsIgnoreCase("homeopathy")
                        ||childrenPOJO.getName().equalsIgnoreCase("medical")
                        ||childrenPOJO.getName().equalsIgnoreCase("general interest")
                        ||childrenPOJO.getName().equalsIgnoreCase("accessories")
                        ) {
                    childrenPOJOs.add(childrenPOJO);
                }
                Log.d(TAG,"child name:-"+childrenPOJO.getName());
            }
            inflateCategoryLevel0(childrenPOJOs);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void inflateCategoryLevel0(final List<ChildrenPOJO> childrenPOJOList){
        ll_nav_cat_level0.removeAllViews();
        for(int i=0;i<childrenPOJOList.size();i++) {
            final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.inflate_ll_child_scroll, null);
            TextView tv_level0 = (TextView) view.findViewById(R.id.tv_level0);
            ImageView iv_cat_drop = (ImageView) view.findViewById(R.id.iv_cat_drop);
            final LinearLayout ll_cat_level_3 = (LinearLayout) view.findViewById(R.id.ll_cat_level_3);

            tv_level0.setText(childrenPOJOList.get(i).getName());
            final int finalI = i;
            if(childrenPOJOList.get(finalI).getChildrenPOJOs().size()>0){
                inflateCategoryLevel1(ll_cat_level_3,childrenPOJOList.get(finalI).getChildrenPOJOs());
            }
//            Log.d(TAG,"name:-"+childrenPOJOList.get(i).getName());
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

    public void inflateCategoryLevel1(LinearLayout linearLayout,final List<ChildrenPOJO> childrenPOJOList){
        ll_nav_cat_level0.removeAllViews();
        for(int i=0;i<childrenPOJOList.size();i++) {
            final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.inflate_level_cat3, null);
            TextView tv_cat_level_3 = (TextView) view.findViewById(R.id.tv_cat_level_3);

            tv_cat_level_3.setText(childrenPOJOList.get(i).getName());
            Log.d(TAG,"sub name name:-"+childrenPOJOList.get(i).getName());


            linearLayout.addView(view);
        }
    }
}
