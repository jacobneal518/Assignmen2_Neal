package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoWebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoWebFragment extends Fragment {

    public View importantView;

    public InfoWebFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoWebFragment.
     */
    public static InfoWebFragment newInstance(String param1, String param2) {
        InfoWebFragment fragment = new InfoWebFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //default url
        String url = "https://seekingalpha.com/";

        //see if we have an SMS
        Intent intent = getActivity().getIntent();
        if(intent == null){
            Log.i("console", "Intent is null inside InfoWebFragment");
        }
        else{
            String message = intent.getStringExtra("sms");
            if(message == null){
                Log.i("console", "message is null inside InfoWebFragment");
            }
            //make sure format is correct and if it is, update website view accordingly
            else{
                if(message.contains("Ticker:<<") && message.contains(">>") && message.indexOf("Ticker:<<") < message.indexOf(">>")){
                    String urlKey = message.substring(message.indexOf("Ticker:<<") + 9, message.indexOf(">>"));
                    if(urlKey.matches("^[a-zA-Z]*$")){
                        urlKey = urlKey.toUpperCase();
                        url = "https://seekingalpha.com/symbol/" + urlKey;
                    }
                }
            }
        }

        //inflate view
        importantView = inflater.inflate(R.layout.fragment_info_web, container, false);

        //update url display for WebView
        updateWebsite(url);


        return importantView;
    }

    /**
     * Updates website displayed
     * @param url
     */
    public void updateWebsite(String url){

        Log.i("console", "Tried to update website to: " + url);
        WebView myWebview = (WebView) importantView.findViewById(R.id.webView1);
        myWebview.getSettings().setJavaScriptEnabled(true);
        myWebview.getSettings().setDomStorageEnabled(true);
        myWebview.setWebViewClient(new WebViewClient());
        myWebview.loadUrl(url);

        myWebview.clearView();
        myWebview.measure(100, 100);
        myWebview.getSettings().setUseWideViewPort(true);
        myWebview.getSettings().setLoadWithOverviewMode(true);
    }
}