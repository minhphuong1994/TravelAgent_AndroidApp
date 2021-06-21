package com.example.finaltesttravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AttractionsListActivity extends AppCompatActivity {

    // TAG
    private final String TAG = "ATTRACTIONS-LIST";

    // ListView
    ListView lvAttractions;

    // data source
    ArrayList<String> attractionNames = new ArrayList<>();
    ArrayList<Attraction> attractionObjs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions_list);

        //Get file contents as String
        String fileContents = loadDataFromFile("attractions.json");
        //Convert the String to an JSON object
        JSONObject fileContentsAsJSON = convertToJSON(fileContents);
        // parse out the relevant data
        parseJSONData(fileContentsAsJSON);


        // initialize the list view
        lvAttractions = (ListView) findViewById(R.id.lvAttractionsList);

        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, attractionNames);
        lvAttractions.setAdapter(adapter);

        // on click listener
        lvAttractions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "User clicked on item " + i);
                Intent intentViewAttractions
                        = new Intent(getApplicationContext(), ViewAttractionActivity.class);
                intentViewAttractions.putExtra("Data",attractionObjs.get(i));
//                Log.d(TAG,"Moved:"+attractionObjs.get(i).toString());
                startActivity(intentViewAttractions);
            }
        });

    }

    public void viewBookingsButtonPressed(View view) {
        Intent i = new Intent(this, BookingsListActivity.class);
        startActivity(i);
    }
    public void logoutButtonPressed(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void parseJSONData(JSONObject jsonObject) {
        Log.d(TAG, "Successfully got the file contents as a json object");
        Log.d(TAG, jsonObject.toString());

        // Example 1: Get the first name
        // What is the data type of "peter" --> String
        try {

            JSONArray arr = jsonObject.getJSONArray("Phuong");
            for(int i =0; i<arr.length();i++){
                Log.d(TAG,"Attraction name: "+arr.getJSONObject(i).getString("name"));
                JSONObject obj = arr.getJSONObject(i);
                attractionNames.add(obj.getString("name"));
                attractionObjs.add(new Attraction(
                        obj.getString("id"),
                        obj.getString("name"),
                        obj.getString("address"),
                        obj.getString("price"),
                        obj.getString("photo"),
                        obj.getString("description")));
            }
//            Log.d(TAG,attractionObjs.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject convertToJSON(String fileData) {
        JSONObject jsonData;
        try {
            // 1. try to convert the string to the JSONObject data type
            jsonData = new JSONObject(fileData);
            // 2. if successful return it
            return jsonData;

        }catch (JSONException e) {
            // 2. if conversion fails, then print error message and return
            e.printStackTrace();
            return null;
        }
    }


    public String loadDataFromFile(String fileName) {
        // get the JSON file from the
        String jsonString;

        try {
            // open the file
            InputStream fileData = this.getAssets().open(fileName);
            // get information about the file
            int fileSize = fileData.available();
            // set up a array to store each piece of data in the file
            // the size of the array is the same size as the file
            byte[] buffer = new byte[fileSize];
            // get all the data from the file
            fileData.read(buffer);

            // close the file
            fileData.close();
            // convert the data to json
            jsonString = new String(buffer, "UTF-8");

            return jsonString;

        } catch (IOException e) {
            Log.d(TAG,"Error opening file.");
            e.printStackTrace();
            return null;
        }
    }

}