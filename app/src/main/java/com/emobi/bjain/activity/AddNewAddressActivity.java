package com.emobi.bjain.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.emobi.bjain.R;
import com.emobi.bjain.utils.Pref;
import com.emobi.bjain.utils.StringUtils;
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

public class AddNewAddressActivity extends AppCompatActivity implements WebServicesCallBack{


    private final String TAG=getClass().getSimpleName();
    private final String BILLING_API_CALL="billing_api_call";
    private final String SHIPPING_API_CALL="shipping_api_call";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_first_name)
    EditText et_first_name;
    @BindView(R.id.et_last_name)
    EditText et_last_name;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_telephone)
    EditText et_telephone;
    @BindView(R.id.et_street)
    EditText et_street;
    @BindView(R.id.et_region)
    EditText et_region;
    @BindView(R.id.et_city)
    EditText et_city;
    @BindView(R.id.et_postal_code)
    EditText et_postal_code;
    @BindView(R.id.spinner_country)
    Spinner spinner_country;
    @BindView(R.id.btn_save)
    Button btn_save;
    boolean isbilling=false;
    String[] country_code=new String[]{"AC", "AD", "AE", "AF", "AG", "AI", "AL", "AM", "AO", "AQ", "AR", "AS", "AT", "AU", "AW", "AX", "AZ", "BA", "BB", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BL", "BM", "BN", "BO", "BQ", "BR", "BS", "BT", "BV", "BW", "BY", "BZ", "CA", "CC", "CD", "CF", "CG", "CH", "CI", "CK", "CL", "CM", "CN", "CO", "CP", "CR", "CU", "CV", "CW", "CX", "CY", "CZ", "DE", "DG", "DJ", "DK", "DM", "DO", "DZ", "EA", "EC", "EE", "EG", "EH", "ER", "ES", "ET", "FI", "FJ", "FK", "FM", "FO", "FR", "GA", "GB", "GD", "GE", "GF", "GG", "GH", "GI", "GL", "GM", "GN", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY", "HK", "HM", "HN", "HR", "HT", "HU", "IC", "ID", "IE", "IL", "IM", "IN", "IO", "IQ", "IR", "IS", "IT", "JE", "JM", "JO", "JP", "KE", "KG", "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA", "LB", "LC", "LI", "LK", "LR", "LS", "LT", "LU", "LV", "LY", "MA", "MC", "MD", "ME", "MF", "MG", "MH", "MK", "ML", "MM", "MN", "MO", "MP", "MQ", "MR", "MS", "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NC", "NE", "NF", "NG", "NI", "NL", "NO", "NP", "NR", "NU", "NZ", "OM", "PA", "PE", "PF", "PG", "PH", "PK", "PL", "PM", "PN", "PR", "PS", "PT", "PW", "PY", "QA", "RE", "RO", "RS", "RU", "RW", "SA", "SB", "SC", "SD", "SE", "SG", "SH", "SI", "SJ", "SK", "SL", "SM", "SN", "SO", "SR", "SS", "ST", "SV", "SX", "SY", "SZ", "TA", "TC", "TD", "TF", "TG", "TH", "TJ", "TK", "TL", "TM", "TN", "TO", "TR", "TT", "TV", "TW", "TZ", "UA", "UG", "UM", "US", "UY", "UZ", "VA", "VC", "VE", "VG", "VI", "VN", "VU", "WF", "WS", "XK", "YE", "YT", "ZA", "ZM", "ZW"};
    String[] country_arr=new String[]{"Ascension Island,AC", "Andorra,AD", "United Arab Emirates,AE", "Afghanistan,AF", "Antigua & Barbuda,AG", "Anguilla,AI", "Albania,AL", "Armenia,AM", "Angola,AO", "Antarctica,AQ", "Argentina,AR", "American Samoa,AS", "Austria,AT", "Australia,AU", "Aruba,AW", "Åland Islands,AX", "Azerbaijan,AZ", "Bosnia & Herzegovina,BA", "Barbados,BB", "Bangladesh,BD", "Belgium,BE", "Burkina Faso,BF", "Bulgaria,BG", "Bahrain,BH", "Burundi,BI", "Benin,BJ", "St. Barthélemy,BL", "Bermuda,BM", "Brunei,BN", "Bolivia,BO", "Caribbean Netherlands,BQ", "Brazil,BR", "Bahamas,BS", "Bhutan,BT", "Bouvet Island,BV", "Botswana,BW", "Belarus,BY", "Belize,BZ", "Canada,CA", "Cocos (Keeling) Islands,CC", "Congo - Kinshasa,CD", "Central African Republic,CF", "Congo - Brazzaville,CG", "Switzerland,CH", "Côte d’Ivoire,CI", "Cook Islands,CK", "Chile,CL", "Cameroon,CM", "China,CN", "Colombia,CO", "Clipperton Island,CP", "Costa Rica,CR", "Cuba,CU", "Cape Verde,CV", "Curaçao,CW", "Christmas Island,CX", "Cyprus,CY", "Czech Republic,CZ", "Germany,DE", "Diego Garcia,DG", "Djibouti,DJ", "Denmark,DK", "Dominica,DM", "Dominican Republic,DO", "Algeria,DZ", "Ceuta & Melilla,EA", "Ecuador,EC", "Estonia,EE", "Egypt,EG", "Western Sahara,EH", "Eritrea,ER", "Spain,ES", "Ethiopia,ET", "Finland,FI", "Fiji,FJ", "Falkland Islands,FK", "Micronesia,FM", "Faroe Islands,FO", "France,FR", "Gabon,GA", "United Kingdom,GB", "Grenada,GD", "Georgia,GE", "French Guiana,GF", "Guernsey,GG", "Ghana,GH", "Gibraltar,GI", "Greenland,GL", "Gambia,GM", "Guinea,GN", "Guadeloupe,GP", "Equatorial Guinea,GQ", "Greece,GR", "So. Georgia & So. Sandwich Isl.,GS", "Guatemala,GT", "Guam,GU", "Guinea-Bissau,GW", "Guyana,GY", "Hong Kong (China),HK", "Heard & McDonald Islands,HM", "Honduras,HN", "Croatia,HR", "Haiti,HT", "Hungary,HU", "Canary Islands,IC", "Indonesia,ID", "Ireland,IE", "Israel,IL", "Isle of Man,IM", "India,IN", "British Indian Ocean Territory,IO", "Iraq,IQ", "Iran,IR", "Iceland,IS", "Italy,IT", "Jersey,JE", "Jamaica,JM", "Jordan,JO", "Japan,JP", "Kenya,KE", "Kyrgyzstan,KG", "Cambodia,KH", "Kiribati,KI", "Comoros,KM", "St. Kitts & Nevis,KN", "North Korea,KP", "South Korea,KR", "Kuwait,KW", "Cayman Islands,KY", "Kazakhstan,KZ", "Laos,LA", "Lebanon,LB", "St. Lucia,LC", "Liechtenstein,LI", "Sri Lanka,LK", "Liberia,LR", "Lesotho,LS", "Lithuania,LT", "Luxembourg,LU", "Latvia,LV", "Libya,LY", "Morocco,MA", "Monaco,MC", "Moldova,MD", "Montenegro,ME", "St. Martin,MF", "Madagascar,MG", "Marshall Islands,MH", "Macedonia,MK", "Mali,ML", "Myanmar (Burma),MM", "Mongolia,MN", "Macau (China),MO", "Northern Mariana Islands,MP", "Martinique,MQ", "Mauritania,MR", "Montserrat,MS", "Malta,MT", "Mauritius,MU", "Maldives,MV", "Malawi,MW", "Mexico,MX", "Malaysia,MY", "Mozambique,MZ", "Namibia,NA", "New Caledonia,NC", "Niger,NE", "Norfolk Island,NF", "Nigeria,NG", "Nicaragua,NI", "Netherlands,NL", "Norway,NO", "Nepal,NP", "Nauru,NR", "Niue,NU", "New Zealand,NZ", "Oman,OM", "Panama,PA", "Peru,PE", "French Polynesia,PF", "Papua New Guinea,PG", "Philippines,PH", "Pakistan,PK", "Poland,PL", "St. Pierre & Miquelon,PM", "Pitcairn Islands,PN", "Puerto Rico,PR", "Palestinian Territories,PS", "Portugal,PT", "Palau,PW", "Paraguay,PY", "Qatar,QA", "Réunion,RE", "Romania,RO", "Serbia,RS", "Russia,RU", "Rwanda,RW", "Saudi Arabia,SA", "Solomon Islands,SB", "Seychelles,SC", "Sudan,SD", "Sweden,SE", "Singapore,SG", "St. Helena,SH", "Slovenia,SI", "Svalbard & Jan Mayen,SJ", "Slovakia,SK", "Sierra Leone,SL", "San Marino,SM", "Senegal,SN", "Somalia,SO", "Suriname,SR", "South Sudan,SS", "São Tomé & Príncipe,ST", "El Salvador,SV", "Sint Maarten,SX", "Syria,SY", "Swaziland,SZ", "Tristan da Cunha,TA", "Turks & Caicos Islands,TC", "Chad,TD", "French Southern Territories,TF", "Togo,TG", "Thailand,TH", "Tajikistan,TJ", "Tokelau,TK", "Timor-Leste,TL", "Turkmenistan,TM", "Tunisia,TN", "Tonga,TO", "Turkey,TR", "Trinidad & Tobago,TT", "Tuvalu,TV", "Taiwan,TW", "Tanzania,TZ", "Ukraine,UA", "Uganda,UG", "U.S. Outlying Islands,UM", "United States,US", "Uruguay,UY", "Uzbekistan,UZ", "Vatican City,VA", "St. Vincent & Grenadines,VC", "Venezuela,VE", "British Virgin Islands,VG", "U.S. Virgin Islands,VI", "Vietnam,VN", "Vanuatu,VU", "Wallis & Futuna,WF", "Samoa,WS", "Kosovo,XK", "Yemen,YE", "Mayotte,YT", "South Africa,ZA", "Zambia,ZM", "Zimbabwe,ZW"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Add New Address");

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            isbilling=bundle.getBoolean("isbilling");
        }else{
            isbilling=false;
        }

        ArrayAdapter<String> country_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,country_arr);
        spinner_country.setAdapter(country_adapter);
        country_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_country.setSelection(109);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateEts(et_first_name,et_last_name,et_email,et_telephone,et_street
                ,et_region,et_city,et_postal_code)){
                    //saving address
                    callAddBillingAPI();
                }else{
                    ToastClass.showShortToast(getApplicationContext(),"Please Enter All Fields Properly");
                }
            }
        });
    }

    public void callAddBillingAPI(){
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("user_id", Pref.GetStringPref(getApplicationContext(), StringUtils.ENTITY_ID,"")));
        nameValuePairs.add(new BasicNameValuePair("firstname", et_first_name.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("lastname", et_last_name.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("street", et_street.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("city", et_city.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("country_id", country_code[spinner_country.getSelectedItemPosition()]));
        nameValuePairs.add(new BasicNameValuePair("region", et_region.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("postcode", et_postal_code.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("telephone", et_telephone.getText().toString()));
        if(isbilling) {
            new WebServiceBase(nameValuePairs, this, BILLING_API_CALL).execute(WebServicesUrls.ADD_SHIPPING_URL);
        }
        else{
            new WebServiceBase(nameValuePairs, this, SHIPPING_API_CALL).execute(WebServicesUrls.ADD_SHIPPING_URL);
        }
    }

    public boolean validateEts(EditText... editTexts){
        boolean bol=true;
        for(EditText editText:editTexts){
            if(editText.getText().toString().length()==0){
                bol=false;
            }
        }
        return bol;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetMsg(String[] msg) {
        String apicall=msg[0];
        String response=msg[1];
        switch (apicall){
            case BILLING_API_CALL:
                parseShippingapi(response);
                break;
            case SHIPPING_API_CALL:
                parseShippingapi(response);
                break;
        }
    }
    public void parseShippingapi(String response){
        Log.d(TAG,"shipping response:-"+response);
        try{
            JSONObject jsonObject=new JSONObject(response);
            if(jsonObject.optString("success").equals("true")){
                ToastClass.showShortToast(getApplicationContext(),"Address Added Successfully");
                finish();
            }
            else{
                ToastClass.showShortToast(getApplicationContext(),"Something went wrong");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
