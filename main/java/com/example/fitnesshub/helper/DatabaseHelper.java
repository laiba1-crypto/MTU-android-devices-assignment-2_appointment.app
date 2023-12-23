package com.example.fitnesshub.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.fitnesshub.model.BookingModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String Database_Name = "fitness_hub";
    private static final String Booking_Table = "booking";
    private static final String ColumnName_Id = "id";
    private static final String ColumnName_name = "name";
    private static final String ColumnName_date = "date";
    private static final String ColumnName_Time = "time";
    private static final String ColumnName_Duration = "duration";

    private static final String Drop_Booking_Table = "DROP TABLE IF EXISTS " + Booking_Table;
    private static final int Version = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_Name, null, Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query = "CREATE TABLE IF NOT EXISTS " + Booking_Table + "(" +
                ColumnName_Id + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                ColumnName_name + " TEXT NOT NULL," +
                ColumnName_date + " TEXT NOT NULL," +
                ColumnName_Time + " TEXT NOT NULL," +
                ColumnName_Duration + " TEXT NOT NULL " + ")";
        db.execSQL(Query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Drop_Booking_Table);
    }

    public boolean addNewTodo(BookingModel bookingModel) {
        SQLiteDatabase sqLiteDatabase = new DatabaseHelper(context).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ColumnName_name, bookingModel.getName());
        contentValues.put(DatabaseHelper.ColumnName_date, bookingModel.getDate());
        contentValues.put(DatabaseHelper.ColumnName_Time, bookingModel.getTime());
        contentValues.put(DatabaseHelper.ColumnName_Duration, bookingModel.getDuration());
        Long result = sqLiteDatabase.insert(Booking_Table, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Failed to insert the data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Confirmed booking", Toast.LENGTH_SHORT).show();
        }
        sqLiteDatabase.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public ArrayList<BookingModel> getAllBookings() {
        SQLiteDatabase sqLiteDatabase = new DatabaseHelper(context).getReadableDatabase();
        ArrayList<BookingModel> bookingModels = new ArrayList<>();
        String query = "SELECT * FROM " + Booking_Table + " ORDER BY " + ColumnName_Id + " DESC";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            BookingModel bookingModel = new BookingModel();
            bookingModel.setId(cursor.getInt(cursor.getColumnIndex(ColumnName_Id)));
            bookingModel.setName(cursor.getString(cursor.getColumnIndex(ColumnName_name)));
            bookingModel.setDate(cursor.getString(cursor.getColumnIndex(ColumnName_date)));
            bookingModel.setTime(cursor.getString(cursor.getColumnIndex(ColumnName_Time)));
            bookingModel.setDuration(cursor.getString(cursor.getColumnIndex(ColumnName_Duration)));
            bookingModels.add(bookingModel);
        }
        cursor.close();
        sqLiteDatabase.close();
        return bookingModels;
    }

    public void deleteItem(String id) {
        SQLiteDatabase sqLiteDatabase = new DatabaseHelper(context).getWritableDatabase();
        int result = sqLiteDatabase.delete(Booking_Table, ColumnName_Id + "=?", new String[]{id});
        if(result == -1 ){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean updateItem(BookingModel bookingModel) {
        SQLiteDatabase sqLiteDatabase = new DatabaseHelper(context).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ColumnName_name, bookingModel.getName());
        contentValues.put(DatabaseHelper.ColumnName_date, bookingModel.getDate());
        contentValues.put(DatabaseHelper.ColumnName_Time, bookingModel.getTime());
        contentValues.put(DatabaseHelper.ColumnName_Duration, bookingModel.getDuration());

        String _id = String.valueOf(bookingModel.getId());

        int result = sqLiteDatabase.update(Booking_Table, contentValues,ColumnName_Id + "=?", new String[]{_id});
        if(result == -1 ){
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            Toast.makeText(context, "Successfully Update", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
