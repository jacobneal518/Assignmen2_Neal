package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);

        //Toast.makeText(this, "I MADE IT INSIDE", Toast.LENGTH_LONG).show();
        if (savedInstanceState == null) {
            manager.beginTransaction().replace(R.id.TickerListFragmentContainerView, new TickerListFragment()).commit();
        }
    }


}