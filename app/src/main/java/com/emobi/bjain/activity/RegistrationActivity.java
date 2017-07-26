package com.emobi.bjain.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.emobi.bjain.R;
import com.emobi.bjain.utils.FileUtils;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.StringUtils;
import com.emobi.bjain.webservice.WebServiceBase;
import com.emobi.bjain.webservice.WebServicesCallBack;
import com.emobi.bjain.webservice.WebServicesUrls;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements WebServicesCallBack{

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
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_confirm_password)
    EditText et_confirm_password;

    @BindView(R.id.btn_register)
    Button btn_register;

    @BindView(R.id.iv_fb_btn)
    ImageView iv_fb_btn;

    CallbackManager callbackManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("Success", "Login");
                        RequestData();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(RegistrationActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(RegistrationActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Registration");
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckValidation(et_name,et_last_name,et_email,et_password,et_confirm_password)){
                    if(et_password.getText().toString().equals(et_confirm_password.getText().toString())) {
                        callRegisterAPI();
                    }else{
                        Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Please Enter Mandatory Data",Toast.LENGTH_LONG).show();
                }
            }
        });
        iv_fb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FbIntegration();
            }
        });
    }


    public void FbIntegration() {
        LoginManager.getInstance().logInWithReadPermissions(RegistrationActivity.this, Arrays.asList("public_profile", "user_friends", "email"));
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

    public void callRegisterAPI(){
        String request_data=et_name.getText().toString()+","+et_middle_name.getText().toString()+","+
                et_last_name.getText().toString()+","+et_password.getText().toString();

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("email", et_email.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("value", request_data));
        nameValuePairs.add(new BasicNameValuePair("attribute_id", "5,6,7,12"));
        new WebServiceBase(nameValuePairs, this, REGISTER_API_CALL).execute(WebServicesUrls.REGISTRATION_URL);
    }


    public void parseLoginData(String response) {
        Log.d(TAG, "response:-" + response);
        if(response.contains("Duplicate entry")){
            Toast.makeText(getApplicationContext(),"User Already Registerd",Toast.LENGTH_LONG).show();
        }else {
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.optString("Success").equals("true")) {
                    Pref.SetStringPref(getApplicationContext(), StringUtils.QUOTOID, jsonObject.optString("quoto_id"));
                    JSONArray jsonArray = jsonObject.optJSONArray("Result");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject innerObject = jsonArray.optJSONObject(i);
                        try {
                            String attribute_id = innerObject.optString("attribute_id");
                            switch (attribute_id) {
                                case "5":
                                    Pref.SetStringPref(getApplicationContext(), StringUtils.FIRST_NAME, innerObject.optString("value"));
                                    break;
                                case "6":
                                    Pref.SetStringPref(getApplicationContext(), StringUtils.MIDDLE_NAME, innerObject.optString("value"));
                                    break;
                                case "7":
                                    Pref.SetStringPref(getApplicationContext(), StringUtils.LAST_NAME, innerObject.optString("value"));
                                    break;
                                case "12":
                                    Pref.SetStringPref(getApplicationContext(), StringUtils.PASSWORD, innerObject.optString("value"));
                                    break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if ((i + 1) == (jsonArray.length() - 1)) {
                            Pref.SetStringPref(getApplicationContext(), StringUtils.ENTITY_ID, innerObject.optString("value"));
                        }
                        if ((i + 1) == jsonArray.length()) {
                            Pref.SetStringPref(getApplicationContext(), StringUtils.EMAIL_ID, innerObject.optString("value"));
                        }
                    }
                    Pref.SetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, true);

                    Toast.makeText(getApplicationContext(), "Registration Successfull", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }



    public boolean CheckValidation(EditText... ets){
        boolean con=true;
        for(EditText editText:ets){
            if(editText.getText().toString().length()==0){
                con=false;
            }
        }
        return con;
    }

    @Override
    public void onGetMsg(String[] msg) {
        String apicall=msg[0];
        String response=msg[1];
        switch (apicall){
            case REGISTER_API_CALL:
//                parseRegisterData(response);
                parseLoginData(response);
                break;
        }
    }
    public void parseRegisterData(String response){
        Log.d(TAG,"register response:-"+response);
        if(response.contains("Duplicate entry")){
            Toast.makeText(getApplicationContext(),"User Already Registerd",Toast.LENGTH_LONG).show();
        }else {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String Success = jsonObject.optString("Success");
                if (Success.equals("true")) {
                    Pref.SetStringPref(getApplicationContext(), StringUtils.FIRST_NAME, et_name.getText().toString());
                    Pref.SetStringPref(getApplicationContext(), StringUtils.LAST_NAME, et_last_name.getText().toString());
                    Pref.SetStringPref(getApplicationContext(), StringUtils.MIDDLE_NAME, et_middle_name.getText().toString());
                    Pref.SetStringPref(getApplicationContext(), StringUtils.EMAIL_ID, et_email.getText().toString());
                    Pref.SetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, true);
                    Toast.makeText(getApplicationContext(), "User Registered Successfully", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void RequestData() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("sunil", response.toString());
                JSONObject json = response.getJSONObject();
                try {
                    if (json != null) {
//                        String text = "<b>Name :</b> "+json.getString("name")+"<br><br><b>Email :</b> "+json.getString("email")+"<br><br><b>Profile link :</b> "+json.getString("link");
//                        Log.d("sunil",text);
                        Log.d("sunil", json.getString("name"));
                        Log.d("sunil", json.getString("link"));
                        Log.d("sunil", json.getString("id"));
                        Log.d("sunil", json.getString("email"));
                        String path = FileUtils.BASE_FILE_PATH;
                        File file = new File(path + File.separator + "fb_profile.png");
                        String profile_url="https://graph.facebook.com/" + json.getString("id") + "/picture?type=large";

//                        getBitmapCallApi(profile_url,json.getString("name"),
//                                json.getString("email"),"");
                        String request_data=json.getString("name")+","+""+","+
                                ""+","+"1234";

                        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                        nameValuePairs.add(new BasicNameValuePair("email", json.getString("email")));
                        nameValuePairs.add(new BasicNameValuePair("value", request_data));
                        nameValuePairs.add(new BasicNameValuePair("attribute_id", "5,6,7,12"));
                        new WebServiceBase(nameValuePairs, RegistrationActivity.this, REGISTER_API_CALL).execute(WebServicesUrls.REGISTRATION_URL);
                    }

                } catch (JSONException e) {
                    Log.d("profile", e.toString());
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void getBitmapCallApi(final String image_url, final String name, final String email, final String mobile_no) {
//        startActivity(new Intent(SignupActivity.this, HomeActivity.class));
        new AsyncTask<Void, Void, Void>() {
            Bitmap myBitmap;

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    URL url = new URL(image_url);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    myBitmap = BitmapFactory.decodeStream(input);
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
//                callRegisterAPIUsingFB();
            }
        }.execute();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
