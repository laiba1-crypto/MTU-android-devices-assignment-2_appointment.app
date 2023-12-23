package com.example.fitnesshub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitnesshub.R;
import com.example.fitnesshub.adapter.BookingAdapter;
import com.example.fitnesshub.helper.DatabaseHelper;
import com.example.fitnesshub.model.BookingModel;

import java.util.List;

public class Menu extends AppCompatActivity {

    private Button btnBookinglist,btnContactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnBookinglist = findViewById(R.id.btn_bookinglist);
        btnContactUs = findViewById(R.id.btn_contactus);

        btnBookinglist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BookingList.class));
            }
        });

        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ContactUs.class));
            }
        });


    }
}