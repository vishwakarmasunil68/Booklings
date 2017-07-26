package com.emobi.bjain.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emobi.bjain.R;
import com.emobi.bjain.adapter.FeatureListProductAdapter;
import com.emobi.bjain.pojo.featurelist.FeatureDataPOJO;
import com.emobi.bjain.pojo.featurelist.FeaturePOJO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 02-05-2017.
 */

public class ViewAllFragment extends Fragment{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_feature_products)
    RecyclerView rv_feature_products;
    FeaturePOJO featurePOJO;
    List<FeatureDataPOJO> featureDataPOJOList;
    public ViewAllFragment(FeaturePOJO featurePOJO){
        this.featurePOJO=featurePOJO;
    }
    public ViewAllFragment(List<FeatureDataPOJO> featureDataPOJOList){
        this.featureDataPOJOList=featureDataPOJOList;
    }
    public ViewAllFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_feature_view_all,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(featurePOJO!=null){
            GridLayoutManager horizontalLayoutManagaer
                    = new GridLayoutManager(getActivity(), 3);
            rv_feature_products.setLayoutManager(horizontalLayoutManagaer);
            FeatureListProductAdapter featureListProductAdapter=new FeatureListProductAdapter(getActivity(),featurePOJO.getFeatureDataPOJOList());
            rv_feature_products.setAdapter(featureListProductAdapter);
        }else{
            if(featureDataPOJOList!=null&&featureDataPOJOList.size()>0){
                GridLayoutManager horizontalLayoutManagaer
                        = new GridLayoutManager(getActivity(), 3);
                rv_feature_products.setLayoutManager(horizontalLayoutManagaer);
                FeatureListProductAdapter featureListProductAdapter=new FeatureListProductAdapter(getActivity(),featureDataPOJOList);
                rv_feature_products.setAdapter(featureListProductAdapter);
            }
        }
    }
}
