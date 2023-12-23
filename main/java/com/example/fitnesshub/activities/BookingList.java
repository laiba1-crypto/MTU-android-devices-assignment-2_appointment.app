package com.example.fitnesshub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fitnesshub.R;
import com.example.fitnesshub.adapter.AdapterCallBackListener;
import com.example.fitnesshub.adapter.BookingAdapter;
import com.example.fitnesshub.helper.DatabaseHelper;
import com.example.fitnesshub.model.BookingModel;

import java.util.List;

public class BookingList extends AppCompatActivity {

    RecyclerView rvBooking;
    ImageView noRecord;
    Button btnback,btnBookNow;
    List<BookingModel> dbList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        rvBooking = findViewById(R.id.rvbooking);
        noRecord = findViewById(R.id.norecord);
        btnBookNow = findViewById(R.id.btn_menuBooknow);
        btnback = findViewById(R.id.btn_menuback);

        DatabaseHelper db = new DatabaseHelper(this);
        dbList = db.getAllBookings();
        setVisibility();

        rvBooking.setLayoutManager(new LinearLayoutManager(this));
        BookingAdapter adapter = new BookingAdapter(this, dbList, new AdapterCallBackListener() {
            @Override
            public void onClick(String selectedTime) {

            }

            @Override
            public void edit(BookingModel bookingModel) {
                startActivity(new Intent(getApplicationContext(),BookNow.class)
                        .putExtra("bookingmodel", bookingModel)
                        .putExtra("edit",true));
                finish();
            }

            @Override
            public void delete(BookingModel bookingModel) {
                String _id = String.valueOf(bookingModel.getId());
                db.deleteItem(_id);
                dbList.remove(bookingModel);
                setVisibility();
            }
        });

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

        rvBooking.setAdapter(adapter);
    }

    public  void setVisibility(){
        if (dbList.size() == 0){
            noRecord.setVisibility(View.VISIBLE);
            rvBooking.setVisibility(View.GONE);
        }
        else{
            noRecord.setVisibility(View.GONE);
            rvBooking.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}