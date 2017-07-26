package com.emobi.bjain.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.emobi.bjain.R;
import com.emobi.bjain.adapter.FeatureListProductAdapter;
import com.emobi.bjain.pojo.featurelist.FeaturePOJO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeatureViewAllActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_feature_products)
    RecyclerView rv_feature_products;
    FeaturePOJO featurePOJO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_view_all);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Feature Product List");

        featurePOJO= (FeaturePOJO) getIntent().getSerializableExtra("pojo");
        if(featurePOJO!=null){
            GridLayoutManager horizontalLayoutManagaer
                    = new GridLayoutManager(FeatureViewAllActivity.this, 3);
            rv_feature_products.setLayoutManager(horizontalLayoutManagaer);
            FeatureListProductAdapter featureListProductAdapter=new FeatureListProductAdapter(FeatureViewAllActivity.this,featurePOJO.getFeatureDataPOJOList());
            rv_feature_products.setAdapter(featureListProductAdapter);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
