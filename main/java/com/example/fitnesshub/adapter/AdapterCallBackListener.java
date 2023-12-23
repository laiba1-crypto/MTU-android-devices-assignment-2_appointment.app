package com.example.fitnesshub.adapter;

import com.example.fitnesshub.model.BookingModel;

public interface AdapterCallBackListener {

    void onClick(String selectedTime);
    void edit(BookingModel bookingModel);
    void delete(BookingModel bookingModel);

}
