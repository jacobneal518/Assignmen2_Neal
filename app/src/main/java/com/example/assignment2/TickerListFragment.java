
package com.example.assignment2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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




    public TickerListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TickerNameList[0] = "BAC";
        TickerNameList[1] = "AAPL";
        TickerNameList[2] = "DIS";

        TickerLinkList[0] = "https://seekingalpha.com/symbol/BAC";
        TickerLinkList[1] = "https://seekingalpha.com/symbol/AAPL";
        TickerLinkList[2] = "https://seekingalpha.com/symbol/DIS";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticker_list, container, false);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, TickerNameList);

        listView = view.findViewById(R.id.tickerList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int listIndex, long l) {

                Uri uri;
                if(TickerLinkList[listIndex] == ""){
                    uri = Uri.parse("https://seekingalpha.com/");
                }
                else{
                    uri = Uri.parse(TickerLinkList[listIndex]);
                }

                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, TickerLinkList[listIndex]);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }




    /*
    public void addToTickerList(String ticker){
        if(TickerList.length >= 6){
            TickerList[5] = ticker;
        }
        else{
            for(int i = 0; i < TickerList.length; i++){
                if(TickerList[i] == ""){
                    TickerList[i] = ticker;
                    break;
                }
            }
        }
    }
    */

    /**
     * Remove item from Ticker list at index
     * @param index
     */
    public void removeFromTickerList(int index){

    }

}