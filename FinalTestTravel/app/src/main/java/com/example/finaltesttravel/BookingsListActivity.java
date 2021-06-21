package com.example.finaltesttravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.finaltesttravel.db.ReservationsDatabase;

import java.util.ArrayList;
import java.util.List;

public class BookingsListActivity extends AppCompatActivity {
    private final  String TAG = "BOOKING-LIST";
    private static final String PREFERENCE_NAME = "SAMPLE-SP";
    private SharedPreferences prefs;
    // Text View
    TextView resultsList;
    private ReservationsDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings_list);

        // initialize the list of results
        resultsList = (TextView) findViewById(R.id.tvBookings);

        this.prefs = this.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        mDb = ReservationsDatabase.getDatabase(getApplicationContext());
        List<Booking> booking_list = mDb.bookingDAO().getAllBooking();
        Log.d(TAG,booking_list.toString());

        String str = "";
        String username = this.prefs.getString("username","");
        Log.d(TAG,"username: "+username);
        for(Booking item : booking_list){
            if(item.username.equals(username)){
                str +=item.attractionName+"\n"+item.date+"\n\n";
            }
        }
        if (str.equals("")){
            str = "No reservations found";
        }
        resultsList.setText(str);
    }

    public void backButtonPressed(View view) {
        Intent i = new Intent(this, AttractionsListActivity.class);
        startActivity(i);
    }
}