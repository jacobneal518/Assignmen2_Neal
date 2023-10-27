package com.example.assignment2;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    /**
     * THis handles the receiving of SMS messages and parsing them
     */
    public void onReceive(Context context, Intent intent) {
        Log.i("SMSText", "On Receiver");
        final Bundle bundle = intent.getExtras();

        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){

            if(bundle != null){
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                String format = bundle.getString("format").toString();
                String sender = "";
                String message = "";
                for(int i = 0 ; i < pdusObj.length ; i++){
                    //parse message and launch new activity with this data
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                    sender = currentMessage.getDisplayOriginatingAddress();
                    message = currentMessage.getDisplayMessageBody();
                    String printMessage = "Sender: " + sender + " Message: " + message;


                    Intent activityIntent = new Intent(context, MainActivity.class);
                    activityIntent.putExtra("sms", message);
                    activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(activityIntent);


                }
            }
        }
    }
}

