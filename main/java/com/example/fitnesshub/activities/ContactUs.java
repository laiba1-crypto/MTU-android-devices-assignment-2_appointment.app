package com.example.fitnesshub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitnesshub.R;
import com.example.fitnesshub.fragment.MapsFragment;

public class ContactUs extends AppCompatActivity {

    Button btnback,btnBookNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        btnback = findViewById(R.id.btn_contactback);
        btnBookNow = findViewById(R.id.btn_contactBooknow);

//        Fragment mapFragment = new MapsFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,mapFragment).commit();

        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BookNow.class));
                finish();
            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}