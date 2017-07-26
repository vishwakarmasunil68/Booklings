package com.emobi.bjain.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.emobi.bjain.R;
import com.emobi.bjain.activity.HomeActivity;
import com.emobi.bjain.adapter.SearchProductAdapter;
import com.emobi.bjain.pojo.newsearch.NewSearchPOJO;
import com.emobi.bjain.pojo.newsearch.NewSearchResultPOJO;
import com.emobi.bjain.utils.Pref;
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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by sunil on 02-05-2017.
 */

public class SearchFragment extends Fragment implements WebServicesCallBack {
    private static final String SEARCH_API_CALL = "search_api_call";
    @BindView(R.id.rv_search)
    RecyclerView rv_search;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.ll_nav_cat)
    LinearLayout ll_nav_cat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_search.post(new Runnable() {
            @Override
            public void run() {
                et_search.requestFocus();
                InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imgr.showSoftInput(et_search, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (et_search.getText().toString().length() > 0) {
                    callSearchAPI(et_search.getText().toString());
//                    ll_products.setVisibility(View.GONE);
                } else {
//                    removeActiveCenterFragments();
//                    ll_products.setVisibility(View.VISIBLE);
                }
            }
        });


        et_search.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(getActivity().getApplicationContext(), et_search.getText(), Toast.LENGTH_SHORT).show();
                    hideKeyboard();
                    return true;
                }
                return false;
            }
        });


        ll_nav_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).openCatNavDrawer();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        hideKeyboard();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideKeyboard();
    }

    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void callSearchAPI(String s) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("product_name", s));
        nameValuePairs.add(new BasicNameValuePair("type", Pref.GetCurrency(getApplicationContext())));
        new WebServiceBase(nameValuePairs, getActivity(), this, SEARCH_API_CALL, false).execute(WebServicesUrls.SEARCH_API_URL);
    }

    public void parseSearchData(String response) {
        //Log.d(TAG, "search data:-" + response);
        try {
            Gson gson = new Gson();
            NewSearchPOJO searchPOJO = gson.fromJson(response, NewSearchPOJO.class);
            if (searchPOJO.getSuccess().equals("true")) {
                List<String> list_string = new ArrayList<>();
                Set<NewSearchResultPOJO> newSearchResultPOJOSet = new HashSet<>();
                for (String s : searchPOJO.getResult()) {
                    if (!s.equals("false")) {
                        list_string.add(s);
                        try {
                            Gson gson1 = new Gson();
                            NewSearchResultPOJO newSearchResultPOJO = gson1.fromJson(s, NewSearchResultPOJO.class);
                            newSearchResultPOJOSet.add(newSearchResultPOJO);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                List<NewSearchResultPOJO> newSearchResultPOJOList=new ArrayList<>(newSearchResultPOJOSet);
//                if (list_string.size() > 0) {
                SearchProductAdapter adapter = new SearchProductAdapter(getActivity(), newSearchResultPOJOList);
                GridLayoutManager gridLayoutManager
                        = new GridLayoutManager(getActivity(), 3);
                rv_search.setLayoutManager(gridLayoutManager);
                rv_search.setAdapter(adapter);
//                }else{
//                    List<String> stringList=new ArrayList<>();
//                    SearchProductAdapter adapter = new SearchProductAdapter(getActivity(), stringList);
//                    GridLayoutManager gridLayoutManager
//                            = new GridLayoutManager(getActivity(), 3);
//                    rv_search.setLayoutManager(gridLayoutManager);
//                    rv_search.setAdapter(adapter);
//                }
            } else {
                ToastClass.showShortToast(getApplicationContext(), "No Result Found");
                List<NewSearchResultPOJO> stringList = new ArrayList<>();
                SearchProductAdapter adapter = new SearchProductAdapter(getActivity(), stringList);
                GridLayoutManager gridLayoutManager
                        = new GridLayoutManager(getActivity(), 3);
                rv_search.setLayoutManager(gridLayoutManager);
                rv_search.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
            List<NewSearchResultPOJO> stringList = new ArrayList<>();
            SearchProductAdapter adapter = new SearchProductAdapter(getActivity(), stringList);
            GridLayoutManager gridLayoutManager
                    = new GridLayoutManager(getActivity(), 3);
            rv_search.setLayoutManager(gridLayoutManager);
            rv_search.setAdapter(adapter);
            ToastClass.showShortToast(getApplicationContext(), "Something went wrong");
        }
    }

    @Override
    public void onGetMsg(String[] msg) {
        String apicall = msg[0];
        String response = msg[1];
        switch (apicall) {
            case SEARCH_API_CALL:
                parseSearchData(response);
                break;
        }
    }
}
