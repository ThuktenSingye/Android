package com.example.apifetchdemo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<University> universityListOriginal = new ArrayList<>();

    NetworkChangeReceiver networkChangeReceiver;
    RecyclerView rv;
    UniversityAdapter universityAdapter;
    TextView infoDisplay;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkChangeReceiver = new NetworkChangeReceiver();
        rv = findViewById(R.id.recycleView); // Assuming you have a RecyclerView in your layout XML
        btn = findViewById(R.id.button);
        infoDisplay = findViewById(R.id.infoDisplay);
        universityAdapter = new UniversityAdapter(universityListOriginal);
        rv.setAdapter(universityAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //        fetchData
        btn.setOnClickListener((v)->{
            if(networkChangeReceiver.isConnected(getApplicationContext())){
                new FetchData().execute();
            }else{
//
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
//        register broadcast receiver
        // Register the BroadcastReceiver to listen for connectivity changes
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        // act as filter that define which broadcast event the broadcast receiver should listen to
        registerReceiver(networkChangeReceiver, intentFilter);
    }
    @Override
    protected void onDestroy(){
//        destroy broadcast receiver
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    public  class FetchData extends AsyncTask<Void, Void, List<University>> {
        List<University> universityList = new ArrayList<>();

        @Override
        protected List<University> doInBackground(Void... voids) {
            try{
                // creating url endpoint
                URL url = new URL("https://api.coincap.io/v2/assets");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                // check for response
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
//                successful in reading data
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while((line= reader.readLine())!=null){ // keep on reading
                        stringBuilder.append(line);
                    }
                    reader.close();
                    String jsonData = stringBuilder.toString();
                    JSONObject jsonObject = new JSONObject(jsonData);
                    // Extract the "data" array
                    JSONArray dataArray = jsonObject.getJSONArray("data");
                    // Check if the JSON array is not empty
                    if(dataArray.length()>0){
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject itemObject = dataArray.getJSONObject(i);

                            // Extract values from each item
                            String name = itemObject.getString("name");
                            String priceUsd = itemObject.getString("priceUsd");
                            University university= new University(name, priceUsd);
                            universityList.add(university);
                            // Now, you can use these values in your Android app as needed.
                        }
                        connection.disconnect();
                    }else{
                        Log.d("Inside fetchAPI", "Error");
                    }

                }else {
                    // Handle the case where the JSON array is empty
                    Log.d("Inside fetchAPI", "Error");
                }

            }catch(Exception e){
                Log.e("Error", "Error: " + e.getMessage()); // Log the specific error message
                e.printStackTrace();
            }
            return  universityList;
        }
        @Override
        protected void onPostExecute(List<University> universities) {
            super.onPostExecute(universities);
//            universityListOriginal = universities;
            if (universityAdapter != null) {
                infoDisplay.setVisibility(View.GONE);
                universityAdapter.setUniversityList(universities);
//                universityAdapter.notifyDataSetChanged();
            }

        }
    }
}
