package com.example.finaltesttravel.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.finaltesttravel.Booking;
import com.example.finaltesttravel.BookingDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//@TODO: Add tables to the Entites  array
@Database(entities = {Booking.class}, version = 1, exportSchema = false)
public abstract class ReservationsDatabase extends RoomDatabase {

    // @TODO: List of DAOS
     public abstract BookingDAO bookingDAO();

    // a singleton
    // Only allow 1 connection / instance of the database to exist in your app
    private static volatile ReservationsDatabase INSTANCE;

    // variables related to running the database in the background (asyncronously)
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Other parts of your app will use this function to get a "copy" of the database
    // (or , a connection to the database)
    public static ReservationsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ReservationsDatabase.class) {
                // The first time this app runs, there will be NO database
                // So, create the database, rename it to office_database, and return it
                // The next  time you run the app, this code will be skipped
                // Your app will just look for the pre-existing database
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ReservationsDatabase.class, "reservations_db")
                            .fallbackToDestructiveMigration()       // allows you to make changes to database structure
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


