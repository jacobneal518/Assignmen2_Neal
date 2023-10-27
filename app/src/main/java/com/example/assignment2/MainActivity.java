package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ItemSelectedListener {

    FragmentManager manager;

    public TickerListFragment tickerListFragment;
    public InfoWebFragment infoWebFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);

        //initialize our data
        Intent intent = getIntent();
        tickerListFragment = new TickerListFragment();
        infoWebFragment = new InfoWebFragment();


        String message = intent.getStringExtra("sms");
        //if no txt message, do defaults
        if(message == null){
            Log.i("sms_OnCreate", "null");
        }
        else{
            Log.i("sms_OnCreate", message);
            String urlKey = "";
            //if formatted correctly, get ticker
            if(message.contains("Ticker:<<") && message.contains(">>") && message.indexOf("Ticker:<<") < message.indexOf(">>")){
                urlKey = message.substring(message.indexOf("Ticker:<<") + 9, message.indexOf(">>"));
                //if valid ticker, update list and website
                if(urlKey.matches("^[a-zA-Z]*$")){
                    urlKey = urlKey.toUpperCase();
                    Log.i("console", "We are adding the following key to the website: " + urlKey);
                    tickerListFragment.addToTickerList(urlKey);
                }
                else{
                    Toast.makeText(this, "Invalid Ticker, must be only letters", Toast.LENGTH_SHORT).show();
                }

            }
            else{
                Toast.makeText(this, "Invalid Watchlist Entry for the Receiver. Must be: \"Ticker:<<[message]>>\"", Toast.LENGTH_SHORT).show();
            }
            //tickerListFragment.setWebsiteFromSMS(message);
        }

        // make sure we have SMS permissions for user
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            String[] perms = new String[]{Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, perms, 101);

        }

        //inflate fragments
        if (savedInstanceState == null) {
            Log.i("insideSavedInstance", "made it to declare ticker");
            manager.beginTransaction().replace(R.id.TickerListFragmentContainerView, tickerListFragment).replace(R.id.InfoWebFragmentContainer, infoWebFragment).commit();
            //manager.beginTransaction().replace(R.id.InfoWebFragmentContainerView, new InfoWebFragment()).commit();
        }

    }

    @Override
    /**
     * Unused method for testing new intent creation
     */
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra("sms");
        Log.d("onNewIntent", message);
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }


    @Override
    /**
     * Interface implementation that allows us to pass the ticker URL to the infoWebFragment to update the website correctly.
     * Solves the problem of making one fragment talk to another fragment
     */
    public void onItemSelected(String url) {
        Log.i("console", "Made it inside onItemSelected in MainActivity");
        if (infoWebFragment != null){
            infoWebFragment.updateWebsite(url);
        }
    }
}