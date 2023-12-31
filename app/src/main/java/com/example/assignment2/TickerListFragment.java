
package com.example.assignment2;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class TickerListFragment extends Fragment {
    public String[] TickerNameList = {"BAC","AAPL","DIS", "", "", ""};
    public String[] TickerLinkList = {"https://seekingalpha.com/symbol/BAC","https://seekingalpha.com/symbol/AAPL",
            "https://seekingalpha.com/symbol/DIS", "", "", ""};

    ListView listView;

    public ArrayAdapter<String> adapter;

    private ItemSelectedListener listener;




    public TickerListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get default entries
        TickerNameList[0] = "BAC";
        TickerNameList[1] = "AAPL";
        TickerNameList[2] = "DIS";

        TickerLinkList[0] = "https://seekingalpha.com/symbol/BAC";
        TickerLinkList[1] = "https://seekingalpha.com/symbol/AAPL";
        TickerLinkList[2] = "https://seekingalpha.com/symbol/DIS";

        //access interface listener to pass new ticker to website
        listener = (ItemSelectedListener) getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("insideSavedInstance", "about to declare View");
        View view = inflater.inflate(R.layout.fragment_ticker_list, container, false);

        //set adapter to let each item in list be a button
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, TickerNameList);

        listView = view.findViewById(R.id.tickerList);

        listView.setAdapter(adapter);
        Log.i("insideSavedInstance", "successfully set Array Adapter");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int listIndex, long l) {
                //open the URL with the listener depending on which item in list was clicked
                listener.onItemSelected(TickerLinkList[listIndex]);

            }
        });

        // Inflate the layout for this fragment
        Log.i("insideSavedInstance", "return view");
        return view;
    }


    /**
     * Updates ticker list
     * @param ticker
     */
    public void addToTickerList(String ticker){

        for(int i = 0; i < 6; i++){
            //if we find an empty one or we get to the last one, replace
            if(TickerNameList[i] == "" || i == 5){
                TickerNameList[i] = ticker;
                TickerLinkList[i] = "https://seekingalpha.com/symbol/" + ticker;
                break;
            }
        }

        Log.i("console", "Added ticker: " + ticker);
    }


}