package com.example.internetconnectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) { // trigger when there is changes in network status or match with intent filter
        // Check connectivity status and update UI
        if (isConnected(context)) {

            Toast.makeText(context, "Inside receiver " , Toast.LENGTH_SHORT).show();
            new FetchData(context).execute();
//            fetch data
        } else {


        }
    }
    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
    public static class FetchData extends AsyncTask<Void, Void, String> {
        private final Context context;

        public FetchData(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... params) {
            // Perform the API data retrieval here

            String apiData = fetchDataFromApi();
            return apiData;
        }
        @Override
        protected void onPostExecute(String result) {
            // Update the UI with the fetched data
            Toast.makeText(context, "Fetched Data: " + result, Toast.LENGTH_LONG).show();
        }

        private String fetchDataFromApi() {
//           implement the data retrieval logic here
            try{
//                create url for api endpoint
                URL url = new URL("https://randomuser.me/api");
                // open the connection to url
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                set up connection properties
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(10000);
//                connection.setRequestProperty("X-RapidAPI-Key","8cfe16c4e6msh10986a3b14124a4p1c54b6jsn8084b68cc54d");
//                connection.setRequestProperty("X-RapidAPI-Host","weatherapi-com.p.rapidapi.com");

//                connect to url
                connection.connect();
                // check for respone

                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    // read response data
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder =  new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!= null){
                        stringBuilder.append(line);
                    }
                    reader.close();
                    return stringBuilder.toString(); // converting json to string
                }else{
                    return "HTTP Error:"+ connection.getResponseCode();
                }

            }catch (Exception e){
                e.printStackTrace();
                return "Error:"+ e.getMessage();
            }

        }
    }
}
