package com.emobi.bjain.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emobi.bjain.R;
import com.emobi.bjain.activity.HomeActivity;
import com.emobi.bjain.pojo.CategoryPOJO;
import com.emobi.bjain.pojo.ChildrenPOJO;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.StringUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 03-06-2017.
 */

public class ShopByCategoriesFragment extends Fragment{
    @BindView(R.id.ll_nav_cat_level0)
    LinearLayout ll_nav_cat_level0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_shop_cat,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parseCategoryData(Pref.GetStringPref(getActivity().getApplicationContext(),StringUtils.CATEGORY_DATA,""));
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
            Pref.SetStringPref(getActivity().getApplicationContext(), StringUtils.CATEGORY_DATA,response);
            inflateCategoryLevel0(childrenPOJOs);

        } catch (Exception e) {

        }
    }

    public void inflateCategoryLevel0(final List<ChildrenPOJO> childrenPOJOList) {
        ll_nav_cat_level0.removeAllViews();
        for (int i = 0; i < childrenPOJOList.size(); i++) {
            final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.inflate_ll_child_scroll, null);
            TextView tv_level0 = (TextView) view.findViewById(R.id.tv_level0);
            ImageView iv_cat_drop = (ImageView) view.findViewById(R.id.iv_cat_drop);
            final LinearLayout ll_cat_level_3 = (LinearLayout) view.findViewById(R.id.ll_cat_level_3);
            LinearLayout ll_header_scroll= (LinearLayout) view.findViewById(R.id.ll_header_scroll);
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
            final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                    HomeActivity homeActivity= (HomeActivity) getActivity();
                    homeActivity.removeActiveCenterFragments();
                    homeActivity.showCategoryProductFragment(childrenPOJOList.get(finalI));
                }
            });

            linearLayout.addView(view);
        }
    }
}
