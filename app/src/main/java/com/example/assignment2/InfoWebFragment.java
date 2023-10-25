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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
    // TODO: Rename and change types and number of parameters
    public static InfoWebFragment newInstance(String param1, String param2) {
        InfoWebFragment fragment = new InfoWebFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //String intent = getActivity().;


        String data = ((SecondActivity)getActivity()).getIntentData();
        if(data == null){
            Log.i("intentData", "null");
        }
        else{
            Log.i("intentData", data);
        }



        String url = "https://pastebin.com/";
        View v = inflater.inflate(R.layout.fragment_info_web, container, false);

        WebView myWebview = (WebView) v.findViewById(R.id.webView1);
        myWebview.getSettings().setJavaScriptEnabled(true);
        myWebview.getSettings().setDomStorageEnabled(true);
        myWebview.setWebViewClient(new WebViewClient());
        myWebview.loadUrl(data);

        myWebview.clearView();
        myWebview.measure(100, 100);
        myWebview.getSettings().setUseWideViewPort(true);
        myWebview.getSettings().setLoadWithOverviewMode(true);

        return v;
    }
}