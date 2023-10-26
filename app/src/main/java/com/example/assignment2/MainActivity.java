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

        /*
        smsReceiver = new SMSReceiver(){
            public void onReceive(Context context, Intent intent){
                Log.i("SMSText", "On Receiver");
                final Bundle bundle = intent.getExtras();

                if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){

                    if(bundle != null){
                        Object[] pdusObj = (Object[]) bundle.get("pdus");
                        String format = bundle.getString("format").toString();
                        String sender = "";
                        String message = "";
                        for(int i = 0 ; i < pdusObj.length ; i++){
                            SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                            sender = currentMessage.getDisplayOriginatingAddress();
                            message = currentMessage.getDisplayMessageBody();
                            String printMessage = "Sender: " + sender + " Message: " + message;
                            //Toast.makeText(context, printMessage, Toast.LENGTH_SHORT).show();

                            updateTickerList(message);
                        }
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(smsReceiver, filter);

         */

        Intent intent = getIntent();
        tickerListFragment = new TickerListFragment();
        infoWebFragment = new InfoWebFragment();


        String message = intent.getStringExtra("sms");
        if(message == null){
            Log.i("sms_OnCreate", "null");
        }
        else{
            Log.i("sms_OnCreate", message);
            tickerListFragment.addToTickerList(message);
            //tickerListFragment.setWebsiteFromSMS(message);
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            String[] perms = new String[]{Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, perms, 101);

        }

        /*
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
        */

        if (savedInstanceState == null) {
            Log.i("insideSavedInstance", "made it to declare ticker");
            manager.beginTransaction().replace(R.id.TickerListFragmentContainerView, tickerListFragment).replace(R.id.InfoWebFragmentContainer, infoWebFragment).commit();
            //manager.beginTransaction().replace(R.id.InfoWebFragmentContainerView, new InfoWebFragment()).commit();
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra("sms");
        Log.d("onNewIntent", message);
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    public void updateTickerList(String message){
        tickerListFragment.addToTickerList(message);
    }


    @Override
    public void onItemSelected(String url) {
        Log.i("console", "Made it inside onItemSelected in MainActivity");
        if (infoWebFragment != null){
            infoWebFragment.updateWebsite(url);
        }
    }
}