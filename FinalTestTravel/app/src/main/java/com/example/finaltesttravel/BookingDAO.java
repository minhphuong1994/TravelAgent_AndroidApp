package com.example.finaltesttravel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookingDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE) //the @Insert will perform insert query. onConflict Allow to insert records with same info
    void insert(Booking e);


    @Query("SELECT * FROM booking_table")
    List<Booking> getAllBooking();
}
