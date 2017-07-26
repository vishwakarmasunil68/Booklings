package com.emobi.bjain.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.emobi.bjain.R;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.StringUtils;
import com.emobi.bjain.utils.TagUtils;
import com.emobi.bjain.utils.ToastClass;
import com.emobi.bjain.webservice.WebServiceBase;
import com.emobi.bjain.webservice.WebServicesCallBack;
import com.emobi.bjain.webservice.WebServicesUrls;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAccountActivity extends AppCompatActivity  implements WebServicesCallBack {

    private static final String CALL_PROFILE_UPDATE_API = "call_profile_update_api";
    private final String TAG=getClass().getSimpleName();
    private final static String REGISTER_API_CALL="register_api_call";


    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_middle_name)
    EditText et_middle_name;
    @BindView(R.id.et_last_name)
    EditText et_last_name;
    @BindView(R.id.et_email)
    EditText et_email;

    @BindView(R.id.btn_update)
    Button btn_update;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("My Account");


        et_name.setText(Pref.GetStringPref(getApplicationContext(), StringUtils.FIRST_NAME,""));
        et_middle_name.setText(Pref.GetStringPref(getApplicationContext(), StringUtils.MIDDLE_NAME,""));
        et_last_name.setText(Pref.GetStringPref(getApplicationContext(), StringUtils.LAST_NAME,""));
        et_email.setText(Pref.GetStringPref(getApplicationContext(), StringUtils.EMAIL_ID,""));

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBjainUpdateAPI();
            }
        });
    }

    public void callBjainUpdateAPI(){
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("first_name", et_name.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("middle_name", et_middle_name.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("last_name", et_last_name.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("email", et_email.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("user_id", Pref.GetStringPref(getApplicationContext(),StringUtils.ENTITY_ID,"")));
        new WebServiceBase(nameValuePairs, this, CALL_PROFILE_UPDATE_API).execute(WebServicesUrls.PROFILE_UPDATE);
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
    public void onGetMsg(String[] msg) {
        String apicall=msg[0];
        String response=msg[1];
        switch (apicall){
            case CALL_PROFILE_UPDATE_API:
                parseUpdateResponse(response);
                break;
        }
    }
    public void parseUpdateResponse(String response){
        Log.d(TagUtils.getTag(),"update response:-"+response);
        try{
            JSONObject jsonObject=new JSONObject(response);
            if(jsonObject.optString("Success").equals("true")){
                ToastClass.showShortToast(getApplicationContext(),"Profile Updated");
                Pref.SetStringPref(getApplicationContext(),StringUtils.FIRST_NAME,et_name.getText().toString());
                Pref.SetStringPref(getApplicationContext(),StringUtils.MIDDLE_NAME,et_middle_name.getText().toString());
                Pref.SetStringPref(getApplicationContext(),StringUtils.LAST_NAME,et_last_name.getText().toString());
                Pref.SetStringPref(getApplicationContext(),StringUtils.EMAIL_ID,et_email.getText().toString());
            }else{
                ToastClass.showShortToast(getApplicationContext(),"Failed to update Profile");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
