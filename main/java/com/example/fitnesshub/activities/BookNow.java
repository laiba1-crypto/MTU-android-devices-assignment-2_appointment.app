package com.example.fitnesshub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnesshub.R;
import com.example.fitnesshub.adapter.AdapterCallBackListener;
import com.example.fitnesshub.adapter.TimeAdapter;
import com.example.fitnesshub.helper.DatabaseHelper;
import com.example.fitnesshub.helper.Timedata;
import com.example.fitnesshub.model.BookingModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookNow extends AppCompatActivity {

    Button btnBooked, btngotomenu, btnbookback;
    RecyclerView rvTime;
    CardView cvOneDuration,cvTwoDuration;
    TextView tvOneDuration,tvTwoDuration ,tvDate;

    ImageView ivDate;
    String selectedDate, time , duration = "";
    EditText edname;
    BookingModel bookingModel;
    Boolean isEdit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);

        bookingModel = (BookingModel) getIntent().getSerializableExtra("bookingmodel");
        isEdit =  getIntent().getBooleanExtra("edit",false);

        tvDate = findViewById(R.id.tv_date);
        ivDate = findViewById(R.id.iv_date);
        rvTime = findViewById(R.id.rv_time);
        tvOneDuration = findViewById(R.id.tv_durationonehour);
        tvTwoDuration = findViewById(R.id.tv_durationtwohour);
        cvOneDuration = findViewById(R.id.cvoneduration);
        cvTwoDuration = findViewById(R.id.cvtwoduration);
        btngotomenu = findViewById(R.id.btn_gotomenu);
        btnBooked = findViewById(R.id.btn_booked);
        btnbookback = findViewById(R.id.btn_bookback);
        edname = findViewById(R.id.edname);

        if(isEdit){
            edname.setText(bookingModel.getName());
            tvDate.setText(bookingModel.getDate());
            selectedDate = bookingModel.getDate();
            time = bookingModel.getTime();
           if(bookingModel.getDuration().contains("1 Hour")){
               cvOneDuration.setCardBackgroundColor(getColor(R.color.blue));
               cvTwoDuration.setCardBackgroundColor(getColor(R.color.white));
               tvTwoDuration.setTextColor(getColor(R.color.black));
               tvOneDuration.setTextColor(getColor(R.color.white));
               duration = bookingModel.getDuration();
           }else{
               cvTwoDuration.setCardBackgroundColor(getColor(R.color.blue));
               cvOneDuration.setCardBackgroundColor(getColor(R.color.white));
               tvOneDuration.setTextColor(getColor(R.color.black));
               tvTwoDuration.setTextColor(getColor(R.color.white));
               duration = bookingModel.getDuration();
           }
        }


        List<String> timedata = ListOfTime();
        DatabaseHelper db = new DatabaseHelper(this);
        List<BookingModel> dbList = db.getAllBookings();
        rvTime.setLayoutManager(new GridLayoutManager(this, 3));

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });

        ivDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });

        btnbookback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




        TimeAdapter timeAdapter = new TimeAdapter(this, timedata, dbList, new AdapterCallBackListener() {
            @Override
            public void onClick(String selectedTime) {
                time = selectedTime;
            }

            @Override
            public void edit(BookingModel bookingModel) {

            }

            @Override
            public void delete(BookingModel bookingModel) {

            }
        });

        btngotomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Menu.class));
            }
        });

        cvOneDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cvOneDuration.setCardBackgroundColor(getColor(R.color.blue));
                cvTwoDuration.setCardBackgroundColor(getColor(R.color.white));
                tvTwoDuration.setTextColor(getColor(R.color.black));
                tvOneDuration.setTextColor(getColor(R.color.white));
                duration = tvOneDuration.getText().toString().trim();
            }
        });

        cvTwoDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cvTwoDuration.setCardBackgroundColor(getColor(R.color.blue));
                cvOneDuration.setCardBackgroundColor(getColor(R.color.white));
                tvOneDuration.setTextColor(getColor(R.color.black));
                tvTwoDuration.setTextColor(getColor(R.color.white));
                duration = tvTwoDuration.getText().toString().trim();
            }
        });

        rvTime.setAdapter(timeAdapter);

        btnBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = selectedDate;
                String name = edname.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(), "Please enter the Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(date) || date.contains("Select the date")) {
                    Toast.makeText(getApplicationContext(), "Please select the date", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(time)) {
                    Toast.makeText(getApplicationContext(), "Please select the time", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(duration)) {
                    Toast.makeText(getApplicationContext(), "Please select the duration", Toast.LENGTH_SHORT).show();
                } else {

                    if(isEdit){
                        Boolean result = db.updateItem(new BookingModel(bookingModel.getId(),name,date, time, duration));
                        if (result) {
                            tvDate.setText("");
                            edname.setText("");
                            startActivity(new Intent(getApplicationContext(),BookingList.class));
                            finish();
                        }
                    }else {
                        Boolean result = db.addNewTodo(new BookingModel(name,date, time, duration));
                        if (result) {
                            tvDate.setText("");
                            edname.setText("");
                            startActivity(new Intent(getApplicationContext(),BookingList.class));
                            finish();
                        }
                    }
                }


            }
        });
    }

    public void datePicker(){
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        java.util.Date currentTime = new Date();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(currentTime.getTime());



        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvDate.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                selectedDate = (year + "/" + (month + 1) + "/" + dayOfMonth).toString();
            }
        }, mYear, mMonth, mDay);
        dialog.getDatePicker().setMinDate(System.currentTimeMillis());
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public List<String> ListOfTime() {
        List<String> timeList = new ArrayList<String>();
        timeList.add("12:00 PM");
        timeList.add("12:30 PM");
        timeList.add("01:00 PM");
        timeList.add("01:30 PM");
        timeList.add("02:00 PM");
        timeList.add("02:30 PM");
        timeList.add("03:00 PM");
        timeList.add("03:30 PM");
        timeList.add("04:00 PM");
        timeList.add("04:30 PM");
        timeList.add("05:00 PM");
        timeList.add("05:30 PM");
        timeList.add("06:00 PM");
        timeList.add("06:30 PM");
        timeList.add("07:00 PM");
        timeList.add("07:30 PM");
        timeList.add("08:00 PM");
        timeList.add("08:30 PM");
        timeList.add("09:00 PM");
        timeList.add("09:30 PM");
        timeList.add("10:00 PM");
        timeList.add("10:30 PM");
        timeList.add("11:00 PM");
        timeList.add("11:30 PM");

        // Am List
        timeList.add("12:00 AM");
        timeList.add("12:30 AM");
        timeList.add("01:00 AM");
        timeList.add("01:30 AM");
        timeList.add("02:00 AM");
        timeList.add("02:30 AM");
        timeList.add("03:00 AM");
        timeList.add("03:30 AM");
        timeList.add("04:00 AM");
        timeList.add("04:30 AM");
        timeList.add("05:00 AM");
        timeList.add("05:30 AM");
        timeList.add("06:00 AM");
        timeList.add("06:30 AM");
        timeList.add("07:00 AM");
        timeList.add("07:30 AM");
        timeList.add("08:00 AM");
        timeList.add("08:30 AM");
        timeList.add("09:00 AM");
        timeList.add("09:30 AM");
        timeList.add("10:00 AM");
        timeList.add("10:30 AM");
        timeList.add("11:00 AM");
        timeList.add("11:30 AM");
        return  timeList;
    }
}