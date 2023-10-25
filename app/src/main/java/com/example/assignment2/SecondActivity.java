package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    FragmentManager manager;

    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = getSupportFragmentManager();
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();


        data = intent.getExtras().getString(Intent.EXTRA_TEXT);
        //Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        if (savedInstanceState == null) {
            manager.beginTransaction().replace(R.id.fragmentContainer2, new InfoWebFragment()).commit();
        }
    }

    public String getIntentData(){
        return data;
    }

    public void print(String message){
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}