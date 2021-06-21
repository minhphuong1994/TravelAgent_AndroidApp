package com.example.finaltesttravel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "booking_table")
public class Booking {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    @NonNull
    public String username;
    @NonNull
    public String numGuests;
    @NonNull
    public String date;
    @NonNull
    public String attractionName;

    public Booking(@NonNull String username, @NonNull String numGuests, @NonNull String date, @NonNull String attractionName) {
        this.username = username;
        this.numGuests = numGuests;
        this.date = date;
        this.attractionName = attractionName;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", numGuests='" + numGuests + '\'' +
                ", date='" + date + '\'' +
                ", attractionName='" + attractionName + '\'' +
                '}';
    }
}
