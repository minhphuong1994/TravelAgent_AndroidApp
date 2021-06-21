package com.example.finaltesttravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;
    static final String PREFERENCE_NAME = "SAMPLE-SP";
    private final String TAG = "PHUONG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = this.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

    }

    public void loginButtonPressed(View view) {
        SharedPreferences.Editor prefEditor = this.pref.edit();

        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
//        Intent i = new Intent(this, AttractionsListActivity.class);
//        startActivity(i);
        if(!etUsername.getText().toString().equals("") && !etPassword.getText().toString().equals("") ){
            prefEditor.putString("username",etUsername.getText().toString());
            prefEditor.apply();
            Log.d(TAG,"Saved username: "+etPassword.getText().toString());
            Intent i = new Intent(this, AttractionsListActivity.class);
            startActivity(i);
        }
        else   {
            Toast.makeText(getApplicationContext(), "You must have both username and password", Toast.LENGTH_SHORT).show();
        }


    }
}