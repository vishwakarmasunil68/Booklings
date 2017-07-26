package com.emobi.bjain.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.emobi.bjain.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactUsActivity extends AppCompatActivity implements OnMapReadyCallback {
    @BindView(R.id.tv_bjain_pub)
    TextView tv_bjain_pub;
    @BindView(R.id.tv_homeopathy_heritage)
    TextView tv_homeopathy_heritage;
    @BindView(R.id.tv_radar_opus)
    TextView tv_radar_opus;

    private GoogleMap mMap;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Contact Us");


        String bjain="<u>B. Jain Publishers Pvt. Ltd.</u>";
        String homeopathy="<u>Homeopathy Heritage - Journal</u>";
        String RadarOpus="<u>RadarOpus - homeopathy Software</u>";

        tv_bjain_pub.setText(Html.fromHtml(bjain));
        tv_homeopathy_heritage.setText(Html.fromHtml(homeopathy));
        tv_radar_opus.setText(Html.fromHtml(RadarOpus));

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng bjain = new LatLng(28.625492, 77.383623);
        mMap.addMarker(new MarkerOptions().position(bjain).title("B. Jain Publishers Pvt. Ltd"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bjain));
    }
}
