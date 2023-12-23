package com.example.fitnesshub.model;

import java.io.Serializable;

public class BookingModel implements Serializable {

    private int id;

    private String name;
    private String date;
    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String duration;

    public BookingModel() {
    }

    @Override
    public String toString() {
        return "BookingModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    public BookingModel(int id, String name, String date, String time, String duration) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.name = name;
    }
    public BookingModel(String name,String date, String time, String duration) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.name = name;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
