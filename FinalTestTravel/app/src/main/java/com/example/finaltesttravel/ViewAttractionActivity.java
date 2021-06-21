package com.example.finaltesttravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finaltesttravel.db.ReservationsDatabase;

import org.w3c.dom.Attr;

public class ViewAttractionActivity extends AppCompatActivity {
    private static final String PREFERENCE_NAME = "SAMPLE-SP";
    private SharedPreferences prefs;
    private final String TAG = "BOOKING";
    // Edit Text Variables
    EditText etBookingDate;
    EditText etNumGuests;

    // Text View Variables
    TextView tvName;
    TextView tvAddress;
    TextView tvPrice;

    Attraction attraction;

    // Image View
    ImageView ivPhoto;
    private ReservationsDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attraction);

        this.prefs = this.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        mDb = ReservationsDatabase.getDatabase(getApplicationContext());


        // initialize layout variables
        tvName = (TextView) findViewById(R.id.tvAttractionName);
        tvAddress = (TextView) findViewById(R.id.tvAttractionAddress);
        tvPrice = (TextView) findViewById(R.id.tvAttractionPrice);
        Intent data = this.getIntent();
        attraction = (Attraction) data.getSerializableExtra("Data");
        tvName.setText(attraction.name);
        tvAddress.setText(attraction.address);
        tvPrice.setText(attraction.price);


        etBookingDate = (EditText) findViewById(R.id.etBookingDate);
        etNumGuests = (EditText) findViewById(R.id.etNumGuests);


        ivPhoto = (ImageView) findViewById(R.id.imageView);
        int id =getResources()
                .getIdentifier(
                        attraction.photo,      // gets the picture file name frm the object
                        "drawable",
                        getPackageName()
                );
        ivPhoto.setImageResource(id);
    }

    public void backButtonPressed(View view) {
        Intent i = new Intent(this, AttractionsListActivity.class);
        startActivity(i);
    }

    public void bookTourPressed(View view) {
        // @TODO: Write code for what should happen if they press the book tour button
        String username = this.prefs.getString("username","");
        String numGuests = etNumGuests.getText().toString();
        String date = etBookingDate.getText().toString();
        if (numGuests.equals("") || date.equals("")){
            Toast.makeText(getApplicationContext(), "You must enter date and number of guests", Toast.LENGTH_SHORT).show();
        }
        else{
            String attractionName = attraction.name;
            Booking obj = new Booking(username,numGuests,date,attractionName);
            mDb.bookingDAO().insert(obj);
            Log.d(TAG,"Inserted");
            Toast.makeText(getApplicationContext(), "You have booked successfully!!", Toast.LENGTH_SHORT).show();
        }
    }
}